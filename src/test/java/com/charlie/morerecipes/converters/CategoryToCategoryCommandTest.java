package com.charlie.morerecipes.converters;

import com.charlie.morerecipes.commands.CategoryCommand;
import com.charlie.morerecipes.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {

    public static final String DESCRIPTION = "description";
    public static final Long INTEGER_VALUE = 1L;

    private CategoryToCategoryCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    public void testForNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testForEmptyObject() {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    public void convert() {

        // given
        Category category = new Category();
        category.setDescription(DESCRIPTION);
        category.setId(INTEGER_VALUE);
        // when
        CategoryCommand command = converter.convert(category);
        // then
        assertNotNull(command);
        assertEquals(INTEGER_VALUE,command.getId());
        assertEquals(DESCRIPTION,command.getDescription());

    }
}