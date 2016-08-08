package com.singhtech.command;

import com.singhtech.TestDataFactory;
import com.singhtech.model.Graph;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by sukhdeepsingh1982 on 7/08/2016.
 */
public class ShortestPathCommandTest {

    ShortestPathCommand underTest = new ShortestPathCommand();

    @Test
    public void testShortestPath(){
        Graph graph = TestDataFactory.getGrap();
        String result = underTest.execute(graph, "AC");
        assertEquals(result, "9");
    }


    @Test
    public void testShortestPathSameNode(){
        Graph graph = TestDataFactory.getGrap();
        String result = underTest.execute(graph, "BB");
        assertEquals(result, "9");
    }
}