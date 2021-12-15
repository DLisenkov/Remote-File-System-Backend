package com.project.dao;

import com.project.models.File;
import com.project.single.FilesStorage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component ("FilesDaoInMemory")
public class FilesDaoInMemory implements FilesDao{
    @Override
    public Optional<File> find(int id) {
        for(File file: FilesStorage.storage().files()) {
            if (file.getId() == id) {
                return Optional.of(file);
            }
        }
        return Optional.empty();
    }

    @Override
    public void save(File model) {
        FilesStorage.storage().files().add(model);
    }

    @Override
    public void update(File model) {
        for(File file: FilesStorage.storage().files()) {
            if (file.getId() == model.getId()) {
                FilesStorage.storage().files().set(file.getId(), model);
            }
        }
    }

    @Override
    public void delete(int id) {
        FilesStorage.storage().files().removeIf(file -> file.getId() == id);
    }

    @Override
    public List<File> findAll() {
        return FilesStorage.storage().files();
    }

    @Override
    public Optional<File> findFileByPath(String path) {
        for(File file: FilesStorage.storage().files()) {
            if (file.getPath().equals(path)) {
                return Optional.of(file);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<File> findAllByParentFile(File parentFile) {
        List<File> files = new ArrayList<>();
        for(File file: FilesStorage.storage().files()) {
            if (file.getParentFile().equals(parentFile)) {
                files.add(file);
            }
        }
        return files;
    }
}
