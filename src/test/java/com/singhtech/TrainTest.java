package com.singhtech;

import com.singhtech.command.RouteDistanceCommand;
import com.singhtech.command.TripStopCommand;
import com.singhtech.model.Graph;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by sukhdeepsingh1982 on 6/08/2016.
 */
public class TrainTest {


    @Test
    public void testDistanceCommand(){
        Graph graph = TestDataFactory.getGrap();
        RouteDistanceCommand distanceCommand = new RouteDistanceCommand();
        String result = distanceCommand.execute(graph, "ABC");
        assertEquals("9", result);

        result = distanceCommand.execute(graph, "AED");
        assertEquals("NO_ROUTE", result);
    }

    @Test
    public void testStopTripCommand(){
        Graph graph = TestDataFactory.getGrap();
        TripStopCommand tripStopCommand = new TripStopCommand();
        String result = tripStopCommand.execute(graph, "<4", "CC");
        assertEquals("2", result);

    }

}