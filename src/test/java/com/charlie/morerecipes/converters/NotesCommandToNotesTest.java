package com.charlie.morerecipes.converters;

import com.charlie.morerecipes.commands.NotesCommand;
import com.charlie.morerecipes.domain.Ingredient;
import com.charlie.morerecipes.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {

    public static final Integer INTEGER_VALUE = 1;
    public static final BigDecimal BIG_DECIMAL_VALUE = BigDecimal.valueOf(30l);
    public static final String DESCRIPTION = "description";
    public static final String NOTES_VALUE = "Now is the time for all good men";

    private NotesCommandToNotes converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesCommandToNotes();
    }

    @Test
    public void testForNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testForEmptyObject() {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    public void convert() {

        // given
        NotesCommand command = new NotesCommand();
        command.setId(INTEGER_VALUE);
        command.setRecipeNotes(NOTES_VALUE);
        // when
        Notes notes = converter.convert(command);
        // then
        assertNotNull(notes);
        assertEquals(INTEGER_VALUE,notes.getId());
        assertEquals(NOTES_VALUE,notes.getRecipeNotes());
    }
}