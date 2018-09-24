package com.charlie.morerecipes.converters;

import com.charlie.morerecipes.commands.UnitOfMeasureCommand;
import com.charlie.morerecipes.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

    public static final String DESCRIPTION = "description";
    public static final Long INTEGER_VALUE = 1L;

    UnitOfMeasureCommandToUnitOfMeasure converter;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void convert() {

        // given
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setDescription(DESCRIPTION);
        command.setId(INTEGER_VALUE);

        // when
        UnitOfMeasure uom = converter.convert(command);

        // then
        assertEquals(DESCRIPTION,uom.getDescription());
        assertEquals(INTEGER_VALUE,uom.getId());

    }
}