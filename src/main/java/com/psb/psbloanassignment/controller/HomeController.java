package com.psb.psbloanassignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home() {
        return "redirect:/api/products";
    }

    // add request mapping for /leaders
    @GetMapping("/leaders")
    public String showLeaders() {

        return "leaders";
    }

    // add request mapping for /systems
    @GetMapping("/systems")
    public String showSystems() {

        return "systems";
    }
}
