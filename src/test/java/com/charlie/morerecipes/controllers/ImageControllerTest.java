package com.charlie.morerecipes.controllers;

import com.charlie.morerecipes.commands.RecipeCommand;
import com.charlie.morerecipes.services.ImageService;
import com.charlie.morerecipes.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class ImageControllerTest {

    ImageController imageController;

    @Mock
    ImageService imageService;

    @Mock
    RecipeService recipeService;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        imageController = new ImageController(imageService, recipeService);

        mockMvc = MockMvcBuilders.standaloneSetup(imageController)
                .setControllerAdvice(new ControllerExceptionHandler()).build();
    }

    @Test
    public void getImageForm() throws Exception {
        // given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);
        // when
        mockMvc.perform(get("/recipe/1/image"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/imageuploadform"));
        // then
        verify(recipeService, times(1)).findCommandById(anyLong());
    }

    @Test
    public void handleImagePost() throws Exception {

        // given
        MockMultipartFile multipartFile = new MockMultipartFile("imagefile", "testing.txt",
                "text/plain", "Charlie is the greatest!".getBytes());
        // then
        mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
                .andExpect(status().isFound())
                .andExpect(header().string("Location", "/recipe/1/show"));
    }

    @Test
    public void renderImageFromDB() throws Exception {

        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        String s = "fakeImageTest";
        Byte[] bytesBoxed = new Byte[s.getBytes().length];

        int i = 0;
        for (byte b : s.getBytes()) {
            bytesBoxed[i++] = b;
        }

        recipeCommand.setImage(bytesBoxed);

        // when
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        MockHttpServletResponse servletResponse = mockMvc.perform(get("/recipe/1/recipeimage"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        byte[] responseBytes = servletResponse.getContentAsByteArray();

        // then
        assertEquals(bytesBoxed.length, responseBytes.length);
        verify(recipeService, times(1)).findCommandById(anyLong());
    }

    @Test
    public void testPostImageNumberFormatException() throws  Exception{

        // given
        MockMultipartFile multipartFile = new MockMultipartFile("imagefile", "testing.txt",
                "text/plain", "Charlie is the greatest!".getBytes());
        // then
        mockMvc.perform(multipart("/recipe/abcd/image").file(multipartFile))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));
    }
}