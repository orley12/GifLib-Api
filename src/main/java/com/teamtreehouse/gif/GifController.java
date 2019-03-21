package com.teamtreehouse.gif;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamtreehouse.category.Category;
import com.teamtreehouse.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class GifController {

    private final GifRepository gifRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public GifController(GifRepository gifRepo, CategoryRepository catRepo) {
        gifRepository = gifRepo;
        categoryRepository = catRepo;
    }

    @RequestMapping(method = POST, value = "/upload", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Gif addGif(MultipartFile file, @RequestPart("gif") String gifJson, @RequestPart("name") String categoryName ) {

        /*TODO: this is just a temporary implimentation for the sake of postman
        * TODO: Till we can find a better way to send the category Object via Json */
        System.out.println(categoryName);
        Category category = categoryRepository.findByName(categoryName);
        System.out.println(category.toString());

        Gif gif = null;
        try {
            gif = new ObjectMapper().readValue(gifJson, Gif.class);
            gif.setCategory(category);
            gif.setBytes(file.getBytes());
            gifRepository.save(gif);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gif;
    }

}

