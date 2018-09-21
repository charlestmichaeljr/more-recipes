package com.charlie.morerecipes.converters;

import com.charlie.morerecipes.commands.UnitOfMeasureCommand;
import com.charlie.morerecipes.domain.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;

public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand,UnitOfMeasure> {

    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {
        if(source == null) {
            return null;
        }

        final UnitOfMeasure unitOfMeasure = new UnitOfMeasure();

        unitOfMeasure.setDescription(source.getDescription());
        unitOfMeasure.setId(source.getId());

        return unitOfMeasure;
    }
}
