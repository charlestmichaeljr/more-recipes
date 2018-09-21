package com.charlie.morerecipes.converters;

import com.charlie.morerecipes.commands.CategoryCommand;
import com.charlie.morerecipes.commands.IngredientCommand;
import com.charlie.morerecipes.commands.NotesCommand;
import com.charlie.morerecipes.commands.RecipeCommand;
import com.charlie.morerecipes.domain.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {

    RecipeToRecipeCommand recipeConverter;

    public static final Integer RECIPE_ID = 1;
    public static final Integer COOK_TIME = Integer.valueOf("5");
    public static final Integer PREP_TIME = Integer.valueOf("7");
    public static final String DESCRIPTION = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS = Integer.valueOf("3");
    public static final String SOURCE = "Source";
    public static final String URL = "Some URL";
    public static final Integer CAT_ID_1 = 1;
    public static final Integer CAT_ID2 = 2;
    public static final Integer INGRED_ID_1 = 3;
    public static final Integer INGRED_ID_2 = 4;
    public static final Integer NOTES_ID = 9;

    @Before
    public void setUp() throws Exception {
        recipeConverter = new RecipeToRecipeCommand(new NotesToNotesCommand(),new CategoryToCategoryCommand(),
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()));
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(recipeConverter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(recipeConverter.convert(new Recipe()));
    }

    @Test
    public void convert() {
        // given
        Recipe recipe = new Recipe();

        Ingredient ingredient1 = new Ingredient();
        Ingredient ingredient2 = new Ingredient();

        ingredient1.setId(INGRED_ID_1);
        recipe.getIngredients().add(ingredient1);

        ingredient2.setId(INGRED_ID_2);
        recipe.getIngredients().add(ingredient2);

        Category category1 = new Category();
        Category category2 = new Category();

        category1.setId(CAT_ID_1);
        category2.setId(CAT_ID2);

        recipe.getCategories().add(category1);
        recipe.getCategories().add(category2);

        recipe.setImage(new Byte[]{});
        recipe.setDifficulty(DIFFICULTY);
        recipe.setCookTime(COOK_TIME);
        recipe.setDescription(DESCRIPTION);
        recipe.setId(RECIPE_ID);
        recipe.setPrepTime(PREP_TIME);
        recipe.setDirections(DIRECTIONS);
        recipe.setSource(SOURCE);


        Notes notes = new Notes();
        notes.setId(NOTES_ID);
        recipe.setNotes(notes);

        recipe.setServings(SERVINGS);
        recipe.setUrl(URL);
        // when
        RecipeCommand command = recipeConverter.convert(recipe);
        // then
        assertNotNull(recipe);
        assertNotNull(recipe.getIngredients());
        assertNotNull(recipe.getCategories());
        assertNotNull(recipe.getNotes());
        assertEquals(2,recipe.getIngredients().size());
        assertEquals(2,recipe.getCategories().size());
        assertEquals(Difficulty.EASY,recipe.getDifficulty());
        assertEquals(COOK_TIME,recipe.getCookTime());
        assertEquals(DESCRIPTION,recipe.getDescription());
        assertEquals(RECIPE_ID,recipe.getId());
        assertEquals(PREP_TIME,recipe.getPrepTime());
        assertEquals(NOTES_ID,recipe.getNotes().getId());
        assertEquals(SERVINGS,recipe.getServings());
        assertEquals(DIRECTIONS,recipe.getDirections());
        assertEquals(SOURCE,recipe.getSource());
        assertEquals(URL, recipe.getUrl());
    }
}