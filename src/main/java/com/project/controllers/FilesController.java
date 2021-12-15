package com.project.controllers;

import com.project.dao.FilesDao;
import com.project.models.File;
import com.project.transfer.FileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.project.services.FilesService;

import java.io.IOException;
import java.util.List;

@RestController
public class FilesController {

    @Autowired
    private FilesService filesService;

    @Autowired
    @Qualifier("FilesDaoDatabase")
    private FilesDao filesDao;

    @GetMapping(value = "/files/download-file/{user-id}")
    public Resource downloadFile(
            @PathVariable("user-id") int userId,
            @RequestParam String path) throws IOException {
        return filesService.downloadFile(userId, path);
    }

    @GetMapping(value = "/files/download-directory/{user-id}")
    public List<FileDto> downloadDirectory(
            @PathVariable("user-id") int userId,
            @RequestParam String path) throws IOException {
        return FileDto.from(filesService.downloadDirectory(userId, path));
    }

    @PostMapping(value = "/files/upload-file/{user-id}")
    public ResponseEntity<Object> uploadFile(
            @PathVariable("user-id") int userId,
            @RequestParam("file") MultipartFile file,
            @RequestParam String path) throws IOException {
        filesService.uploadFile(userId, file, path);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/files/upload-directory/{user-id}")
    public ResponseEntity<Object> createDirectory(
            @RequestParam String path,
            @RequestParam String name,
            @PathVariable("user-id") int userId) throws IOException {
        filesService.uploadDirectory(userId, path, name);
        return ResponseEntity.ok().build();
    }
}
