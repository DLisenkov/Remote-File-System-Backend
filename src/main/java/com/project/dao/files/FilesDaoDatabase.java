package com.project.dao.files;

import com.project.models.File;
import com.project.repositories.FilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component ("FilesDaoDatabase")
public class FilesDaoDatabase implements FilesDao{

    @Autowired
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

    @Override
    public Optional<File> findFileByPath(String path) {
        return filesRepository.findFileByPath(path);
    }

    @Override
    public List<File> findAllByParentFile(File parentFile) {
        return filesRepository.findAllByParentFile(parentFile);
    }
}
