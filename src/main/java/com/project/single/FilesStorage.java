package com.project.single;

import com.project.models.File;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for storing files in memory
 */
public class FilesStorage {

    /**
     * {@link FilesStorage} filed
     */
    private static final FilesStorage storage;

    static {
        storage = new FilesStorage();
    }

    /**
     * List of files
     *
     * @see List
     */
    private List<File> files;

    /**
     * Parameterless constructor creates a list of files
     *
     * @see ArrayList
     */
    private FilesStorage() {
        files = new ArrayList<>();
    }

    /**
     * Static method for getting storage
     *
     * @return storage
     * @see FilesStorage#storage
     */
    public static FilesStorage storage() {
        return storage;
    }

    /**
     * Method of getting files from storage
     *
     * @return files
     * @see FilesStorage#files
     */
    public List<File> files() {
        return files;
    }
}
