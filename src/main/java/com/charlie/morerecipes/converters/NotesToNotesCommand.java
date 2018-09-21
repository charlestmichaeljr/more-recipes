package com.charlie.morerecipes.converters;

import com.charlie.morerecipes.commands.NotesCommand;
import com.charlie.morerecipes.domain.Notes;
import org.springframework.core.convert.converter.Converter;

public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {
    @Override
    public NotesCommand convert(Notes source) {
        if(source == null) {
            return null;
        }

        NotesCommand command = new NotesCommand();
        command.setId(source.getId());
        command.setId(source.getId());
        command.setRecipeNotes(source.getRecipeNotes());

        return command;
    }
}
