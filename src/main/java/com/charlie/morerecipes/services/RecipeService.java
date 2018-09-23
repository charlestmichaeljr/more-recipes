package com.charlie.morerecipes.services;

import com.charlie.morerecipes.commands.RecipeCommand;
import com.charlie.morerecipes.domain.Recipe;

import java.util.Optional;
import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    RecipeCommand findCommandById(Integer id);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
}
