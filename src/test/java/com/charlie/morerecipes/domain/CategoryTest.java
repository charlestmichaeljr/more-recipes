package com.charlie.morerecipes.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    Category category;

    @Before
    public void setUp() throws Exception {
        category = new Category();
    }

    @Test
    public void getId() {
        Integer idValue = 4;
        category.setId(idValue);
        assertEquals(idValue,category.getId());
    }

    @Test
    public void getRecipes() {
    }

    @Test
    public void getDescription() {
    }
}