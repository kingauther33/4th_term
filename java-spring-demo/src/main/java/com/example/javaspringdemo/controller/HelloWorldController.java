package com.example.javaspringdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("hello")
    public String sayHello(){
        return "Hello world!";
    }

    @GetMapping("goodbye")
    public String sayGoodbye(){
        return "Goodbye!";
    }

    @GetMapping("demo")
    public String demo(){
        return "Demo Spring!";
    }
}
