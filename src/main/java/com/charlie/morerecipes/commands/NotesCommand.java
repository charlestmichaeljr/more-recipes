package com.charlie.morerecipes.commands;

import com.charlie.morerecipes.domain.Recipe;

import javax.persistence.*;

public class NotesCommand {

    private Integer Id;
    private RecipeCommand recipe;
    private String recipeNotes;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public RecipeCommand getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipeCommand recipe) {
        this.recipe = recipe;
    }

    public String getRecipeNotes() {
        return recipeNotes;
    }

    public void setRecipeNotes(String recipeNotes) {
        this.recipeNotes = recipeNotes;
    }
}
