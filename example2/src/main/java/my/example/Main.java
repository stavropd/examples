/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.example;

import my.example.metrics.Metric;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import my.example.output.FileEmitter;
import my.example.response.ResponseHandler;
import my.example.rest.ApiInvoker;

/**
 *
 * @author stavropd
 */
public class Main {
    private static final Logger LOG = Logger.getLogger(Main.class.getName());
    
    public static void main(String[] args) {
        String url = "http://api.wunderground.com/api/9556d20a8642174b/history_20171030/q/NY/New_York.json";
        String filePath = "metrics.txt";
        
        apiMetricsToFile(url, filePath);
    }
    
    private static void apiMetricsToFile(String url, String filePath){
        //call the service
        LOG.log(Level.INFO, "REST call to {0}", url);
        String weatherResponse = ApiInvoker.factory(url).callAndGetResponseAsString();
        
        //create the metric objects
        Metric m1 = new Metric("maxhumidity", "Max percentage humidity");
        Metric m2 = new Metric("maxtempm", "Max Temp in C");
        Metric m3 = new Metric("mintempm", "Min Temp in C");
        Metric m4 = new Metric("precipm", "Precipitation in mm");

        //read the metrics from response
        LOG.info("Reading metrics from response");
        List<Metric> listOfMetrics = ResponseHandler.factory(weatherResponse)
                .readMetrics(m1, m2, m3, m4);
        
        //emit file with metrics
        LOG.info("Emit file with metrics");
        FileEmitter emit = new FileEmitter();
        emit.writeToFile(listOfMetrics, filePath);
    }
}
