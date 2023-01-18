package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.services.UserService;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping
    public String index(Model model){
        model.addAttribute("user", userService.getAll());
        // model.addAttribute("role", roleService.getIdByLevel()); // untuk debug
        return "user/index";
    }
}
