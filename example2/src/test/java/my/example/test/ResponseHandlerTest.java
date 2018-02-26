/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.example.test;

import javax.json.stream.JsonParsingException;
import my.example.metrics.Metric;
import my.example.response.JsonStructureNotFound;
import my.example.response.ResponseHandler;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author stavropd
 */
public class ResponseHandlerTest {
    Metric m1 = new Metric("maxhumidity", "Max percentage humidity");
    Metric m2 = new Metric("maxtempm", "Max Temp in C");
    Metric m3 = new Metric("mintempm", "Min Temp in C");
    Metric m4 = new Metric("precipm", "Precipitation in mm");
    
    
    @Test(expected = JsonParsingException.class)
    public void testBadResponse(){
        String response = "{\"bad response\"}";
        
        ResponseHandler.factory(response).readMetrics(m1, m2, m3, m4);
    }
    
    @Test(expected = JsonStructureNotFound.class)
    public void testUnexpectedResponse(){
        String response = "{ \"response\": { \"version\":\"0.1\", "
                + "\"termsofService\":\"http://www.wunderground.com/weather/api/d/terms.html\", \"features\": { \"history\": 1 } } , "
                + "\"history\": { \"date\": { \"pretty\": \"October 30, 2017\", \"year\": \"2017\", \"mon\": \"10\", \"mday\": \"30\", "
                + "\"hour\": \"12\", \"min\": \"00\", \"tzname\": \"America/New_York\" }, \"utcdate\": { \"pretty\": \"October 30, 2017\", "
                + "\"year\": \"2017\", \"mon\": \"10\", \"mday\": \"30\", \"hour\": \"16\", \"min\": \"00\", \"tzname\": \"UTC\" }, \"observations\": [ ]}}";
        
        ResponseHandler.factory(response).readMetrics(m1, m2, m3, m4);
    }
    
    @Test
    public void testExpectedResponse(){
        String response = "{ \"response\": { \"version\":\"0.1\", "
                + "\"termsofService\":\"http://www.wunderground.com/weather/api/d/terms.html\", \"features\": { \"history\": 1 } } , "
                + "\"history\": { \"date\": { \"pretty\": \"October 30, 2017\", \"year\": \"2017\", \"mon\": \"10\", \"mday\": \"30\", "
                + "\"hour\": \"12\", \"min\": \"00\", \"tzname\": \"America/New_York\" }, \"utcdate\": { \"pretty\": \"October 30, 2017\", "
                + "\"year\": \"2017\", \"mon\": \"10\", \"mday\": \"30\", \"hour\": \"16\", \"min\": \"00\", \"tzname\": \"UTC\" }, \"observations\": [ ],"
                + "\"dailysummary\": [{\"maxhumidity\": \"93\",\"maxtempm\": \"16\",\"mintempm\": \"8\",\"precipm\": \"6.35\"}]}}";
        
        ResponseHandler.factory(response).readMetrics(m1, m2, m3, m4);
        
        Assert.assertEquals("93", m1.getValue());
        Assert.assertEquals("16", m2.getValue());
        Assert.assertEquals("8", m3.getValue());
        Assert.assertEquals("6.35", m4.getValue());
    }
}
