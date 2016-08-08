package com.singhtech.command;

import com.singhtech.model.Path;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeMap;

/**
 * This command will calculate the all possible path between the given nodes
 * which satisfy the given stop condition condition .
 *
 *
 * Created by sukhdeepsingh1982 on 5/08/2016.
 */

@Component(value = "tripStopCommand")
public class TripStopCommand extends  BaseTripCommand {

    static Map<String, Operation> operatorsMap;

    static {
        operatorsMap = new TreeMap<String, Operation>();
        operatorsMap.put(">", new Operation() {
            public boolean evaluate(Path path, int y) {
                return path.getStops() > y;
            }
        });
        operatorsMap.put("<", new Operation() {
            public boolean evaluate(Path path, int y) {
                return path.getStops() < y;
            }
        });
        operatorsMap.put("=", new Operation() {
            public boolean evaluate(Path path, int y) {
                return path.getStops() == y;
            }
        });
    }

    Operation getOperation(String operator) {
        return operatorsMap.get(operator);
    }
}
