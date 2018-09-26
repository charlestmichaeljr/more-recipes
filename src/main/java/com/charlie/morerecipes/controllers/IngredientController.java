package com.charlie.morerecipes.controllers;

import com.charlie.morerecipes.commands.IngredientCommand;
import com.charlie.morerecipes.commands.RecipeCommand;
import com.charlie.morerecipes.commands.UnitOfMeasureCommand;
import com.charlie.morerecipes.services.IngredientService;
import com.charlie.morerecipes.services.RecipeService;
import com.charlie.morerecipes.services.UnitOfMeasureService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

@Slf4j
@Controller
public class IngredientController {

    RecipeService recipeService;
    IngredientService ingredientService;
    UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService,IngredientService ingredientService,UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model) {
        RecipeCommand returnedRecipe = recipeService.findCommandById(Long.valueOf(recipeId));
        model.addAttribute("recipe",returnedRecipe);
        return "/recipe/ingredient/list";
    }

    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showRecipeIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
        IngredientCommand ingredient =
                ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId),Long.valueOf(ingredientId));
        model.addAttribute("ingredient",ingredient);

        return "/recipe/ingredient/show";
    }

    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {

        IngredientCommand ingredient =
                ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId),Long.valueOf(ingredientId));
        model.addAttribute("ingredient",ingredient);

        Set<UnitOfMeasureCommand> uoms = unitOfMeasureService.listAllUoms();
        model.addAttribute("uomList",uoms);

        return "/recipe/ingredient/ingredientform";
    }

    @RequestMapping(value = "/recipe/{recipeId}/ingredient",method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute IngredientCommand command, @PathVariable Long recipeId, Model model) {
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }

    @RequestMapping(value = "/recipe/{recipeId}/ingredient/new",method = RequestMethod.GET)
    public String newRecipe(@PathVariable String recipeId, Model model) {
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));

        ingredientCommand.setUnitOfMeasure(new UnitOfMeasureCommand());
        model.addAttribute("ingredient",ingredientCommand);

        Set<UnitOfMeasureCommand> uoms = unitOfMeasureService.listAllUoms();
        model.addAttribute("uomList",uoms);

        return "/recipe/ingredient/ingredientform";
    }


}
