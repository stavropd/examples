/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.example.rest;

import java.util.function.Predicate;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

/**
 * Makes the RESTfull call to the given URL and gets the response as a string.
 * @author stavropd
 */
public class ApiInvoker implements ApiInvokerInt{
    
    private final String url;
    private final ResteasyClientBuilder restClientBuilder;
    private final ResteasyClient restClient;

    /**
     * Factory to construct the ApiInvoker.
     * @param url the URL to make the call
     * @return an ApiInvoker
     */
    public static ApiInvokerInt factory(String url){
        return new ApiInvoker(url);
    }
    
    private ApiInvoker(String url) {
        this.url = url;
        restClientBuilder = new ResteasyClientBuilder();
        
        restClient = restClientBuilder.build();
        restClient.abortIfClosed();
    }
    
    /**
     * Make the call to the REST service and read the response as string
     * @param validator validators to test the response
     * @return the string response
     */
    @Override
    public String callAndGetResponseAsString(Predicate<Response> ...validator){
        ResteasyWebTarget target = restClient.target(url);
        Response response = target.request().get();
        
        validateResponse(response);
        
        String weatherResponse = response.readEntity(String.class);
        response.close();
        
        return weatherResponse;
    }
    
    private void validateResponse(Response response, Predicate<Response> ...validator){
        //Validate the response status code
        for(Predicate<Response> v : validator){
            if(!v.test(response)){
                response.close();
                throw new RuntimeException("Invalid response : " 
                   + response.getStatus());
            }
        }
    }
}
