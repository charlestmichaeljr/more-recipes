package com.charlie.morerecipes.services;

import com.charlie.morerecipes.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
}