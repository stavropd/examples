/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.example.output;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import my.example.metrics.Metric;

/**
 * Takes a list of {@link Metric} and creates an output file with those metrics.
 * @author stavropd
 */
public class FileEmitter {
    
    /**
     * Emit an output file with the metrics as in the @param list.
     * @param list a list with the metrics
     * @param path the path of the output file
     */
    public void writeToFile(List<Metric> list, String path){
        try {
            Files.write(Paths.get(path), (Iterable<String>)list.stream().map(Object::toString)::iterator);
        } catch (IOException ex) {
            Logger.getLogger(FileEmitter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
