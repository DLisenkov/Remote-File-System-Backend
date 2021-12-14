package com.project.dao;

import com.project.models.File;
import com.project.models.User;
import com.project.repositories.FilesRepository;
import com.project.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;

public class FilesDaoDatabase implements FilesDao{
    FilesRepository filesRepository;

    @Override
    public Optional<File> find(int id) {
        return filesRepository.findById(id);
    }

    @Override
    public void save(File model) {
        filesRepository.save(model);
    }

    @Override
    public void update(File model) {
        filesRepository.save(model);
    }

    @Override
    public void delete(int id) {
        filesRepository.deleteById(id);
    }

    @Override
    public List<File> findAll() {
        return filesRepository.findAll();
    }
}
