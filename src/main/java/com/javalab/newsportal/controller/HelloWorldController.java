package com.javalab.newsportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {

    @RequestMapping("/welcome")
    public String helloWorld(Model model) {
        String message = "Hello World, Spring MVC Tutorial. This message is coming from HelloWorldController.java";
        model.addAttribute("message", message);
        return "welcome";
    }

    @RequestMapping("/")
    public String welcome(){
        return "index";
    }
}
