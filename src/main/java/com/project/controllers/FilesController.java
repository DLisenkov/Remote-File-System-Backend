package com.project.controllers;

import com.project.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.project.services.FilesService;

import java.io.IOException;

@RestController
public class FilesController {

    private FilesService filesService;

    @PostMapping(value = "/files/{user-id}")
    public ResponseEntity<Object> addFile(
            @RequestParam("file") MultipartFile file,
            @PathVariable("user-id") int userId) {
        try {
            filesService.upload(file, userId);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
