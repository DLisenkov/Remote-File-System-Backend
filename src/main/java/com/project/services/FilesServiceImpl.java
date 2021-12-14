package com.project.services;

import com.project.dao.FilesDao;
import com.project.dao.UsersDao;
import com.project.models.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Service
public class FilesServiceImpl implements FilesService{

    private UsersDao usersDao;

    private FilesDao filesDao;

    @Override
    public void upload(MultipartFile multipartFile, int userId) throws IOException {
        File fileRecord = File.builder()
                .name(multipartFile.getOriginalFilename())
                .type(multipartFile.getContentType())
                .size(multipartFile.getSize())
                .creationDate(new Date())
                .modificationDate(new Date()).build();
                //.owner(usersDao.find(userId).get()).build();
        filesDao.save(fileRecord);

        Path path = Paths.get(UPLOAD_PATH, fileRecord.getId() + "." + fileRecord.getName());
        Path file = Files.createFile(path);
        try (FileOutputStream stream = new FileOutputStream(file.toString())) {
            stream.write(multipartFile.getBytes());
        }
        //для каждого пользователя создавать отдельную папку с уникальным именем = login
    }
}
