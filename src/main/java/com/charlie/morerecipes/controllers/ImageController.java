package com.charlie.morerecipes.controllers;

import com.charlie.morerecipes.commands.RecipeCommand;
import com.charlie.morerecipes.services.ImageService;
import com.charlie.morerecipes.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

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
    public String addRecipeImage(@PathVariable Long recipeId, @RequestParam("imagefile") MultipartFile file)
    throws Exception {

        imageService.saveImageFile(recipeId,file);

        return "redirect:/recipe/" + recipeId + "/show";
    }

    @RequestMapping(value = "/recipe/{recipeId}/image",method = RequestMethod.GET)
    public String getAddRecipeImageForm(@PathVariable String recipeId, Model model) throws IOException {
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));

        model.addAttribute("recipe",recipeCommand);

        return "recipe/imageuploadform";
    }

    @RequestMapping(value = "recipe/{recipeId}/recipeimage",method = RequestMethod.GET)
    public void getImageFromDB(@PathVariable String recipeId, HttpServletResponse response) throws Exception {
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));

        byte[] byteArray = null;
        if(recipeCommand.getImage() != null) {
            byteArray = new byte[recipeCommand.getImage().length];
            int i = 0;
            for(Byte b: recipeCommand.getImage()) {
                byteArray[i++] = b;
            }
        }
        else {
            byteArray = new byte[0];
        }

        response.setContentType("image/jpeg");
        InputStream is = new ByteArrayInputStream(byteArray);
        IOUtils.copy(is,response.getOutputStream());
    }
}
