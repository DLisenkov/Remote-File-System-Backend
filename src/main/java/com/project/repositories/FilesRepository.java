package com.project.repositories;

import com.project.models.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FilesRepository extends JpaRepository<File, Integer> {
    //int countFilesByOwner (User user);
    Optional<File> findFileByPath(String path);
    List<File> findAllByParentFile(File parentFile);
}
