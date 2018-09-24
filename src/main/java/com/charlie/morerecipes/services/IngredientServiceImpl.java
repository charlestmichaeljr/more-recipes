package com.charlie.morerecipes.services;

import com.charlie.morerecipes.commands.IngredientCommand;
import com.charlie.morerecipes.commands.RecipeCommand;
import com.charlie.morerecipes.converters.IngredientToIngredientCommand;
import com.charlie.morerecipes.converters.RecipeToRecipeCommand;
import com.charlie.morerecipes.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.charlie.morerecipes.domain.Ingredient;
import com.charlie.morerecipes.domain.Recipe;
import com.charlie.morerecipes.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    RecipeRepository recipeRepository;
    IngredientToIngredientCommand ingredientToIngredientCommand;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()) {
            log.error("Recipe not found with ID " + recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> optionalIngredientCommand =
                recipe.getIngredients().stream()
                        .filter(ingredient -> ingredient.getId().equals(ingredientId))
                        .map(ingredient ->
                                ingredientToIngredientCommand.convert(ingredient))
                        .findFirst();
       

        if (!optionalIngredientCommand.isPresent()) {
            log.error("Ingredient not found");
        }

        return optionalIngredientCommand.get();


    }
}
