/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.example.output;

import java.util.List;
import my.example.metrics.Metric;

/**
 * Takes a list of metrics and creates an output file with those metrics
 * @author stavropd
 */
public interface EmitterInt {
    public void emit(List<Metric> list, String path);
}
