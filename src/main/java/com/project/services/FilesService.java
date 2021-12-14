package com.project.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FilesService {

    public final String UPLOAD_PATH = "/files";

    public void upload(MultipartFile file, int userId) throws IOException;
}
