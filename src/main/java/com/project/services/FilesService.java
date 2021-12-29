package com.project.services;

import com.project.forms.DirectoryForm;
import com.project.forms.FileForm;
import com.project.models.File;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FilesService {

    String LOAD_PATH = System.getenv("LOAD_PATH") + "\\";

    void addFile(FileForm fileForm) throws IOException;
    void addDirectory(DirectoryForm directoryForm) throws IOException;
    File getFileContent(String path) throws IOException;
    List<File> getDirectoryContent(String path) throws IOException;
}
