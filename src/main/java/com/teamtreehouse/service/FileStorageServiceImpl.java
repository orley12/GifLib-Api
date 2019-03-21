package com.teamtreehouse.service;

import com.teamtreehouse.exc.StorageException;
import com.teamtreehouse.exc.StorageFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class FileStorageServiceImpl implements FileStorageService{

    private final Path rootLocation;

    @Autowired
    public FileStorageServiceImpl(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    /**
     * Stores the MultipartFile in a file directory
     * @param file
     * @method cleanPath() Normalize the path by removing sequences like "path/.." and inner simple dots.
     * @resrved try(){} with resources block auto-closes the inputStream
     * @method Files.copy() takes the inputStream data and writes it into the the file at the resolved path
     * @method resolve returns a path with its argument @param(filename) appended to the root path (rootLocation)
     * @throws StorageException
     */
    @Override
    public Path store(MultipartFile file){
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Path resolvedPath = this.rootLocation.resolve(filename);
                System.out.println(resolvedPath.toString());
                Files.copy(inputStream, resolvedPath,
                        StandardCopyOption.REPLACE_EXISTING);
                Path relativizePath = this.rootLocation.relativize(resolvedPath);
                return relativizePath;
            }
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }


    /**
     * @return Stream data of Path Objects
     * @method filter() filter for paths that are not equal to the location, if true.
     * @method map() return a list of relativize paths eg upload-dir/Screenshots(20).jpg becomes
     *         ../Screenshots(20).jpg or just /Screenshots(20).jpg
     * @throws StorageException
     */
    @Override
    public Stream<Path> loadAll()  {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename){
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init(){
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            try {
                throw new StorageException("Could not initialize storage", e);
            } catch (StorageException e1) {
                e1.printStackTrace();
            }
        }
    }
}
