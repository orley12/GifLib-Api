package com.teamtreehouse.gif;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.teamtreehouse.category.Category;
import com.teamtreehouse.category.CategoryRepository;
import com.teamtreehouse.category.Color;
import com.teamtreehouse.exc.StorageFileNotFoundException;
import com.teamtreehouse.service.FileStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Path;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class GifController {

    private final GifRepository gifRepository;
    private final CategoryRepository categoryRepository;
    private final FileStorageServiceImpl fileStorageService;

    @Autowired
    public GifController(GifRepository gifRepo, CategoryRepository catRepo, FileStorageServiceImpl fileStorageService) {
        gifRepository = gifRepo;
        categoryRepository = catRepo;
        this.fileStorageService = fileStorageService;
    }

    @RequestMapping(method = POST, value = "/upload", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Gif addGif(MultipartFile file, @RequestPart("gif") String gifJson, @RequestPart("name") String categoryName ) {

            Path relativizedPath = fileStorageService.store(file);
            String fileUrl = MvcUriComponentsBuilder
                    .fromMethodName(GifController.class, "serveFile",
                            relativizedPath.getFileName().toString()).build().toString();

        /*TODO: this is just a temporary implimentation for the sake of postman
        * TODO: Till we can find a better way to send the category Object via Json */
        System.out.println(categoryName);
        Category category = categoryRepository.findByName(categoryName);
        System.out.println(category.toString());

        Gif gif = null;
        try {
            gif = new ObjectMapper().readValue(gifJson, Gif.class);
            gif.setCategory(category);
            gif.setUrl(fileUrl);
            gifRepository.save(gif);
            return gif;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = fileStorageService.loadAsResource(filename);
        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(c))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);

    }

    @GetMapping(value = "/categories/colors", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody public String getCategoryColors() {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            return objectWriter.writeValueAsString(Color.values());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}

