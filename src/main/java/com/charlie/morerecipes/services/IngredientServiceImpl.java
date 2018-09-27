package com.charlie.morerecipes.services;

import com.charlie.morerecipes.commands.IngredientCommand;
import com.charlie.morerecipes.commands.RecipeCommand;
import com.charlie.morerecipes.converters.IngredientCommandToIngredient;
import com.charlie.morerecipes.converters.IngredientToIngredientCommand;
import com.charlie.morerecipes.converters.RecipeToRecipeCommand;
import com.charlie.morerecipes.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.charlie.morerecipes.domain.Ingredient;
import com.charlie.morerecipes.domain.Recipe;
import com.charlie.morerecipes.repositories.RecipeRepository;
import com.charlie.morerecipes.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    RecipeRepository recipeRepository;
    IngredientToIngredientCommand ingredientToIngredientCommand;
    IngredientCommandToIngredient ingredientCommandToIngredient;
    UnitOfMeasureRepository unitOfMeasureRepository;
    RecipeToRecipeCommand recipeToRecipeCommand;

    public IngredientServiceImpl(RecipeRepository recipeRepository,
                                 IngredientToIngredientCommand ingredientToIngredientCommand,
                                 IngredientCommandToIngredient ingredientCommandToIngredient,
                                 UnitOfMeasureRepository unitOfMeasureRepository,
                                 RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
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

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {

        Optional<Recipe> recipeOptional  = recipeRepository.findById(command.getRecipeId());
        Recipe returnedRecipe = null;

        if(!recipeOptional.isPresent()) {
            log.error("Recipe not found");
        }
        else {
            returnedRecipe = recipeOptional.get();
            Optional<Ingredient> ingredientOptional = returnedRecipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId())).findFirst();

            if(ingredientOptional.isPresent()) {
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(command.getDescription());
                ingredientFound.setAmount(command.getAmount());
                ingredientFound.setUom(unitOfMeasureRepository.findById(command.getUnitOfMeasure().getId())
                        .orElseThrow(()->new RuntimeException("Unit Of Measure Not Found")));
            }
            else {
                Ingredient ingredient = ingredientCommandToIngredient.convert(command);
                ingredient.setRecipe(returnedRecipe);
                returnedRecipe.addIngredient(ingredientCommandToIngredient.convert(command));

            }
        }
        Recipe savedRecipe = recipeRepository.save(returnedRecipe);

        Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(command.getId())).findFirst();

        if(!savedIngredientOptional.isPresent()) {
            savedIngredientOptional = savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients ->recipeIngredients.getDescription().equals(command.getDescription()))
                    .filter(recipeIngredients -> recipeIngredients.getAmount().equals(command.getAmount()))
                    .filter(recipeIngredients -> recipeIngredients.getUom().getId().equals(command.getUnitOfMeasure().getId()))
                    .findFirst();
        }

        return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
    }

    @Override
    public void deleteById(Long recipeId,Long ingredientId) {
        Optional<Recipe> optionalRecipe =  recipeRepository.findById(recipeId);
        Recipe recipe = null;
        Recipe savedRecipe = null;

        if(!optionalRecipe.isPresent()) {
            log.error("Recipe not found!!");
        }
        else {
            recipe = optionalRecipe.get();
            Optional<Ingredient> optionalIngredient = recipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientId))
                    .findFirst();

            if(!optionalIngredient.isPresent()) {
                log.error("Ingredient not found");
            }
            else
            {
                Ingredient ingredientToDelete = optionalIngredient.get();
                ingredientToDelete.setRecipe(null);
                recipe.getIngredients().remove(ingredientToDelete);
                savedRecipe = recipeRepository.save(recipe);
            }
        }
    }
}
