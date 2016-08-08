package com.singhtech.command;

import com.singhtech.TrainTestUtil;
import com.singhtech.model.Edge;
import com.singhtech.model.Graph;
import com.singhtech.model.Node;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This command will find the shotest path from source node to target
 * node using of the Dijkstra algorithm.
 *
 * Created by sukhdeepsingh1982 on 7/08/2016.
 */

@Component(value = "shortestPathCommand")
public class ShortestPathCommand implements Command {

    public String execute(Graph graph, String... arguments) {
        // second parameter contains the nodes specification
        if (arguments.length < 1) {
            throw new IllegalArgumentException("Invalid command");
        }

        int lowerCost = Integer.MAX_VALUE;
        String nodesSpec = arguments[0];
        List<Node> nodes = TrainTestUtil.getNodes(graph, nodesSpec);

        Node start = nodes.get(0);
        Node target = nodes.get(1);

        if (start.isEqual(target)) {
            for (Edge edge : start.getNeighbours()) {
                int cost = TrainTestUtil.dijkstra(graph, edge.getDestination(), target);
                if (cost != Integer.MAX_VALUE) {
                    cost += edge.getDistance();
                    if (cost < lowerCost) {
                        lowerCost = cost;
                    }
                }
            }
        } else {
            lowerCost = TrainTestUtil.dijkstra(graph, nodes.get(0), nodes.get(1));
        }

        if (lowerCost == Integer.MAX_VALUE) {
            return "NO ROUTE";
        }
        return String.valueOf(lowerCost);
    }
}
