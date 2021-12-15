package com.project.dao;

import com.project.models.File;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

public interface FilesDao extends CrudDao<File> {
    Optional<File> findFileByPath(String path);
    List<File> findAllByParentFile(File parentFile);
}
