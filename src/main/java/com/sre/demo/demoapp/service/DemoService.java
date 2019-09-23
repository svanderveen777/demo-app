package com.sre.demo.demoapp.service;

import java.util.Date;

import com.sre.demo.demoapp.model.Hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    final AnotherService anotherService;

    public DemoService(AnotherService anotherService) {
        this.anotherService = anotherService;
    }

    public Hello hello(String name) {
        log.info("Calling DemoService.hello()");
        Hello hello = new Hello();
        hello.setDate(new Date());
        if (name != null) {
            hello.setMessage(String.format("Hello, %s!", name));
        } else {
            hello.setMessage("Hello, World!");
        }
        log.info("Returning: {}", hello.toString());
        anotherService.process(name);
        return hello;
    }



}