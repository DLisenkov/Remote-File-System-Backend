package com.project.dao;

import com.project.models.File;
import com.project.models.User;
import com.project.single.FilesStorage;
import com.project.single.UsersStorage;

import java.util.List;
import java.util.Optional;

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
}
