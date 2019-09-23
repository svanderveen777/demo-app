package com.sre.demo.demoapp.controller;

import com.sre.demo.demoapp.model.Hello;
import com.sre.demo.demoapp.service.DemoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo")
public class DemoController {

    final DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping(path = "/hello")
    public Hello hello(@RequestParam(name = "name", required = false) String name) {
        return demoService.hello(name);
    }
}
