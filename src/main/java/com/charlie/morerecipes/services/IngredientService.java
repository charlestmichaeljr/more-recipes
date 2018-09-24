package com.charlie.morerecipes.services;

import com.charlie.morerecipes.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId,Long ingredientId);
}
