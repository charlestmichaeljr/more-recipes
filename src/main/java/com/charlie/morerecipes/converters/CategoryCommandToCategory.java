package com.charlie.morerecipes.converters;

import com.charlie.morerecipes.commands.CategoryCommand;
import com.charlie.morerecipes.domain.Category;
import org.springframework.core.convert.converter.Converter;

public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {
    @Override
    public Category convert(CategoryCommand source) {
        if(source == null) {
            return null;
        }

        Category category = new Category();
        category.setId(source.getId());
        category.setDescription(source.getDescription());

        return category;
    }
}
