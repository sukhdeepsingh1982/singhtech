package com.singhtech.command;

import com.singhtech.TrainTestUtil;
import com.singhtech.model.Edge;
import com.singhtech.model.Graph;
import com.singhtech.model.Node;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This command will  the path recording the distance travelled.
 * If the path specified is not possible, the result is
 * "NO SUCH ROUTE".
 *
 * Created by sukhdeepsingh1982 on 6/08/2016.
 */
@Component(value = "routeDistanceCommand")
public class RouteDistanceCommand implements Command {

    /**
     *Follows the path recording the distance travelled.
     * If the path specified is not possible, the result is
     * "NO SUCH ROUTE".
     *
     * @param arguments only take single argument which
     *                  specify the nodes to travel e.g "ABC"
     *
     * */
    public String execute(Graph graph, String... arguments) {

        if (arguments.length < 1) {
            throw new IllegalArgumentException("Invalid command arguments" + arguments);
        }

        String nodesSpec = arguments[0];

        List<Node> nodes = TrainTestUtil.getNodes(graph, nodesSpec);
        int distance = 0;

        // start from the first node
        Node lastNode = nodes.get(0);

        // iterate from the second node onwards...
        for (int i = 1; i < nodes.size(); ++i) {
            Node node = nodes.get(i);
            boolean routeFound = false;
            // find out if there is indeed a path from
            // the previous node visited to this one...
            for (Edge neighbour: lastNode.getNeighbours()) {
                if (neighbour.getDestination().isEqual(node)) {
                    //found a path
                    distance += neighbour.getDistance();
                    routeFound = true;
                    break;
                }
            }
            if (!routeFound) {
                // route doesn't exist. Distance is infinity
                distance = Integer.MAX_VALUE;
                break;
            }
            lastNode = node;
        }

        if (distance == Integer.MAX_VALUE) {
            return "NO ROUTE";
        }
        return String.valueOf(distance);
    }

}
