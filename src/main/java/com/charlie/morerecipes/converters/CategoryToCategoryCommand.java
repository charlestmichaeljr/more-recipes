package com.charlie.morerecipes.converters;

import com.charlie.morerecipes.commands.CategoryCommand;
import com.charlie.morerecipes.domain.Category;
import org.springframework.core.convert.converter.Converter;

public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {
    @Override
    public CategoryCommand convert(Category source) {
        if(source == null) {
            return null;
        }

        CategoryCommand command = new CategoryCommand();
        command.setDescription(source.getDescription());
        command.setId(source.getId());

        return command;
    }
}
