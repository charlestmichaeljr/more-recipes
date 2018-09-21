package com.charlie.morerecipes.converters;

import com.charlie.morerecipes.commands.IngredientCommand;
import com.charlie.morerecipes.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand converter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand converter) {
        this.converter = converter;
    }

    @Override
    public IngredientCommand convert(Ingredient source) {
        if(source == null) {
            return null;
        }

        IngredientCommand command = new IngredientCommand();
        command.setAmount(source.getAmount());
        command.setDescription(source.getDescription());
        command.setId(source.getId());
        command.setUnitOfMeasure(converter.convert(source.getUom()));

        return command;
    }
}
