package com.charlie.morerecipes.controllers;

import com.charlie.morerecipes.domain.Category;
import com.charlie.morerecipes.domain.Difficulty;
import com.charlie.morerecipes.domain.Recipe;
import com.charlie.morerecipes.domain.UnitOfMeasure;
import com.charlie.morerecipes.repositories.CategoryRepository;
import com.charlie.morerecipes.repositories.UnitOfMeasureRepository;
import com.charlie.morerecipes.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.Set;

@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","/index"})
    public String getIndexPage(Model model) {

        Set<Recipe> recipeSet = recipeService.getRecipes();

        model.addAttribute("recipes",recipeSet);

        return "index";
    }
}
