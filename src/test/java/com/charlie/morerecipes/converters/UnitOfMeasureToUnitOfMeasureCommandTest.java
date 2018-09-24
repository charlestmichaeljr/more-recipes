package com.charlie.morerecipes.converters;

import com.charlie.morerecipes.commands.UnitOfMeasureCommand;
import com.charlie.morerecipes.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

    public static final String DESCRIPTION = "description";
    public static final Long INTEGER_VALUE = 1L;

    UnitOfMeasureToUnitOfMeasureCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    public void convert() {
        // given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(INTEGER_VALUE);
        uom.setDescription(DESCRIPTION);

        // when
        UnitOfMeasureCommand command = converter.convert(uom);
        // then
        assertEquals(INTEGER_VALUE,command.getId());
        assertEquals(DESCRIPTION,command.getDescription());
    }
}