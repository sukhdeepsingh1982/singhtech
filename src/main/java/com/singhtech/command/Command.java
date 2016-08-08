package com.singhtech.command;

import com.singhtech.model.Graph;

/**
 * Created by sukhdeepsingh1982 on 05/08/2016.
 */
public interface Command {

    public String execute(Graph graph, String... arguments );
}
