package com.singhtech.model;

/**
 * Created by sukhdeepsingh1982 on 05/08/2016.
 */
public class Edge {
	private Node destination;
	private int  distance;
	
	public Node getDestination() {
		return destination;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public Edge() {
		destination = null;
		distance = 0;
	}
	
	public Edge(Node n, int d) {
		destination = n;
		distance = d;
	}
	
	public String toString() {
		return destination.getName() + ": " + distance;
	}
}
