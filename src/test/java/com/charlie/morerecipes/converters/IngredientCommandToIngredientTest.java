package com.charlie.morerecipes.converters;

import com.charlie.morerecipes.commands.IngredientCommand;
import com.charlie.morerecipes.commands.UnitOfMeasureCommand;
import com.charlie.morerecipes.domain.Ingredient;
import com.charlie.morerecipes.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {

    public static final Long INTEGER_VALUE = 1L;
    public static final BigDecimal BIG_DECIMAL_VALUE = BigDecimal.valueOf(30l);
    public static final String DESCRIPTION = "description";

    IngredientCommandToIngredient ingredientConverter;


    @Before
    public void setUp() throws Exception {
        ingredientConverter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testForNull() {
        assertNull(ingredientConverter.convert(null));
    }

    @Test
    public void testForEmptyObject() {
        assertNotNull(ingredientConverter.convert(new IngredientCommand()));
    }

    @Test
    public void convert() {

        // given
        IngredientCommand  command = new IngredientCommand();
        command.setId(INTEGER_VALUE);
        command.setDescription(DESCRIPTION);
        command.setAmount(BIG_DECIMAL_VALUE);

        UnitOfMeasureCommand uom = new UnitOfMeasureCommand();
        uom.setDescription(DESCRIPTION);
        uom.setId(INTEGER_VALUE);

        command.setUnitOfMeasure(uom);

        // when
        Ingredient ingredient = ingredientConverter.convert(command);
        // then
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUom());
        assertEquals(INTEGER_VALUE,ingredient.getId());
        assertEquals(DESCRIPTION,ingredient.getDescription());
        assertEquals(BIG_DECIMAL_VALUE,ingredient.getAmount());
        assertEquals(INTEGER_VALUE,ingredient.getUom().getId());
    }

    @Test
    public void convertWithNullUom() {

        // given
        IngredientCommand  command = new IngredientCommand();
        command.setId(INTEGER_VALUE);
        command.setDescription(DESCRIPTION);
        command.setAmount(BIG_DECIMAL_VALUE);

        command.setUnitOfMeasure(null);

        // when
        Ingredient ingredient = ingredientConverter.convert(command);
        // then
        assertNotNull(ingredient);
        assertNull(ingredient.getUom());
        assertEquals(INTEGER_VALUE,ingredient.getId());
        assertEquals(DESCRIPTION,ingredient.getDescription());
        assertEquals(BIG_DECIMAL_VALUE,ingredient.getAmount());
    }
}