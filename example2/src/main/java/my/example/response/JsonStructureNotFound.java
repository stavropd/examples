/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.example.response;

/**
 *
 * @author stavropd
 */
public class JsonStructureNotFound extends RuntimeException{

    public JsonStructureNotFound(String structureName) {
        super(structureName+" not found in JSON response.");
    }

}
