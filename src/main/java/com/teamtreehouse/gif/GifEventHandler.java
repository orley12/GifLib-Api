//package com.teamtreehouse.gif;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
//@Component
//@RepositoryEventHandler(Gif.class)
//public class GifEventHandler {
//
//    private final GifRepository gifRepository;
//
//    @Autowired
//    public GifEventHandler(GifRepository gifRepository){
//        this.gifRepository = gifRepository;
//    }
//
//    public void convertMultipartFileToBytes(Gif gif, @RequestParam MultipartFile file){
//        try {
//            gif.setBytes(file.getBytes());
//            gifRepository.save(gif);
//        } catch (IOException e) {
//            System.err.println("Unable to get byte array from file");
//        }
//    }
//}
