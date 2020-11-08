package com.psb.psbloanassignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String showMyLoginPage(Model model) {
        return "fancy-login";
    }


    // add request mapping for /access-denied
    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }
}
