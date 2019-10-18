package com.fullstack.springbootlibrarycatalogmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CatalogueController {
    @GetMapping("/")
    public String list(){
        return "catalogue";
    }
}