package com.charlie.morerecipes.services;

import com.charlie.morerecipes.commands.RecipeCommand;
import com.charlie.morerecipes.converters.RecipeCommandToRecipe;
import com.charlie.morerecipes.converters.RecipeToRecipeCommand;
import com.charlie.morerecipes.domain.Recipe;
import com.charlie.morerecipes.repositories.RecipeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceIntegrationTest {

    public static final String NEW_DESCRIPTION = "New Description";

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Autowired
    RecipeRepository recipeRepository;

    @Transactional
    @Test
    public void testSaveOfDescription() throws Exception {

        // given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

        // when
        testRecipeCommand.setDescription(NEW_DESCRIPTION);
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);
        // then
        assertEquals(NEW_DESCRIPTION,savedRecipeCommand.getDescription());
        assertEquals(testRecipe.getId(),savedRecipeCommand.getId());
        assertEquals(testRecipe.getCategories().size(),savedRecipeCommand.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(),savedRecipeCommand.getIngredients().size());


    }

}
