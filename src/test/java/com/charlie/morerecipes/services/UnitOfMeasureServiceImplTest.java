package com.charlie.morerecipes.services;

import com.charlie.morerecipes.commands.UnitOfMeasureCommand;
import com.charlie.morerecipes.domain.UnitOfMeasure;
import com.charlie.morerecipes.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.charlie.morerecipes.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UnitOfMeasureServiceImplTest {

    UnitOfMeasureToUnitOfMeasureCommand converter  = new UnitOfMeasureToUnitOfMeasureCommand();

    UnitOfMeasureService unitOfMeasureService;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        unitOfMeasureService = new UnitOfMeasureServiceImpl(converter,unitOfMeasureRepository);
    }

    @Test
    public void findAll() {

        // given
        Set<UnitOfMeasure> uoms = new HashSet<>();
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId(1L);
        uom1.setDescription("Teaspoon");

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom1.setId(2L);
        uom1.setDescription("Tablespoon");
        uoms.add(uom1);
        uoms.add(uom2);

        when(unitOfMeasureRepository.findAll()).thenReturn(uoms);

        // when
        Set<UnitOfMeasureCommand> returnedUoms = unitOfMeasureService.listAllUoms();

        // then
        assertNotNull(uoms);
        assertEquals(2,uoms.size());
        verify(unitOfMeasureRepository,times(1)).findAll();
    }
}