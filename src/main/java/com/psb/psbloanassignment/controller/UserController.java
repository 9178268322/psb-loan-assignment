package com.psb.psbloanassignment.controller;

import com.psb.psbloanassignment.model.User;
import com.psb.psbloanassignment.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/showRegistrationForm")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "registration-form";
    }

    @PostMapping("/processRegistrationForm")
    public String newUserRegister(@ModelAttribute("user") User user, Model model) throws Exception {
            String userName = user.getUsername();
            LOGGER.info("Processing registration form for: " + userName);

            if (userService.findByUsername(userName) != null) {
                model.addAttribute("userNameExists", true);

                LOGGER.warn("User name already exists.");
                return "registration-form";
            }

            if (userService.findByEmail(user.getEmail()) != null) {
                model.addAttribute("emailExists", true);
                return "registration-form";
            }

            userService.save(user);

            LOGGER.info("Successfully created user: " + userName);

            return "registration-confirmation";
    }
}
