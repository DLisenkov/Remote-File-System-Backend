package com.project.controllers;

import com.project.forms.DeleteForm;
import com.project.forms.DirectoryForm;
import com.project.forms.FileForm;
import com.project.transfer.DirectoryDto;
import com.project.transfer.FileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.services.FilesService;

import java.io.IOException;
import java.util.List;

@RestController
public class FilesController {

    @Autowired
    private FilesService filesService;

    @GetMapping(value = "/files/get-file-content")
    public FileDto getFileContent(
            @RequestParam String path) throws IOException {
        return FileDto.from(filesService.getFileContent(path));
    }

    @GetMapping(value = "/files/get-directory-content")
    public List<DirectoryDto> getDirectoryContent(
            @RequestParam String path) {
        return DirectoryDto.from(filesService.getDirectoryContent(path));
    }

    @PostMapping(value = "/files/add-file")
    public ResponseEntity<Object> addFile(
            @RequestBody FileForm fileForm) throws IOException {
        filesService.addFile(fileForm);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/files/edit-file")
    public ResponseEntity<Object> editFile(
            @RequestBody FileForm fileForm) throws IOException {
        filesService.editFile(fileForm);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/files/delete-file")
    public ResponseEntity<Object> deleteFile(
            @RequestBody DeleteForm deleteFileForm) {
        filesService.deleteFile(deleteFileForm);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/files/add-directory")
    public ResponseEntity<Object> addDirectory(
            @RequestBody DirectoryForm directoryForm) {
        filesService.addDirectory(directoryForm);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/files/edit-directory")
    public ResponseEntity<Object> editDirectory(
            @RequestBody DirectoryForm directoryForm) {
        filesService.editDirectory(directoryForm);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/files/delete-directory")
    public ResponseEntity<Object> deleteDirectory(
            @RequestBody DeleteForm deleteForm) throws IOException {
        filesService.deleteDirectory(deleteForm);
        return ResponseEntity.ok().build();
    }
}
