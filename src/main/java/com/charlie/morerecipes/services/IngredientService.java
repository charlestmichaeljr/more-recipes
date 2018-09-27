package com.charlie.morerecipes.services;

import com.charlie.morerecipes.commands.IngredientCommand;
import com.charlie.morerecipes.commands.RecipeCommand;
import com.charlie.morerecipes.domain.Ingredient;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId,Long ingredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand command);
    void deleteById(Long recipeId, Long ingredientId);
}
