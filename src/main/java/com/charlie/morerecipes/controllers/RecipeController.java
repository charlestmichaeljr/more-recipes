package com.charlie.morerecipes.controllers;

import com.charlie.morerecipes.commands.RecipeCommand;
import com.charlie.morerecipes.exceptions.NotFoundException;
import com.charlie.morerecipes.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    private static final String RECIPE_RECIPEFORM_URL = "/recipe/recipeform";


    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @RequestMapping(value = "/recipe/{id}/show")
    public String getRecipeById(@PathVariable String id, Model model) {
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(id));
        model.addAttribute("recipe", recipeCommand);
        return "/recipe/show";
    }

    @RequestMapping("/recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe",new RecipeCommand());
        return RECIPE_RECIPEFORM_URL;
    }

    @RequestMapping(value="/recipe",method = RequestMethod.POST)
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand recipeCommand, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> {log.debug(objectError.toString());});

            return RECIPE_RECIPEFORM_URL;
        }

        RecipeCommand savedRecipe = recipeService.saveRecipeCommand(recipeCommand);


        return "redirect:/recipe/" + savedRecipe.getId() + "/show" ;
    }

    @RequestMapping("/recipe/{id}/update")
    public String getRecipeUpdateForm(@PathVariable String id,Model model) throws IOException {
        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(id)));
        return  "/recipe/recipeform";
    }

    @RequestMapping("/recipe/{id}/delete")
    public String deleteRecipeById(@PathVariable String id) {
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception e) {
        log.error("Handling not found error");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModel().put("errorMessage",e.getMessage());
        modelAndView.setViewName("404error");

        return modelAndView;

    }
}
