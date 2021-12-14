package com.project.single;

import com.project.models.File;

import java.util.ArrayList;
import java.util.List;

public class FilesStorage {

    private static final FilesStorage storage;

    static {
        storage = new FilesStorage();
    }

    private List<File> files;

    private FilesStorage() {
        files = new ArrayList<>();
    }

    public static FilesStorage storage() {
        return storage;
    }

    public List<File> files() {
        return files;
    }
}
