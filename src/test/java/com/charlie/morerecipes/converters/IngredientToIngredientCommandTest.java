package com.charlie.morerecipes.converters;

import com.charlie.morerecipes.commands.IngredientCommand;
import com.charlie.morerecipes.domain.Ingredient;
import com.charlie.morerecipes.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {

    public static final Long INTEGER_VALUE = 1L;
    public static final BigDecimal BIG_DECIMAL_VALUE = BigDecimal.valueOf(30l);
    public static final String DESCRIPTION = "description";

    IngredientToIngredientCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testForNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testForEmptyObject() {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    public void convert() {

        // given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(INTEGER_VALUE);
        uom.setDescription(DESCRIPTION);

        Ingredient ingredient = new Ingredient();
        ingredient.setAmount(BIG_DECIMAL_VALUE);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setId(INTEGER_VALUE);
        ingredient.setUom(uom);

        // when
        IngredientCommand command = converter.convert(ingredient);
        // then
        assertNotNull(command);
        assertNotNull(command.getUnitOfMeasure());
        assertEquals(INTEGER_VALUE,command.getId());
        assertEquals(DESCRIPTION,command.getDescription());
        assertEquals(BIG_DECIMAL_VALUE, command.getAmount());
        assertEquals(INTEGER_VALUE,command.getUnitOfMeasure().getId());

    }

    @Test
    public void convertWithNullUom() {

        // given
        Ingredient ingredient = new Ingredient();
        ingredient.setAmount(BIG_DECIMAL_VALUE);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setId(INTEGER_VALUE);
        ingredient.setUom(null);

        // when
        IngredientCommand command = converter.convert(ingredient);
        // then
        assertNotNull(command);
        assertNull(command.getUnitOfMeasure());
        assertEquals(INTEGER_VALUE,command.getId());
        assertEquals(DESCRIPTION,command.getDescription());
        assertEquals(BIG_DECIMAL_VALUE, command.getAmount());
    }
}