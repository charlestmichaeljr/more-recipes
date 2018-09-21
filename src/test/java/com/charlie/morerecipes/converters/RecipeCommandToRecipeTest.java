package com.charlie.morerecipes.converters;

import com.charlie.morerecipes.commands.CategoryCommand;
import com.charlie.morerecipes.commands.IngredientCommand;
import com.charlie.morerecipes.commands.NotesCommand;
import com.charlie.morerecipes.commands.RecipeCommand;
import com.charlie.morerecipes.domain.Difficulty;
import com.charlie.morerecipes.domain.Ingredient;
import com.charlie.morerecipes.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {

    private RecipeCommandToRecipe recipeConverter;

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

        recipeConverter = new RecipeCommandToRecipe(new NotesCommandToNotes(),new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()));
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(recipeConverter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(recipeConverter.convert(new RecipeCommand()));
    }

    @Test
    public void convert() {

        // given
        RecipeCommand command = new RecipeCommand();

        IngredientCommand ingredient1 = new IngredientCommand();
        IngredientCommand ingredient2 = new IngredientCommand();

        ingredient1.setId(INGRED_ID_1);
        command.getIngredients().add(ingredient1);

        ingredient2.setId(INGRED_ID_2);
        command.getIngredients().add(ingredient2);

        CategoryCommand category1 = new CategoryCommand();
        CategoryCommand category2 = new CategoryCommand();

        category1.setId(CAT_ID_1);
        category2.setId(CAT_ID2);

        command.getCategories().add(category1);
        command.getCategories().add(category2);

        command.setImage(new Byte[]{});
        command.setDifficulty(DIFFICULTY);
        command.setCookTime(COOK_TIME);
        command.setDescription(DESCRIPTION);
        command.setId(RECIPE_ID);
        command.setPrepTime(PREP_TIME);
        command.setDirections(DIRECTIONS);
        command.setSource(SOURCE);


        NotesCommand notes = new NotesCommand();
        notes.setId(NOTES_ID);
        command.setNotes(notes);

        command.setServings(SERVINGS);
        command.setUrl(URL);
        // when
        Recipe recipe = recipeConverter.convert(command);
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