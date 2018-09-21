package com.charlie.morerecipes.controllers;

import com.charlie.morerecipes.domain.Recipe;
import com.charlie.morerecipes.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
public class RecipeController {

    private final RecipeService recipeService;


    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping(value = "/recipe/{id}",method = RequestMethod.GET)
    public String getRecipeById(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findById(Integer.parseInt(id)));
        return "/recipe/show";
    }

    @RequestMapping(value="/recipe/{id}",method = RequestMethod.POST)
    public String addRecipe(@PathVariable String id, @RequestBody Object formInfo) {
        return "/recipe/show";
    }
}
