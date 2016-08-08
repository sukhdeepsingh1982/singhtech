package com.singhtech.command;

import com.singhtech.TestDataFactory;
import com.singhtech.model.Graph;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by sukhdeepsingh1982 on 7/08/2016.
 */
public class TripDistanceCommandTest {

    TripDistanceCommand underTest = new TripDistanceCommand();

    @Test
    public void testTripDistanceCommand(){
        Graph graph = TestDataFactory.getGrap();
        String result = underTest.execute(graph, "<30", "CC");
        assertEquals(result, "7");

        result = underTest.execute(graph,  "=9", "AC");
        assertEquals(result, "1");
    }


    @Test
    public void testStopTripCommandNORoute(){
        Graph graph = TestDataFactory.getGrap();
        String result = underTest.execute(graph, "<30", "ZY");
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