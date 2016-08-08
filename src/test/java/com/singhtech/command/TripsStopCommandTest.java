package com.singhtech.command;

import com.singhtech.TestDataFactory;
import com.singhtech.model.Graph;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by sukhdeepsingh1982 on 7/08/2016.
 */
public class TripsStopCommandTest {

    TripStopCommand underTest = new TripStopCommand();

    @Test
    public void testStopTripCommand(){
        Graph graph = TestDataFactory.getGrap();
        String result = underTest.execute(graph, "<4", "CC");
        assertEquals(result, "2");
    }


    @Test
    public void testStopTripCommandNORoute(){
        Graph graph = TestDataFactory.getGrap();
        String result = underTest.execute(graph, "<4", "ZY");
        assertEquals(result, 0);
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testInvalidOperator(){
        Graph graph = TestDataFactory.getGrap();
        String result = underTest.execute(graph, "!4", "ZY");
        assertEquals(result, 0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testInvalidArgument(){
        Graph graph = TestDataFactory.getGrap();
        String result = underTest.execute(graph, "!", " 4", "ZY");
        assertEquals(result, 0);
    }


}