package com.charlie.morerecipes.repositories;

import com.charlie.morerecipes.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Integer> {
}
