package com.example.lab15.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @GetMapping("/")
    public String getHome(@RequestParam(name = "text",required = false,defaultValue = "hello")String text,
                          Model model){
        model.addAttribute("text",text);
        return "home";
    }
    @GetMapping("/about")
    public String getAbout(Model model){
        model.addAttribute("name","Dan");
        return "about";
    }

    @GetMapping("/contacts")
    public String getContacts(Model model){
        model.addAttribute("name","Contacts");
        return "contacts";
    }
}
