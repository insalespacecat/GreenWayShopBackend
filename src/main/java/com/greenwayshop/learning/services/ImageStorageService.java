/*package com.greenwayshop.learning.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@Slf4j
public class ImageStorageService {

    private final Path imagesPath = Paths.get("images");

    public void init() {
        try {
            if(Files.notExists(imagesPath)){
                Files.createDirectory(imagesPath);
            }
        } catch (IOException e) {
            throw new RuntimeException("Image directory does not exist and its creation is failed!");
        }
    }

    public void save(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.imagesPath.resolve(Objects.requireNonNull(file.getOriginalFilename())), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to save the file!");
        }
    }

    public void save(InputStream inputStream, String filename) {
        try {
            Files.copy(inputStream, this.imagesPath.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to save the file!");
        }
    }

    public Resource load(String filename) {
        try {
            Path pathToFile = imagesPath.resolve(filename);
            Resource resource = new UrlResource(pathToFile.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to load the file!");
            }
        } catch (MalformedURLException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.imagesPath, 1).filter(path -> !path.equals(this.imagesPath)).map(this.imagesPath::relativize);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to retrieve the files!");
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(imagesPath.toFile());
    }

    public void delete(String filename){
        Path pathToFile = imagesPath.resolve(filename);
        try {
            Files.deleteIfExists(pathToFile);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to delete the file!");
        }
    }

}

 */
