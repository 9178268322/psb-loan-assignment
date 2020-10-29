package com.psb.psbloanassignment.controller;

import com.psb.psbloanassignment.model.User;
import com.psb.psbloanassignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/registration")
    public String register() {
        return "registration";
    }

    @PostMapping("/userLogin")
    public String loginUser(@ModelAttribute("username") String username, @ModelAttribute("password") String password) {
        User user = userService.findByUsername(username);
        if (user != null) {
            return "productPage";
        }
        return "userError";
    }

    @PostMapping("/newUser")
    public String newUserPost(@ModelAttribute("email") String userEmail,
                @ModelAttribute("username") String username,
                @ModelAttribute("password") String password,
                Model model) throws Exception {

            model.addAttribute("email", userEmail);
            model.addAttribute("username", username);
            model.addAttribute("password", password);


            User user = new User();
            user.setUsername(username);
            user.setEmail(userEmail);
            user.setPassword(password);

            userService.createUser(user);

            return "login";
    }


}
