package com.project.dao.files;

import com.project.dao.CrudDao;
import com.project.models.File;

import java.util.List;
import java.util.Optional;

/**
 * Interface describes methods for working with files in the storage
 * @see CrudDao
 */
public interface FilesDao extends CrudDao<File> {
    /**
     * Method finds a file in a specific path
     * @param path the path to the file
     * @return an object of type {@link Optional} for {@link File}
     */
    Optional<File> findFileByPath(String path);

    /**
     * Method finds all files by parent file
     * @param parentFile parent file
     * @return file list as {@link List}
     */
    List<File> findAllByParentFile(File parentFile);
}
