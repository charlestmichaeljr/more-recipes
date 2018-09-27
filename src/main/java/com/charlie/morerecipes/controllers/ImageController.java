package com.charlie.morerecipes.controllers;

import com.charlie.morerecipes.commands.RecipeCommand;
import com.charlie.morerecipes.services.ImageService;
import com.charlie.morerecipes.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
public class ImageController {

    ImageService imageService;

    RecipeService recipeService;

    public ImageController(ImageService imageService,RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @RequestMapping(value = "/recipe/{recipeId}/image",method = RequestMethod.POST)
    public String addRecipeImage(@PathVariable Long recipeId, @RequestParam("imagefile") MultipartFile file) {
        imageService.saveImageFile(recipeId,file);

        return "redirect:/recipe/" + recipeId + "/show";
    }

    @RequestMapping(value = "/recipe/{recipeId}/image",method = RequestMethod.GET)
    public String getAddRecipeImageForm(@PathVariable String recipeId, Model model) {
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));

        model.addAttribute("recipe",recipeCommand);

        return "recipe/imageuploadform";
    }
}
