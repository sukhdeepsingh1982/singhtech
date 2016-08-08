package com.singhtech;

import com.singhtech.command.Condition;
import com.singhtech.command.Operation;
import com.singhtech.model.Edge;
import com.singhtech.model.Graph;
import com.singhtech.model.Node;
import com.singhtech.model.Path;

import java.util.*;

/**
 * Created by sukhdeepsingh1982 on 05/08/2016.
 */
public  final class TrainTestUtil {

    public static List<Node> getNodes(Graph graph, String s) {
        List<Node> nodes = new ArrayList();
        int i = 0;
        for (char c: s.toCharArray()) {
            String name = String.valueOf(c);
            nodes.add(graph.getNode(name));
        }
        return nodes;
    }

    public static List<Path> findPaths(Graph graph, Node current, Node target, Path ancestor, Condition runCondition, Condition filterCondition) {
        ArrayList<Path> paths = new ArrayList<Path>();

        // For all neighbours directly reachable from the current node...
        for (Edge edge: current.getNeighbours()) {
            Node destination = edge.getDestination();
            int distance = edge.getDistance();
            Path newPath = new Path(ancestor.getDistance() + distance, ancestor.getStops() + 1);

            // if the path reaching this neighbour means the running condition is no longer
            // satisfied, this search branch should be aborted
            if (!runCondition.evaluate(newPath)) {
                continue;
            }

            // if this neighbour is the target we are looking for...
            if (destination.isEqual(target)) {
                // if no filter was specified *or* the filter selects this path,
                // then it is added to the current list of results
                if (filterCondition == null || filterCondition.evaluate(newPath)) {
                    paths.add(newPath);
                }


            }

            // Look recursively for all paths that can be found via this neighbour
            for (Path path: findPaths(graph, destination, target, newPath, runCondition, filterCondition)) {
                paths.add(path);
            }
        }
        return paths;
    }

    /**
     * Performs a complete run of the Dijkstra algorithm to calculate the shortest
     * possible route between a start and target node
     * @param graph
     * @param start
     * @param target
     * @return
     */
    public static int dijkstra(Graph graph, Node start, Node target) {
        Set<Node> unVistedSet = new HashSet<Node>();
        Set<Node> vistedSet = new HashSet<Node>();

        Node[] nodes = graph.getNodes();
        Map<String, Integer> costMap = new TreeMap<String, Integer>();

        // Start by setting the cost from all nodes to the start node to
        // infinity
        for (Node node: nodes) {
            costMap.put(node.getName(), Integer.MAX_VALUE);
        }

        // move start node to the list of unvisited nodes and set the
        // distance to 0 (i.e. the distance from the start node to itself)
        unVistedSet.add(start);
        costMap.put(start.getName(), 0);

        // while the list of unvisited nodes is not empty...
        while(!unVistedSet.isEmpty()) {

            // find the node in the unvisited list with the
            // smallest known cost to the source so far (i.e. the
            // "nearest" node
            Node nearest=null;
            int minCost = Integer.MAX_VALUE;
            for(Node n : unVistedSet) {
                Integer cost = costMap.get(n.getName());
                if ( cost < minCost) {
                    nearest = n;
                    minCost = cost ;
                }
            }

            // move the nearest node to the list of visited nodes
            unVistedSet.remove(nearest);
            vistedSet.add(nearest);

            // for all neighbours of the current node...
            for (Edge edge : nearest.getNeighbours()) {
                // which haven't been visited...
                if (!vistedSet.contains(edge.getDestination())) {
                    // find the ones for which we find a better (smallest) cost
                    // to the source than the one we had previously calculated for it...
                    int newDistance = costMap.get(nearest.getName()) + edge.getDistance();
                    Integer dist = costMap.get(edge.getDestination().getName());
                    if (newDistance < dist) {
                        // save the new shortest distance for this neighbour
                        // and move it to the list of unvisited nodes.
                        costMap.put(edge.getDestination().getName(), newDistance);
                        unVistedSet.add(edge.getDestination());
                    }
                }
            }

            // if the target node has already been visited, we can stop searching
            if (vistedSet.contains(target)) {
                break;
            }
        }
        // returns the shortest distance recorded from the target node to the start node
        return costMap.get(target.getName());
    }
}
