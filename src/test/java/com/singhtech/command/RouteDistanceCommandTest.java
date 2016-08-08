package com.singhtech.command;

import com.singhtech.TestDataFactory;
import com.singhtech.model.Graph;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Unite test for RouteDistanceCommand.
 *
 * Created by sukhdeepsingh1982 on 7/08/2016.
 */
public class RouteDistanceCommandTest {

    RouteDistanceCommand underTest = new RouteDistanceCommand();

    @Test
    public void testDistanceCommand(){
        Graph graph = TestDataFactory.getGrap();
        String result = underTest.execute(graph, "ABC");
        assertEquals("9", result);

        result = underTest.execute(graph, "AED");
        assertEquals("NO ROUTE", result);
    }

    @Test
    public void testNoRoute(){
        Graph graph = TestDataFactory.getGrap();
        String result = underTest.execute(graph, "AED");
        assertEquals("NO ROUTE", result);
    }

    @Test
    public void testNoNodeExist(){
        Graph graph = TestDataFactory.getGrap();
        String result = underTest.execute(graph, "AEF");
        assertEquals("NO_ROUTE", result);
    }
}
