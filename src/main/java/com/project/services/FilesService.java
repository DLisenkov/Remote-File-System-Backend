package com.project.services;

import com.project.models.File;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FilesService {

    String LOAD_PATH = "src/main/resources/files/";

    void uploadFile(int userId, MultipartFile file, String path) throws IOException;
    void uploadDirectory(int userId, String path, String name) throws IOException;
    Resource downloadFile(int userId, String path) throws IOException;
    List<File> downloadDirectory(int userId, String path) throws IOException;
}
