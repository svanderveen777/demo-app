package com.sre.demo.demoapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AnotherService {

    @Value("${demo.monkey.key:steve}")
    String key;

    public void process(String name) {
        if(name != null && name.equalsIgnoreCase(key)) {
            new StaticThingy().populateList();
        } else {
            new StaticThingy().clearList();
        }
    }
}