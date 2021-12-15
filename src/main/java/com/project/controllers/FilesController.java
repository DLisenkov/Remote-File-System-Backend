package com.project.controllers;

import com.project.dao.FilesDao;
import com.project.models.File;
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
    public ResponseEntity<Resource> downloadFile(
            @PathVariable("user-id") int userId,
            @RequestParam String path) {
        try {
            Resource resource = filesService.downloadFile(userId, path);
            return ResponseEntity.ok()
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/files/download-directory/{user-id}")
    public ResponseEntity<Object> downloadDirectory(
            @PathVariable("user-id") int userId,
            @RequestParam String path) {
        try {
            List<String> fileNames = filesService.downloadDirectory(userId, path);
            return ResponseEntity.ok()
                    //.header("Content-Disposition", "attachment; filename=" + filesDao.findFileByPath(path))
                    .body(fileNames);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/files/upload-file/{user-id}")
    public ResponseEntity<Object> uploadFile(
            @PathVariable("user-id") int userId,
            @RequestParam("file") MultipartFile file,
            @RequestParam String path) {
        try {
            filesService.uploadFile(userId, file, path);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "/files/upload-directory/{user-id}")
    public ResponseEntity<Object> createDirectory(
            @RequestParam String path,
            @RequestParam String name,
            @PathVariable("user-id") int userId) {
        try {
            filesService.uploadDirectory(userId, path, name);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
