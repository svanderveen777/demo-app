package com.sre.demo.demoapp.service;

import java.util.ArrayList;
import java.util.List;

public class StaticThingy {

    public static List<Double> list = new ArrayList<>();

    public void populateList(boolean flush) {
        if (flush) {
            list = new ArrayList<>();
        } else {
            for (int i = 0; i < 10000000; i++) {
                list.add(Math.random());
            }
        }
    }
}