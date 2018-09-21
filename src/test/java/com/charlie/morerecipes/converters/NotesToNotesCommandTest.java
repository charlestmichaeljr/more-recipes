package com.charlie.morerecipes.converters;

import com.charlie.morerecipes.commands.NotesCommand;
import com.charlie.morerecipes.domain.Notes;
import com.charlie.morerecipes.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {
    public static final Integer INTEGER_VALUE = 1;
    public static final BigDecimal BIG_DECIMAL_VALUE = BigDecimal.valueOf(30l);
    public static final String DESCRIPTION = "description";
    public static final String NOTES_VALUE = "Now is the time for all good men";

    NotesToNotesCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesToNotesCommand();
    }

    @Test
    public void testForNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testForEmptyObject() {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    public void convert() {

        // given
        Notes notes = new Notes();
        notes.setId(INTEGER_VALUE);
        notes.setRecipeNotes(NOTES_VALUE);

        // when
        NotesCommand command = converter.convert(notes);
        // then
        assertNotNull(command);
        assertEquals(INTEGER_VALUE,command.getId());
        assertEquals(NOTES_VALUE,command.getRecipeNotes());
    }
}