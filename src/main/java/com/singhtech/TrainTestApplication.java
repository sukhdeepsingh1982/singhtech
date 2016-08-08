package com.singhtech;

import com.singhtech.command.Command;
import com.singhtech.model.Graph;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * This is the main class to run the train test.
 * This class expect the command in following format:
 *
 *
 *  <p>
 *     1)  distance {nodes}:  calculates the distance for a given
 *                                     route. For example: "distance ADC"
 *  </p>
 *
 *   <p>
 *     2)  tripStop {cond} {nodes}: calculates the number of possible
 *                                 route  between two nodes which satisfy
 *                                  a given stop condition. Conditions are
 *                                  expressed with a combination of an
 *                                  operator and a value. i.e. ">5" or "=6".
 *                                  For example: "tripStop <10 EA" will calculate
 *                                  the number of trips between E and A which have
 .                                  have less than 10 stops.
 *  </p>
 *
 *  <p>
 *     3)  tripDistance {cond} {nodes}: calculates the number of possible
 *                                 route  between two nodes which satisfy
 *                                  a given distance condition. Conditions are
 *                                  expressed with a combination of an
 *                                  operator and a value. i.e. ">5" or "=6".
 *                                  For example: "tripDistance =10 EA" will calculate
 *                                  the number of trips between E and A which have
 *                                  distance equal to 10.
 *  </p>
 *
 *  <p>
 *     4)   shortest {nodes}:    finds the shortest distance between
 *                                         two nodes (i.e. "shortest AD")
 *  </p>
 *
 *
 * Created by sukhdeepsingh1982 on 7/08/2016.
 */
@Component
public class TrainTestApplication {

    private final String ROUTE_DISTANCE_CMD_STR = "distance";
    private final String SHORTEST_PATH_CMD_STR = "shortest";
    private final String TRIP_STOP_CMD_STR = "tripStop";
    private final String TRIP_DISTANCE_CMD_STR = "tripDistance";



    @Autowired
    @Qualifier(value = "routeDistanceCommand" )
    private Command routeDistanceCommand;

    @Autowired
    @Qualifier(value = "shortestPathCommand" )
    private Command shortestPathCommand;

    @Autowired
    @Qualifier(value = "tripDistanceCommand" )
    private Command tripDistanceCommand;

    @Autowired
    @Qualifier(value = "tripStopCommand" )
    private Command tripStopCommand;


    public String executeCommand(String commandStatment, Graph graph){
        String[] splitCmds = StringUtils.splitByWholeSeparator(commandStatment, null);
        String command = splitCmds[0];
        if (StringUtils.equalsIgnoreCase(ROUTE_DISTANCE_CMD_STR, command)){
            return routeDistanceCommand.execute(graph, splitCmds[1]);
        } else if  (StringUtils.equalsIgnoreCase(SHORTEST_PATH_CMD_STR, command)){
            return shortestPathCommand.execute(graph, splitCmds[1]);
        }else if  (StringUtils.equalsIgnoreCase(TRIP_STOP_CMD_STR, command)){
            return tripStopCommand.execute(graph, splitCmds[1], splitCmds[2]);
        }else if  (StringUtils.equalsIgnoreCase(TRIP_DISTANCE_CMD_STR, command)){
            return tripDistanceCommand.execute(graph, splitCmds[1], splitCmds[2]);
        } else  throw new IllegalArgumentException("Invalid command " + command);

    }




}
