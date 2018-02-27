/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.example.test;

import javax.ws.rs.core.Response;
import my.example.DailySummarizer;
import org.jboss.resteasy.core.ServerResponse;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author stavropd
 */
public class ResponseValidatorsTest {
    private DailySummarizer dailySum;
    
    
    @Before
    public void init(){
        dailySum = new DailySummarizer();
    }
    
    @Test
    public void responseValidatorTest_401() {
        Response r = new ServerResponse(null, 401, null);

        boolean outcome = dailySum.responseValidator.test(r);

        assertFalse(outcome);
    }
    
    @Test
    public void responseValidatorTest_500() {
        Response r = new ServerResponse(null, 500, null);

        boolean outcome = dailySum.responseValidator.test(r);

        assertFalse(outcome);
    }
    
    @Test
    public void responseValidatorTest_302() {
        Response r = new ServerResponse(null, 320, null);

        boolean outcome = dailySum.responseValidator.test(r);

        assertFalse(outcome);
    }
    
    @Test
    public void responseValidatorTest_200() {
        Response r = new ServerResponse(null, 200, null);

        boolean outcome = dailySum.responseValidator.test(r);

        assertTrue(outcome);
    }
}
