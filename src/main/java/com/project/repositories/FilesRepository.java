package com.project.repositories;

import com.project.models.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Files repository interface extends {@link JpaRepository} interface uses hibernate to work with file database
 */
public interface FilesRepository extends JpaRepository<File, Integer> {
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
