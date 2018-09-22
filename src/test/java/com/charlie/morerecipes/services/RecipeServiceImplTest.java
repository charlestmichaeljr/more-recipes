package com.charlie.morerecipes.services;

import com.charlie.morerecipes.converters.RecipeCommandToRecipe;
import com.charlie.morerecipes.converters.RecipeToRecipeCommand;
import com.charlie.morerecipes.domain.Recipe;
import com.charlie.morerecipes.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipes() throws Exception{
        Recipe recipe = new Recipe();
        recipe.setDescription("Crispy Tacos");
        HashSet recipesData = new HashSet();

        recipesData.add(recipe);

        when(recipeService.getRecipes()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeService.getRecipes();
        assertEquals(1,recipes.size());
        verify(recipeRepository,times(1)).findAll();
        assertEquals("Crispy Tacos", recipes.iterator().next().getDescription());
    }

    public void getRecipeByIdTest() throws Exception{
        Recipe recipe = new Recipe();
        recipe.setDescription("Crispy Tacos");
        Optional<Recipe> optionalRecipe = Optional.of(recipe);

        when(recipeService.findById(anyInt())).thenReturn(recipe);

        Recipe recipeReturned = recipeService.findById(1);

        assertNotNull(recipeReturned);
        verify(recipeRepository,times(1)).findById(1);
    }
}