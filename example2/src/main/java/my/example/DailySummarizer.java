/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.example;

import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;
import my.example.metrics.Metric;
import my.example.output.EmitterInt;
import my.example.output.FileEmitter;
import my.example.response.ResponseHandler;
import my.example.rest.ApiInvoker;

/**
 * This class makes the REST call to get the response string from which it reads
 * the metrics that are defined and emits an output file with those metrics
 * @author stavropd
 */
public class DailySummarizer {

    private static final Logger LOG = Logger.getLogger(DailySummarizer.class.getName());

    //predicate to check that response from api is successfull
    public final Predicate<Response> responseValidator = r
            -> r.getStatusInfo().getFamily().equals(Response.Status.Family.SUCCESSFUL);

    
    public void getDailySumAndEmitToFile(String url, String filePath) {
        //call the service
        LOG.log(Level.INFO, "REST call to {0}", url);
        String weatherResponse = ApiInvoker.factory(url).callAndGetResponseAsString(responseValidator);

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
        EmitterInt emitter = new FileEmitter();
        emitter.emit(listOfMetrics, filePath);
    }
}
