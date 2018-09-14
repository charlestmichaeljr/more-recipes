package com.charlie.morerecipes.repositories;

import com.charlie.morerecipes.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe,Integer> {
}
