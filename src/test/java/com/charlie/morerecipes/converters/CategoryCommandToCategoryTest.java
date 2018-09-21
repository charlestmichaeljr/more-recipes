package com.charlie.morerecipes.converters;

import com.charlie.morerecipes.commands.CategoryCommand;
import com.charlie.morerecipes.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

    public static final String DESCRIPTION = "description";
    public static final Integer INTEGER_VALUE = 1;

    CategoryCommandToCategory converter;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryCommandToCategory();
    }

    @Test
    public void testForNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testForEmptyObject() {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    public void convert() {
        // given
        CategoryCommand command = new CategoryCommand();
        command.setId(INTEGER_VALUE);
        command.setDescription(DESCRIPTION);
        // when
        Category category = converter.convert(command);
        // then
        assertEquals(INTEGER_VALUE,category.getId());
        assertEquals(DESCRIPTION,category.getDescription());
    }
}