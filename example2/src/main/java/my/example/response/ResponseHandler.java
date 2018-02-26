/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.example.response;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import my.example.metrics.Metric;

/**
 *
 * @author stavropd
 */
public class ResponseHandler {
    
    private final JsonReader reader;
    
    public static ResponseHandler factory(String response){
        return new ResponseHandler(response);
    }

    private ResponseHandler(String response) {
        this.reader = Json.createReader(new StringReader(response));
    }

    public List<Metric> readMetrics(Metric ...metric){
        List<Metric> listOfMetrics = new ArrayList<>();
     
        JsonObject dailySummary = readResponse();
        for (Metric m : metric) {
            validateJsonObject(dailySummary, m.getName());
            m.setValue(dailySummary.getString(m.getName()));
            listOfMetrics.add(m);
        }
        return listOfMetrics;
    }
    
    private JsonObject readResponse(){
        JsonObject jsonObject = reader.readObject();
        
        JsonObject history = jsonObject.getJsonObject("history");
        validateJsonObject(history, "history");
        
        JsonArray summaries = history.getJsonArray("dailysummary");
        validateJsonObject(summaries, "dailysummary");
        
        JsonObject dailySummary = summaries.getJsonObject(0);
        validateJsonObject(dailySummary, "dailysummary element");
        
        return dailySummary;
    }
    
    private void validateJsonObject(Object object, String name){
        if(object==null){
            throw new JsonStructureNotFound(name);
        }
    }

}
