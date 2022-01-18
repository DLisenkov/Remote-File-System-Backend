package com.project.dao.files;

import com.project.dao.CrudDao;
import com.project.models.File;
import com.project.repositories.FilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Class for working with a file database that implements the {@link FilesDao} interface
 */
@Component ("FilesDaoDatabase")
public class FilesDaoDatabase implements FilesDao{

    /**
     * Field for accessing files repository methods
     * @see FilesRepository
     */
    @Autowired
    FilesRepository filesRepository;

    /**
     * Implements the method {@link com.project.dao.CrudDao#find(int)}
     * @param id unique identificator
     * @return an object of type {@link Optional} for {@link File}
     * @see FilesRepository#findById(Object) 
     */
    @Override
    public Optional<File> find(int id) {
        return filesRepository.findById(id);
    }

    /**
     * Implements the method {@link com.project.dao.CrudDao#save(Object)}
     * @param model data to save
     * @see FilesRepository#save(Object) 
     */
    @Override
    public void save(File model) {
        filesRepository.save(model);
    }

    /**
     * Implements the method {@link com.project.dao.CrudDao#update(Object)}
     * @param model data to update
     * @see FilesRepository#save(Object) 
     */
    @Override
    public void update(File model) {
        filesRepository.save(model);
    }

    /**
     * Implements the method {@link com.project.dao.CrudDao#delete(int)}
     * @param id unique identificator
     * @see FilesRepository#deleteById(Object) 
     */
    @Override
    public void delete(int id) {
        filesRepository.deleteById(id);
    }

    /**
     * Implements the method {@link CrudDao#findAll()}
     * @return file list as {@link List}
     * @see FilesRepository#findAll() 
     */
    @Override
    public List<File> findAll() {
        return filesRepository.findAll();
    }

    /**
     * Implements the method {@link FilesDao#findFileByPath(String)}
     * @param path the path to the file
     * @return an object of type {@link Optional} for {@link File}
     * @see FilesRepository#findFileByPath(String) 
     */
    @Override
    public Optional<File> findFileByPath(String path) {
        return filesRepository.findFileByPath(path);
    }

    /**
     * Implements the method {@link FilesDao#findAllByParentFile(File)}
     * @param parentFile parent file
     * @return file list as {@link List}
     * @see FilesRepository#findAllByParentFile(File) 
     */
    @Override
    public List<File> findAllByParentFile(File parentFile) {
        return filesRepository.findAllByParentFile(parentFile);
    }
}
