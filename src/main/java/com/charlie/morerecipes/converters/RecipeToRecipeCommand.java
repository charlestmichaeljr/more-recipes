package com.charlie.morerecipes.converters;

import com.charlie.morerecipes.commands.RecipeCommand;
import com.charlie.morerecipes.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final NotesToNotesCommand notesConverter;
    private final CategoryToCategoryCommand categoryConverter;
    private final IngredientToIngredientCommand ingredientConverter;

    public RecipeToRecipeCommand(NotesToNotesCommand notesConverter, CategoryToCategoryCommand categoryConverter, IngredientToIngredientCommand ingredientConverter) {
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Override
    public RecipeCommand convert(Recipe source) {
        if(source == null) {
            return null;
        }

        RecipeCommand command = new RecipeCommand();
        command.setId(source.getId());
        command.setDescription(source.getDescription());
        command.setUrl(source.getUrl());
        command.setSource(source.getSource());
        command.setServings(source.getServings());
        command.setPrepTime(source.getPrepTime());
        command.setCookTime(source.getCookTime());
        command.setNotes(notesConverter.convert(source.getNotes()));
        command.setDifficulty(source.getDifficulty());
        command.setImage(source.getImage());
        command.setDirections(source.getDirections());
        if(source.getCategories() != null && source.getCategories().size() > 0) {
            source.getCategories().forEach(category -> {
                command.getCategories().add(categoryConverter.convert(category));
            });
        }

        if(source.getIngredients() != null && source.getIngredients().size() > 0) {
            source.getIngredients().forEach(ingredient -> {
                command.getIngredients().add(ingredientConverter.convert(ingredient));
            });
        }

        return command;

    }
}
