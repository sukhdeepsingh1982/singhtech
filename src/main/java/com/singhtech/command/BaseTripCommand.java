package com.singhtech.command;

import com.singhtech.TrainTestUtil;
import com.singhtech.model.Graph;
import com.singhtech.model.Node;
import com.singhtech.model.Path;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * This command is base command for trip related command, it
 * will calculate the all possible path between the given nodes
 * which satisfy the given condition e.i distance or stops. It performs a recursive tree search starting
 * on the first node, to find all possible paths that end at the target node
 *
 * Created by sukhdeepsingh1982 on 05/08/2016.
 */
public abstract class BaseTripCommand implements Command {

    /**
     * Calculate the number route for the given nodes which satisfy the condition.
     *
     *  @param arguments thake array to 2 argument one the condition and other is
     *                   node from which the route need to be calculated eg. <5 ABC.
     * */

    public String execute(Graph graph, String... arguments) {

        if (arguments.length < 2) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        String condition = arguments[0];
        String nodesSpec = arguments[1];

        String operator  = condition.substring(0,1);
        int value        = Integer.parseInt(condition.substring(1));

        List<Node> nodes     = TrainTestUtil.getNodes(graph, nodesSpec);

        if (nodes.size() != 2) {
            throw new IllegalArgumentException("must specify source and target node");
        }

        Node start = nodes.get(0);
        Node target = nodes.get(1);


        Condition filterCond = new Condition(value, getOperation(operator));
        Condition runCond = filterCond;

        // when the filtering condition is to find all paths that match
        // a given value, the test runs so long as the number of hops
        // of the distance is still less than the value we are searching for
        if (operator.equals("=")) {
            runCond = new Condition(value + 1, getOperation("<"));
        }

        List<Path> paths = TrainTestUtil.findPaths(graph, start, target, new Path(0,0), runCond, filterCond);

        return String.valueOf(paths.size());
    }

    /**
     * @param operator possible value "<", ">" or "="
     * @return #Operation object for the given operator
     * */
    abstract Operation getOperation(String operator);
}
