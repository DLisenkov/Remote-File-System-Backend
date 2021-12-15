package com.project.services;

import com.project.dao.FilesDao;
import com.project.dao.UsersDao;
import com.project.models.File;
import com.project.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FilesServiceImpl implements FilesService{

    @Autowired
    @Qualifier ("UsersDaoDatabase")
    private UsersDao usersDao;

    @Autowired
    @Qualifier ("FilesDaoDatabase")
    private FilesDao filesDao;

    @Override
    public Resource downloadFile(int userId, String path) throws IOException{
        Optional<User> userCandidate = usersDao.find(userId);
        if (userCandidate.isPresent()) {
            User user = userCandidate.get();

            Path absolutePath = Paths.get(LOAD_PATH + user.getLogin() + path);
            Resource resource = new UrlResource(absolutePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new IOException();
            }
        } else {
            throw new IOException("User not found!");
        }
    }

    @Override
    public List<File> downloadDirectory(int userId, String path) throws IOException {
        Optional<User> userCandidate = usersDao.find(userId);
        if (userCandidate.isPresent()) {
            User user = userCandidate.get();

            String absoluteParentPath = LOAD_PATH + user.getLogin() + path.substring(0, path.length() - 1);
            Optional<File> fileCandidate = filesDao.findFileByPath(absoluteParentPath);
            if (fileCandidate.isPresent()) {
                return filesDao.findAllByParentFile(fileCandidate.get());
            } else {
                throw new IOException("File not found!");
            }
        } else {
            throw new IOException("User not found!");
        }
    }

    @Override
    public void uploadFile(int userId, MultipartFile multipartFile, String path) throws IOException {
        Optional<User> userCandidate = usersDao.find(userId);
        if (userCandidate.isPresent()) {
            User user = userCandidate.get();

            createPersonalDirectory(user);

            String absolutePath = LOAD_PATH + user.getLogin() + path + multipartFile.getOriginalFilename();
            java.io.File file = new java.io.File(absolutePath);
            if (!file.createNewFile()) {
                throw new IOException("File not created!");
            }
            FileOutputStream stream = new FileOutputStream(file.toString());
            stream.write(multipartFile.getBytes());

            String absoluteParentPath = LOAD_PATH + user.getLogin() + path.substring(0, path.length() - 1);
            Optional<File> fileCandidate = filesDao.findFileByPath(absoluteParentPath);
            if (fileCandidate.isPresent()) {
                File parentFile = fileCandidate.get();
                parentFile.setSize(parentFile.getSize() + multipartFile.getSize());

                File fileRecord = File.builder()
                        .name(multipartFile.getOriginalFilename())
                        .size(multipartFile.getSize())
                        .creationDate(new Date())
                        .modificationDate(new Date())
                        .path(absolutePath)
                        .parentFile(parentFile)
                        .owner(user)
                        .build();
                filesDao.save(fileRecord);
            } else {
                throw new IOException("Parent file not found");
            }
        } else {
            throw new IOException("User not found");
        }
    }

    @Override
    public void uploadDirectory(int userId, String path, String name) throws IOException {
        Optional<User> userCandidate = usersDao.find(userId);
        if (userCandidate.isPresent()) {
            User user = userCandidate.get();

            createPersonalDirectory(user);

            String absolutePath = LOAD_PATH + user.getLogin() + path + name;
            java.io.File directory = new java.io.File(absolutePath);
            if (!directory.mkdir()) {
                throw new IOException("Directory not created!");
            }

            String absoluteParentPath = LOAD_PATH + user.getLogin() + path.substring(0, path.length() - 1);
            Optional<File> fileCandidate = filesDao.findFileByPath(absoluteParentPath);
            if (fileCandidate.isPresent()) {
                File parentFile = fileCandidate.get();

                File fileRecord = File.builder()
                        .name(name)
                        .size(0)
                        .creationDate(new Date())
                        .modificationDate(new Date())
                        .path(absolutePath)
                        .parentFile(parentFile)
                        .owner(user)
                        .build();
                filesDao.save(fileRecord);
            } else {
                throw new IOException("Parent file not found");
            }
        } else {
            throw new IOException("User not found");
        }
    }

    private void createPersonalDirectory(User user) throws IOException {
        if (user.getFiles().isEmpty()) {
            java.io.File personalDirectory = new java.io.File(LOAD_PATH + user.getLogin());
            if (!personalDirectory.mkdir()) {
                throw new IOException("Personal directory not created!");
            }
            File personalDirectoryRecord = File.builder()
                    .name(user.getLogin())
                    .size(0)
                    .creationDate(new Date())
                    .modificationDate(new Date())
                    .path(LOAD_PATH + user.getLogin())
                    .owner(user)
                    .build();
            filesDao.save(personalDirectoryRecord);
        }
    }
}
