package com.charlie.morerecipes.controllers;

import com.charlie.morerecipes.commands.RecipeCommand;
import com.charlie.morerecipes.domain.Recipe;
import com.charlie.morerecipes.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class RecipeController {

    private final RecipeService recipeService;


    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping(value = "/recipe/{id}/show",method = RequestMethod.GET)
    public String getRecipeById(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(Integer.parseInt(id)));
        return "/recipe/show";
    }

    @RequestMapping("/recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe",new RecipeCommand());
        return "/recipe/recipeform";
    }

    @RequestMapping(value="/recipe",method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand) {
        RecipeCommand savedRecipe = recipeService.saveRecipeCommand(recipeCommand);

        return "redirect:/recipe/" + savedRecipe.getId() + "/show" ;
    }

    @RequestMapping("/recipe/{id}/update")
    public String getRecipeUpdateForm(@PathVariable String id,Model model) {
        model.addAttribute("recipe",recipeService.findCommandById(Integer.parseInt(id)));
        return  "/recipe/recipeform";
    }
}
