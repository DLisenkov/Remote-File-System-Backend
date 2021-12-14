package com.project.repositories;

import com.project.models.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilesRepository extends JpaRepository<File, Integer> {

}
