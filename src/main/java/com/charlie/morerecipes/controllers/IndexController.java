package com.charlie.morerecipes.controllers;

import com.charlie.morerecipes.domain.Difficulty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"","/","/index"})
    public String getIndexPage() {
        System.out.println("Some stuff");
        return "index";
    }
}
