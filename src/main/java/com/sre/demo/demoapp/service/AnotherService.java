package com.sre.demo.demoapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AnotherService {

    @Value("${demo.monkey.key:steve}")
    String key;
    static final StaticThingy staticThingy;

    static {
       staticThingy = new StaticThingy();
    }

    public void process(String name) {
        if(name != null && name.equalsIgnoreCase(key)) {
            staticThingy.populateList(false);
        } else {
           staticThingy.populateList(true);;
        }
    }
}