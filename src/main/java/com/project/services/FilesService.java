package com.project.services;

import com.project.forms.DeleteForm;
import com.project.forms.DirectoryForm;
import com.project.forms.FileForm;
import com.project.models.File;

import java.io.IOException;
import java.util.List;

/**
 * Service interface for implementing the logic of the program associated with files
 */
public interface FilesService {

    /**
     * Path field for loading from the environment variable
     */
    String LOAD_PATH = System.getenv("LOAD_PATH") + "/";

    /**
     * Method for adding a file to the data storage
     * @param fileForm form like {@link FileForm}
     * @throws IOException if there was an error inside the method
     */
    void addFile(FileForm fileForm) throws IOException;

    /**
     * Method for editing a file in the data storage
     * @param fileForm form like {@link FileForm}
     * @throws IOException if there was an error inside the method
     */
    void editFile(FileForm fileForm) throws IOException;

    /**
     * Method for deleting a file from the data storage
     * @param deleteFileForm form like {@link DeleteForm}
     */
    void deleteFile(DeleteForm deleteFileForm);

    /**
     * Method for adding a directory to the data storage
     * @param directoryForm form like {@link DirectoryForm}
     */
    void addDirectory(DirectoryForm directoryForm);

    /**
     * Method for editing a directory in the data storage
     * @param directoryForm form like {@link DirectoryForm}
     */
    void editDirectory(DirectoryForm directoryForm);

    /**
     * Method for deleting a directory from the data storage
     * @param deleteForm form like {@link DeleteForm}
     * @throws IOException if there was an error inside the method
     */
    void deleteDirectory(DeleteForm deleteForm) throws IOException;

    /**
     * Method for getting file content
     * @param path the path to the file
     * @return file as {@link File}
     */
    File getFileContent(String path);

    /**
     * Method for getting directory content
     * @param path the path to the file
     * @return list of files as {@link List}
     */
    List<File> getDirectoryContent(String path);
}
