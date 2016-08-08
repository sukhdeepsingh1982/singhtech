package com.singhtech.command;

import com.singhtech.model.Path;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by sukhdeepsingh1982 on 7/08/2016.
 */
@Component(value = "tripDistanceCommand" )
public class TripDistanceCommand extends BaseTripCommand {

    static Map<String, Operation> operatorsMap;
    static {
        operatorsMap = new TreeMap<String, Operation>();
        operatorsMap.put(">", new Operation() {
            public boolean evaluate(Path path, int y) {
                return path.getDistance() > y;
            }
        });
        operatorsMap.put("<", new Operation() {
            public boolean evaluate(Path path, int y) {
                return path.getDistance() < y;
            }
        });
        operatorsMap.put("=", new Operation() {
            public boolean evaluate(Path path, int y) {
                return path.getDistance() == y;
            }
        });
    }

    Operation getOperation(String operator) {
        return operatorsMap.get(operator);
    }
}
