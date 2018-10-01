package com.charlie.morerecipes.services;

import com.charlie.morerecipes.domain.Recipe;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void saveImageFile(Long recipeId, MultipartFile file) throws Exception;
}
