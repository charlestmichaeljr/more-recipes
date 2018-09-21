package com.charlie.morerecipes.converters;

import com.charlie.morerecipes.commands.IngredientCommand;
import com.charlie.morerecipes.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;

public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasure converter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure converter) {
        this.converter = converter;
    }

    @Override
    public Ingredient convert(IngredientCommand source) {
        if(source == null) {
            return null;
        }

        Ingredient ingredient = new Ingredient();
        ingredient.setAmount(source.getAmount());
        ingredient.setDescription(source.getDescription());
        ingredient.setId(source.getId());
        ingredient.setUom(converter.convert(source.getUnitOfMeasure()));

        return ingredient;
    }
}
