/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.example;

import java.util.logging.Logger;

/**
 *
 * @author stavropd
 */
public class Main {
    private static final Logger LOG = Logger.getLogger(Main.class.getName());
    
    public static void main(String[] args) {
        String url = "http://api.wunderground.com/api/9556d20a8642174b/history_20171030/q/NY/New_York.json";
        String filePath = "metrics.txt";
        
        DailySummarizer ds = new DailySummarizer();
        ds.getDailySumAndEmitToFile(url, filePath);
    }

}
