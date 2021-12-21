package com.project.dao.files;

import com.project.models.File;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component ("FilesDaoInFile")
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

    @Override
    public Optional<File> findFileByPath(String path) {
        return Optional.empty();
    }

    @Override
    public List<File> findAllByParentFile(File parentFile) {
        return null;
    }
}
