/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.example.response;

import java.util.List;
import my.example.metrics.Metric;

/**
 * Interaface that reads and returns a list of metrics from a source
 * @author stavropd
 */
public interface ResponseHandlerInt {
    public List<Metric> readMetrics(Metric ...metric);
}
