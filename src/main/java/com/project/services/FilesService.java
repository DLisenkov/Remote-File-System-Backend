package com.project.services;

import com.project.models.File;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FilesService {

    public final String LOAD_PATH = "src/main/resources/files/";

    public void uploadFile(int userId, MultipartFile file, String path) throws IOException;
    public void uploadDirectory(int userId, String path, String name) throws IOException;
    public Resource downloadFile(int userId, String path) throws IOException;
    public List<File> downloadDirectory(int userId, String path) throws IOException;
}
