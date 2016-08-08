package com.singhtech;

import com.singhtech.model.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by sukhdeepsingh1982 on 7/08/2016.
 */
@Test
@ContextConfiguration(classes = TrainTestApplicationTest.SpringConfig.class)
public class TrainTestApplicationTest extends AbstractTestNGSpringContextTests{

    @Autowired
    TrainTestApplication underTest;

    /**
     *
     1. The distance of the route A-B-C.
     2. The distance of the route A-D.
     3. The distance of the route A-D-C.
     4. The distance of the route A-E-B-C-D.
     5. The distance of the route A-E-D.
     6. The number of trips starting at C and ending at C with a maximum of 3 stops.  In the sample data below, there are two such trips: C-D-C (2 stops). and C-E-B-C (3 stops).
     7. The number of trips starting at A and ending at C with exactly 4 stops. In the sample data below, there are three such trips: A to C (via B,C,D); A to C (via D,C,D); and A to C (via D,E,B).
     8. The length of the shortest route (in terms of distance to travel from A to C.
     9. The length of the shortest route (in terms of distance to travel from B to B.
     10. The number of different routes from C to C with a distance of less than 30. In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC.
     */
    @Test
    public void test(){
        Graph graph = TestDataFactory.getGrap();
        //   1. The distance of the route A-B-C.
        String cmd = "distance ABC";
        String result = underTest.executeCommand(cmd, graph);
        assertEquals(result, "9");

        // 2. The distance of the route A-D.
        cmd = "distance AD";
        result = underTest.executeCommand(cmd, graph);
        assertEquals(result, "5");

        //  3. The distance of the route A-D-C.
        cmd = "distance ADC";
        result = underTest.executeCommand(cmd, graph);
        assertEquals(result, "13");

        //  4. The distance of the route A-E-B-C-D.
        cmd = "distance AEBCD";
        result = underTest.executeCommand(cmd, graph);
        assertEquals(result, "22");

        //   5. The distance of the route A-E-D.
        cmd = "distance AED";
        result = underTest.executeCommand(cmd, graph);
        assertEquals(result, "NO ROUTE");

        //  6. The number of trips starting at C and ending
        // at C with a maximum of 3 stops.
        cmd = "tripStop <4 CC";
        result = underTest.executeCommand(cmd, graph);
        assertEquals(result, "2");

        //   7. The number of trips starting at A and ending at C with exactly 4 stops.
        cmd = "tripStop  =4 AC";
        result = underTest.executeCommand(cmd, graph);
        assertEquals(result, "3");

        //  8.  The length of the shortest route (in terms of distance to travel from A to C.
        cmd = "shortest  AC";
        result = underTest.executeCommand(cmd, graph);
        assertEquals(result, "9");

        // 9. The length of the shortest route (in terms of distance to travel from B to B.
        cmd = "shortest  BB";
        result = underTest.executeCommand(cmd, graph);
        assertEquals(result, "9");

        // 10. The number of different routes from C to C with a distance of less than 30.
        cmd = "tripDistance <30  CC";
        result = underTest.executeCommand(cmd, graph);
        assertEquals(result, "7");

    }


    @Configuration
    @ComponentScan("com.singhtech.")
    public static class SpringConfig {

    }

}