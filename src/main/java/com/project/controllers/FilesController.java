package com.project.controllers;

import com.project.forms.DeleteForm;
import com.project.forms.DirectoryForm;
import com.project.forms.FileForm;
import com.project.services.FilesService;
import com.project.transfer.DirectoryDto;
import com.project.transfer.FileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Rest controller for handling requests related to files
 */
@RestController
public class FilesController {

    /**
     * Field for accessing files service methods
     *
     * @see FilesService
     */
    @Autowired
    private FilesService filesService;

    /**
     * Request processing method for getting file contents
     *
     * @param path the path to the file
     * @return file DTO from {@link FileDto}
     * @throws IOException if an exception occurs in {@link FilesService#getFileContent(String)}
     * @see FilesService#getFileContent(String)
     */
    @GetMapping(value = "/files/get-file-content")
    public FileDto getFileContent(
            @RequestParam String path) throws IOException {
        return FileDto.from(filesService.getFileContent(path));
    }

    /**
     * Request processing method for getting directory contents
     *
     * @param path the path to the directory
     * @return directory DTO from {@link DirectoryDto}
     * @see FilesService#getDirectoryContent(String)
     */
    @GetMapping(value = "/files/get-directory-content")
    public List<DirectoryDto> getDirectoryContent(
            @RequestParam String path) {
        return DirectoryDto.from(filesService.getDirectoryContent(path));
    }

    /**
     * Method for handling a request to add a file
     *
     * @param fileForm form like {@link FileForm}
     * @return successful completion of the request
     * @throws IOException if an exception occurs in {@link FilesService#addFile(FileForm)}
     * @see FilesService#addFile(FileForm)
     */
    @PostMapping(value = "/files/add-file")
    public ResponseEntity<Object> addFile(
            @RequestBody FileForm fileForm) throws IOException {
        filesService.addFile(fileForm);
        return ResponseEntity.ok().build();
    }

    /**
     * Method for handling a request to edit a file
     *
     * @param fileForm form like {@link FileForm}
     * @return successful completion of the request
     * @throws IOException if an exception occurs in {@link FilesService#editFile(FileForm)}
     * @see FilesService#editFile(FileForm)
     */
    @PostMapping(value = "/files/edit-file")
    public ResponseEntity<Object> editFile(
            @RequestBody FileForm fileForm) throws IOException {
        filesService.editFile(fileForm);
        return ResponseEntity.ok().build();
    }

    /**
     * Method for handling a request to delete a file
     *
     * @param deleteFileForm form like {@link DeleteForm}
     * @return successful completion of the request
     * @see FilesService#deleteFile(DeleteForm)
     */
    @PostMapping(value = "/files/delete-file")
    public ResponseEntity<Object> deleteFile(
            @RequestBody DeleteForm deleteFileForm) {
        filesService.deleteFile(deleteFileForm);
        return ResponseEntity.ok().build();
    }

    /**
     * Method for handling a request to add a directory
     *
     * @param directoryForm form like {@link DirectoryForm}
     * @return successful completion of the request
     * @see FilesService#addDirectory(DirectoryForm)
     */
    @PostMapping(value = "/files/add-directory")
    public ResponseEntity<Object> addDirectory(
            @RequestBody DirectoryForm directoryForm) {
        filesService.addDirectory(directoryForm);
        return ResponseEntity.ok().build();
    }

    /**
     * Method for handling a request to edit a directory
     *
     * @param directoryForm form like {@link DirectoryForm}
     * @return successful completion of the request
     * @see FilesService#editDirectory(DirectoryForm)
     */
    @PostMapping(value = "/files/edit-directory")
    public ResponseEntity<Object> editDirectory(
            @RequestBody DirectoryForm directoryForm) {
        filesService.editDirectory(directoryForm);
        return ResponseEntity.ok().build();
    }

    /**
     * Method for handling a request to delete a directory
     *
     * @param deleteForm form like {@link DeleteForm}
     * @return successful completion of the request
     * @throws IOException if an exception occurs in {@link FilesService#deleteDirectory(DeleteForm)}
     * @see FilesService#deleteDirectory(DeleteForm)
     */
    @PostMapping(value = "/files/delete-directory")
    public ResponseEntity<Object> deleteDirectory(
            @RequestBody DeleteForm deleteForm) throws IOException {
        filesService.deleteDirectory(deleteForm);
        return ResponseEntity.ok().build();
    }
}
