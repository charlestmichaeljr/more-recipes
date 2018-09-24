package com.charlie.morerecipes.controllers;

import com.charlie.morerecipes.commands.IngredientCommand;
import com.charlie.morerecipes.commands.RecipeCommand;
import com.charlie.morerecipes.services.IngredientService;
import com.charlie.morerecipes.services.RecipeService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IngredientController {

    RecipeService recipeService;
    IngredientService ingredientService;

    public IngredientController(RecipeService recipeService,IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model) {
        RecipeCommand returnedRecipe = recipeService.findCommandById(Long.valueOf(recipeId));
        model.addAttribute("recipe",returnedRecipe);
        return "/recipe/ingredient/list";
    }

    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}")
    public String showRecipeIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
        IngredientCommand ingredient =
                ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId),Long.valueOf(ingredientId));
        model.addAttribute("ingredient",ingredient);

        return "/recipe/ingredient/show";
    }
}
