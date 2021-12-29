package com.project.services;

import com.project.dao.files.FilesDao;
import com.project.dao.users.UsersDao;
import com.project.forms.DirectoryForm;
import com.project.forms.FileForm;
import com.project.models.File;
import com.project.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class FilesServiceImpl implements FilesService{

    @Autowired
    @Qualifier ("UsersDaoDatabase")
    private UsersDao usersDao;

    @Autowired
    @Qualifier ("FilesDaoDatabase")
    private FilesDao filesDao;

    @Override
    public File getFileContent(String path) throws IOException{
        String login = path.split("/")[0];
        Optional<User> userCandidate = usersDao.findOneByLogin(login);
        if (userCandidate.isPresent()) {
            //User user = userCandidate.get();

            String absoluteParentPath = LOAD_PATH + path;
            Optional<File> fileCandidate = filesDao.findFileByPath(absoluteParentPath);
            if (fileCandidate.isPresent()) {
                return fileCandidate.get();
            } else {
                throw new IOException("File not found!");
            }
        } else {
            throw new IOException("User not found!");
        }
    }

    @Override
    public List<File> getDirectoryContent(String path) throws IOException {
        String login = path.split("/")[0];
        Optional<User> userCandidate = usersDao.findOneByLogin(login);
        if (userCandidate.isPresent()) {
            //User user = userCandidate.get();

            String absoluteParentPath = LOAD_PATH /*+ user.getLogin()*/ + path.substring(0, path.length() - 1);
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
    public void addFile(FileForm fileForm) throws IOException {
        String login = fileForm.getPath().split("/")[0];
        Optional<User> userCandidate = usersDao.findOneByLogin(login);
        if (userCandidate.isPresent()) {
            User user = userCandidate.get();

            String absolutePath = LOAD_PATH + fileForm.getPath() + fileForm.getName();
            java.io.File file = new java.io.File(absolutePath);
            if (!file.createNewFile()) {
                throw new IOException("File not created!");
            }
            FileOutputStream stream = new FileOutputStream(file.toString());
            stream.write(fileForm.getContent().getBytes(StandardCharsets.UTF_8));

            String absoluteParentPath = LOAD_PATH + fileForm.getPath().substring(0, fileForm.getPath().length() - 1);
            Optional<File> fileCandidate = filesDao.findFileByPath(absoluteParentPath);
            if (fileCandidate.isPresent()) {
                File parentFile = fileCandidate.get();
                parentFile.setSize(parentFile.getSize() + fileForm.getContent().length());

                File fileRecord = File.builder()
                        .name(fileForm.getName())
                        .size(fileForm.getContent().length())
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
    public void addDirectory(DirectoryForm directoryForm) throws IOException {
        String login = directoryForm.getPath().split("/")[0];
        Optional<User> userCandidate = usersDao.findOneByLogin(login);
        if (userCandidate.isPresent()) {
            User user = userCandidate.get();

            String absolutePath = LOAD_PATH + directoryForm.getPath() + directoryForm.getName();
            java.io.File directory = new java.io.File(absolutePath);
            if (!directory.mkdir()) {
                throw new IOException("Directory not created!");
            }

            String absoluteParentPath = LOAD_PATH + directoryForm.getPath().substring(0, directoryForm.getPath().length() - 1);
            Optional<File> fileCandidate = filesDao.findFileByPath(absoluteParentPath);
            if (fileCandidate.isPresent()) {
                File parentFile = fileCandidate.get();

                File fileRecord = File.builder()
                        .name(directoryForm.getName())
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

//    private void createPersonalDirectory(User user) throws IOException {
//        if (user.getFiles().isEmpty()) {
//            java.io.File personalDirectory = new java.io.File(System.getenv("LOAD_PATH") + user.getLogin());
//            if (!personalDirectory.mkdir()) {
//                throw new IOException("Personal directory not created!");
//            }
//            File personalDirectoryRecord = File.builder()
//                    .name(user.getLogin())
//                    .size(0)
//                    .creationDate(new Date())
//                    .modificationDate(new Date())
//                    .path(System.getenv("LOAD_PATH") + user.getLogin())
//                    .owner(user)
//                    .build();
//            filesDao.save(personalDirectoryRecord);
//        }
//    }
}
