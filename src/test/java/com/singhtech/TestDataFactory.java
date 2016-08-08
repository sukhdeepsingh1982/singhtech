package com.singhtech;

import com.singhtech.model.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sukhdeepsingh1982 on 7/08/2016.
 */
public class TestDataFactory {

    public static Graph getGrap(){
        List<String> nodeSpecs = new ArrayList<String>();
        nodeSpecs.add("AB5");
        nodeSpecs.add("BC4");
        nodeSpecs.add("CD8");
        nodeSpecs.add("DC8");
        nodeSpecs.add("DE6");
        nodeSpecs.add("AD5");
        nodeSpecs.add("CE2");
        nodeSpecs.add("EB3");
        nodeSpecs.add("AE7");
        return new Graph(nodeSpecs);
    }
}
