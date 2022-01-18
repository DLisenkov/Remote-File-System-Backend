package com.project.dao.files;

import com.project.dao.CrudDao;
import com.project.models.File;
import com.project.single.FilesStorage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class for working with storage of files in memory that implements the {@link FilesDao} interface
 * @see FilesStorage
 */
@Component ("FilesDaoInMemory")
public class FilesDaoInMemory implements FilesDao{

    /**
     * Implements the method {@link com.project.dao.CrudDao#find(int)}
     * @param id unique identificator
     * @return an object of type {@link Optional} for {@link File}
     */
    @Override
    public Optional<File> find(int id) {
        for(File file: FilesStorage.storage().files()) {
            if (file.getId() == id) {
                return Optional.of(file);
            }
        }
        return Optional.empty();
    }

    /**
     * Implements the method {@link com.project.dao.CrudDao#save(Object)}
     * @param model data to save
     */
    @Override
    public void save(File model) {
        FilesStorage.storage().files().add(model);
    }

    /**
     * Implements the method {@link com.project.dao.CrudDao#update(Object)}
     * @param model data to update
     */
    @Override
    public void update(File model) {
        for(File file: FilesStorage.storage().files()) {
            if (file.getId() == model.getId()) {
                FilesStorage.storage().files().set(file.getId(), model);
            }
        }
    }

    /**
     * Implements the method {@link com.project.dao.CrudDao#delete(int)}
     * @param id unique identificator
     */
    @Override
    public void delete(int id) {
        FilesStorage.storage().files().removeIf(file -> file.getId() == id);
    }

    /**
     * Implements the method {@link CrudDao#findAll()}
     * @return file list as {@link List}
     */
    @Override
    public List<File> findAll() {
        return FilesStorage.storage().files();
    }

    /**
     * Implements the method {@link FilesDao#findFileByPath(String)}
     * @param path the path to the file
     * @return an object of type {@link Optional} for {@link File}
     */
    @Override
    public Optional<File> findFileByPath(String path) {
        for(File file: FilesStorage.storage().files()) {
            if (file.getPath().equals(path)) {
                return Optional.of(file);
            }
        }
        return Optional.empty();
    }


    /**
     * Implements the method {@link FilesDao#findAllByParentFile(File)}
     * @param parentFile parent file
     * @return file list as {@link List}
     */
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
