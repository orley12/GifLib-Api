//package com.teamtreehouse.gif;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Data
//public static class User {
//    private String name;
//    private String lastName;
//}
//
//@Component
//public  class StringToGifConverter implements Converter<String, Gif> {
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Override
//    public Gif convert(String source) {
//        try {
//            return objectMapper.readValue(source, Gif.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
