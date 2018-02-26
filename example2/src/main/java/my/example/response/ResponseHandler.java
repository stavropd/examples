/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.example.response;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import my.example.metrics.Metric;

/**
 * Reads the given response string and finds the required metrics in the Json response object
 * @author stavropd
 */
public class ResponseHandler {
    
    private final JsonReader reader;
    
    /**
     * Factory to construct the ResponseHandler instance
     * @param response the Json string to read
     * @return a ResponseHandler instance
     */
    public static ResponseHandler factory(String response){
        return new ResponseHandler(response);
    }

    private ResponseHandler(String response) {
        this.reader = Json.createReader(new StringReader(response));
    }

    /**
     * Read the metrics in the Json response
     * @param metric the metrics that should be reported. {@link Metric} should 
     * have a name as this is used to get the value from the Json response.
     * @return a list of the metrics that were read
     */
    public List<Metric> readMetrics(Metric ...metric){
        List<Metric> listOfMetrics = new ArrayList<>();
     
        JsonObject dailySummary = readResponse();
        for (Metric m : metric) {
            if(m.getName()!=null && !m.getName().isEmpty()){
                validateJsonObject(dailySummary, m.getName());
                m.setValue(dailySummary.getString(m.getName()));
                listOfMetrics.add(m);
            }
            else{
                Logger.getLogger(ResponseHandler.class.getName())
                        .log(Level.WARNING, "Ignoring given metric with no name!");
            }
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
