/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.example.rest;

import java.util.function.Predicate;
import javax.ws.rs.core.Response;

/**
 * Interface to make a RESTfull call and gets the response as a string
 * @author stavropd
 */
public interface ApiInvokerInt {
    public String callAndGetResponseAsString(Predicate<Response> ...validator);
}
