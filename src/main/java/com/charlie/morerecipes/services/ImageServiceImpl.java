package com.charlie.morerecipes.services;

import com.charlie.morerecipes.domain.Recipe;
import com.charlie.morerecipes.repositories.RecipeRepository;
import javassist.bytecode.ByteArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImageFile(Long recipeId, MultipartFile file) throws Exception {

        Recipe recipe = recipeRepository.findById(recipeId).get();
        Recipe savedRecipe = null;

        try {
            Byte[] byteObject = new Byte[file.getBytes().length];
            Integer i = 0;
            for (byte b : file.getBytes()) {
                byteObject[i++] = b;
            }

            recipe.setImage(byteObject);
            savedRecipe = recipeRepository.save(recipe);
        } catch (IOException e) {
            log.error("Error occurred: " + e);
            e.printStackTrace();
        }
    }
}
