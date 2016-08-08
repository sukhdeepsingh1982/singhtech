package com.singhtech.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sukhdeepsingh1982 on 05/08/2016.
 */
public class Node {
	List<Edge> neighbours;
	String name;
	
	public String getName() {
		return name;
	}
	
	public List<Edge> getNeighbours() {
		return neighbours;
	}
	
	public Node(String name) {
		neighbours = new ArrayList<Edge>();
		this.name = name;
	}
	
	public boolean isEqual(Node n) {
		return n.name == this.name;
	}
	
	public void addEdge(Edge edge) {
		neighbours.add(edge);
	}
}
