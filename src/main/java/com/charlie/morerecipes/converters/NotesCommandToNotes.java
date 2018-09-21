package com.charlie.morerecipes.converters;

import com.charlie.morerecipes.commands.NotesCommand;
import com.charlie.morerecipes.domain.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {
    @Override
    public Notes convert(NotesCommand source) {
        if(source == null) {
            return null;
        }

        Notes notes = new Notes();
        notes.setRecipeNotes(source.getRecipeNotes());
        notes.setId(source.getId());

        return notes;
    }
}
