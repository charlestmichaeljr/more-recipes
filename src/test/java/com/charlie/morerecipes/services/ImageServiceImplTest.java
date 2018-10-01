package com.charlie.morerecipes.services;

import com.charlie.morerecipes.domain.Recipe;
import com.charlie.morerecipes.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ImageServiceImplTest {

    ImageService imageService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        imageService = new ImageServiceImpl(recipeRepository);
    }

    @Test
    public void saveImageFile() throws Exception{

        // given
        Long id = 1L;
        MultipartFile multipartFile = new MockMultipartFile("imageFile","testing.txt","text/plain",
                "Charlie is the greatest man in all of humanity".getBytes());
        Recipe recipe = new Recipe();
        recipe.setId(id);
        Optional<Recipe> optionalRecipe = Optional.of(recipe);
        // when
        when(recipeRepository.findById(id)).thenReturn(optionalRecipe);
        ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);

        imageService.saveImageFile(id,multipartFile);
        // then
        verify(recipeRepository,times(1)).save(argumentCaptor.capture());
        Recipe savedRecipe = argumentCaptor.getValue();

        assertEquals(multipartFile.getBytes().length,savedRecipe.getImage().length);

    }
}