package com.project.services;

import com.project.forms.DeleteForm;
import com.project.forms.DirectoryForm;
import com.project.forms.FileForm;
import com.project.models.File;

import java.io.IOException;
import java.util.List;

public interface FilesService {

    String LOAD_PATH = System.getenv("LOAD_PATH") + "/";

    void addFile(FileForm fileForm) throws IOException;
    void editFile(FileForm fileForm) throws IOException;
    void deleteFile(DeleteForm deleteFileForm);
    void addDirectory(DirectoryForm directoryForm);
    void editDirectory(DirectoryForm directoryForm);
    void deleteDirectory(DeleteForm deleteForm) throws IOException;
    File getFileContent(String path);
    List<File> getDirectoryContent(String path);
}
