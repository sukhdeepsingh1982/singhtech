package com.singhtech.model;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by sukhdeepsingh1982 on 05/08/2016.
 */
public class Graph {
    
    Map<String, Node> nodeMap = new TreeMap<String, Node>();



    public Node[] getNodes() {
    	return nodeMap.values().toArray(new Node[0]);
    }
    

    public Node getNode(String name) {
    	Node node = nodeMap.get(name);
    	if (node == null) {
    		node = new Node(name);
    		nodeMap.put(name, node);
    	}
    	return node;
    }

    /**
     * Create graph from list  of connections of the form XYZ,
     * where X and Y correspond to a source and target nodes
     * respectively and Z corresponds to the distance
     * or weight between the two nodes. For example "AB3".
     *
     * @param nodesSpec
     */
	public  Graph(List<String> nodesSpec) {
		for (String nodeSpec : nodesSpec) {
			if (StringUtils.isNotBlank(nodeSpec)) {
				// Must be at least 3 characters.. i.e. AB3
				if (nodeSpec.length() < 3) {
					throw new IllegalArgumentException("Invalid graph specification: " + nodeSpec);
				}
				String sourceNodeName = StringUtils.substring(nodeSpec,0,1);
				String targetNodeName = StringUtils.substring(nodeSpec,1,2);
				
				int distance;
				
				try {
					distance = Integer.parseInt(StringUtils.substring(nodeSpec,2));
				} catch(NumberFormatException e) {
					throw new IllegalArgumentException("Invalid graph specification: " + nodeSpec +
							": " + nodeSpec.substring(2) + " is not a valid integer");
				}
				Node source = getNode(sourceNodeName);
				Node target = getNode(targetNodeName);
				
				source.addEdge(new Edge(target, distance));
			}
		}
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (Map.Entry<String, Node> entry : nodeMap.entrySet()) {
			s.append(entry.getKey() + ":\n");
			for (Edge edge : entry.getValue().neighbours) {
				s.append("  -> " + edge + "\n");
			}
		}
		return s.toString();
	}
	
}
