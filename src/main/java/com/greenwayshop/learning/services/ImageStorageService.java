package com.greenwayshop.learning.services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class ImageStorageService {

    private final Path imagesPath = Paths.get("images");

    public void init() {
        try {
            Files.createDirectory(imagesPath);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to create directory for image storage!");
        }
    }

    public void save(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.imagesPath.resolve(Objects.requireNonNull(file.getOriginalFilename())));
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

}
