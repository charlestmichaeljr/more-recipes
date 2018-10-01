package com.charlie.morerecipes.controllers;

import com.charlie.morerecipes.commands.RecipeCommand;
import com.charlie.morerecipes.exceptions.NotFoundException;
import com.charlie.morerecipes.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;


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
        return "/recipe/recipeform";
    }

    @RequestMapping(value="/recipe",method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand) {
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormatError(Exception e) {
        log.error("Handling numberFormat exception");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("400error");
        modelAndView.addObject("errorMessage",e.getMessage());

        return modelAndView;
    }
}
