/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.example.rest;

import javax.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

/**
 *
 * @author stavropd
 */
public class ApiInvoker {
    
    private final String url;
    private final ResteasyClientBuilder restClientBuilder;
    private final ResteasyClient restClient;

    public static ApiInvoker factory(String url){
        return new ApiInvoker(url);
    }
    
    private ApiInvoker(String url) {
        this.url = url;
        restClientBuilder = new ResteasyClientBuilder();
        restClientBuilder.defaultProxy("squid.eurodyn.com", 8080, "http");
        restClient = restClientBuilder.build();
        restClient.abortIfClosed();
    }
    
    public String callAndGetResponseAsString(){
        ResteasyWebTarget target = restClient.target(url);
        Response response = target.request().get();
        
        validateResponse(response);
        
        String weatherResponse = response.readEntity(String.class);
        response.close();
        
        return weatherResponse;
    }
    
    private void validateResponse(Response response){
        //Validate the response status code
        if(!response.getStatusInfo().getFamily().equals(Response.Status.Family.SUCCESSFUL))
        {
            throw new RuntimeException("Failed with HTTP error code : " 
                   + response.getStatus());
        }
    }
}
