package com.project.dao;

import com.project.models.File;

import java.util.List;
import java.util.Optional;

public class FilesDaoInFile implements FilesDao{
    @Override
    public Optional<File> find(int id) {
        return Optional.empty();
    }

    @Override
    public void save(File model) {

    }

    @Override
    public void update(File model) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<File> findAll() {
        return null;
    }
}
