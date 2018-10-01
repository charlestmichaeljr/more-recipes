package com.charlie.morerecipes.services;

import com.charlie.morerecipes.commands.RecipeCommand;
import com.charlie.morerecipes.domain.Recipe;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(Long id) throws Exception;
    RecipeCommand findCommandById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
    void deleteById(Long id);
}
