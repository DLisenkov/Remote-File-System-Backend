package com.project.services;

import com.project.dao.files.FilesDao;
import com.project.dao.users.UsersDao;
import com.project.forms.DeleteForm;
import com.project.forms.DirectoryForm;
import com.project.forms.FileForm;
import com.project.models.File;
import com.project.models.User;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.processing.FilerException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Service implementing interface {@link FilesService}
 */
@Service
public class FilesServiceImpl implements FilesService {

    /**
     * Field for accessing users DAO methods
     * @see UsersDao
     */
    @Autowired
    @Qualifier("UsersDaoDatabase")
    private UsersDao usersDao;

    /**
     * Field for accessing files DAO methods
     * @see FilesDao
     */
    @Autowired
    @Qualifier("FilesDaoDatabase")
    private FilesDao filesDao;

    /**
     * Implements the method {@link FilesService#getFileContent(String)}
     * @param path the path to the file
     * @return file as {@link File}
     * @see UsersDao#findOneByLogin(String) 
     */
    @Override
    public File getFileContent(String path) {
        String login = path.split("/")[0];
        Optional<User> userCandidate = usersDao.findOneByLogin(login);
        if (userCandidate.isPresent()) {

            String absoluteParentPath = LOAD_PATH + path;
            Optional<File> parentFileCandidate = filesDao.findFileByPath(absoluteParentPath);
            if (parentFileCandidate.isPresent()) {
                return parentFileCandidate.get();
            } else {
                throw new IllegalArgumentException("Parent file not found!");
            }
        } else {
            throw new IllegalArgumentException("User not found!");
        }
    }

    /**
     * Implements the method {@link FilesService#getDirectoryContent(String)}
     * @param path the path to the file
     * @return list of files as {@link List}
     * @see UsersDao#findOneByLogin(String)
     * @see FilesDao#findAllByParentFile(File)
     */
    @Override
    public List<File> getDirectoryContent(String path) {
        String login = path.split("/")[0];
        Optional<User> userCandidate = usersDao.findOneByLogin(login);
        if (userCandidate.isPresent()) {

            String absoluteParentPath = LOAD_PATH + path.substring(0, path.length() - 1);
            Optional<File> parentFileCandidate = filesDao.findFileByPath(absoluteParentPath);
            if (parentFileCandidate.isPresent()) {
                return filesDao.findAllByParentFile(parentFileCandidate.get());
            } else {
                throw new IllegalArgumentException("Parent file not found!");
            }
        } else {
            throw new IllegalArgumentException("User not found!");
        }
    }

    /**
     * Implements the method {@link FilesService#addFile(FileForm)}
     * @param fileForm form like {@link FileForm}
     * @throws IOException if failed to write to file
     * @see UsersDao#findOneByLogin(String)
     * @see FilesDao#findFileByPath(String)
     * @see FilesDao#save(Object)
     */
    @Override
    public void addFile(FileForm fileForm) throws IOException {
        String login = fileForm.getPath().split("/")[0];
        Optional<User> userCandidate = usersDao.findOneByLogin(login);
        if (userCandidate.isPresent()) {
            User user = userCandidate.get();

            String absolutePath = LOAD_PATH + fileForm.getPath() + fileForm.getName();

            FileWriter fileWriter = new FileWriter(absolutePath);
            fileWriter.write(fileForm.getContent());
            fileWriter.close();

            String absoluteParentPath = LOAD_PATH + fileForm.getPath().substring(0, fileForm.getPath().length() - 1);
            Optional<File> parentFileCandidate = filesDao.findFileByPath(absoluteParentPath);
            if (parentFileCandidate.isPresent()) {
                File parentFile = parentFileCandidate.get();
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
                throw new IllegalArgumentException("Parent file not found!");
            }
        } else {
            throw new IllegalArgumentException("User not found!");
        }
    }

    /**
     * Implements the method {@link FilesService#editFile(FileForm)}
     * @param fileForm form like {@link FileForm}
     * @throws IOException if failed to write to file
     * @see UsersDao#findOneByLogin(String)
     * @see FilesDao#findFileByPath(String)
     * @see FilesDao#update(Object)
     */
    @Override
    public void editFile(FileForm fileForm) throws IOException {
        String login = fileForm.getPath().split("/")[0];
        Optional<User> userCandidate = usersDao.findOneByLogin(login);
        if (userCandidate.isPresent()) {

            String oldAbsolutePath = LOAD_PATH + fileForm.getPath();
            String absolutePath = LOAD_PATH
                    + fileForm.getPath().substring(0, fileForm.getPath().length() - fileForm.getPath().split("/")[fileForm.getPath().split("/").length - 1].length())
                    + fileForm.getName();

            Optional<File> fileCandidate = filesDao.findFileByPath(oldAbsolutePath);
            if (fileCandidate.isPresent()) {
                File fileRecord = fileCandidate.get();

                String absoluteParentPath =
                        absolutePath.substring(0, absolutePath.length() - fileForm.getName().length() - 1);
                Optional<File> parentFileCandidate = filesDao.findFileByPath(absoluteParentPath);
                if (parentFileCandidate.isPresent()) {
                    File parentFile = parentFileCandidate.get();
                    parentFile.setSize(parentFile.getSize() - fileRecord.getSize() + fileForm.getContent().length());
                }

                java.io.File file = new java.io.File(oldAbsolutePath);
                if (!file.renameTo(new java.io.File(absolutePath))) {
                    throw new IllegalArgumentException("File not renamed!");
                }

                FileWriter fileWriter = new FileWriter(absolutePath);
                fileWriter.write(fileForm.getContent());
                fileWriter.close();

                fileRecord.setName(fileForm.getName());
                fileRecord.setSize(fileForm.getContent().length());
                fileRecord.setModificationDate(new Date());
                fileRecord.setPath(absolutePath);
                filesDao.update(fileRecord);
            } else {
                throw new IllegalArgumentException("Parent file not found!");
            }
        } else {
            throw new IllegalArgumentException("User not found!");
        }
    }

    /**
     * Implements the method {@link FilesService#deleteFile(DeleteForm)}
     * @param deleteFileForm form like {@link DeleteForm}
     * @see UsersDao#findOneByLogin(String)
     * @see FilesDao#findFileByPath(String)
     * @see FilesDao#delete(int)
     */
    @Override
    public void deleteFile(DeleteForm deleteFileForm) {
        String path = deleteFileForm.getPath();
        String login = path.split("/")[0];

        Optional<User> userCandidate = usersDao.findOneByLogin(login);
        if (userCandidate.isPresent()) {

            String absolutePath = LOAD_PATH + path;
            Optional<File> fileCandidate = filesDao.findFileByPath(absolutePath);
            if (fileCandidate.isPresent()) {

                java.io.File file = new java.io.File(absolutePath);
                if (!file.delete()) {
                    throw new IllegalArgumentException("File not deleted!");
                }

                File fileRecord = fileCandidate.get();
                filesDao.delete(fileRecord.getId());

                String absoluteParentPath = LOAD_PATH +
                        path.substring(0, path.length() - path.split("/")[path.split("/").length - 1].length() - 1);
                Optional<File> parentFileCandidate = filesDao.findFileByPath(absoluteParentPath);
                if (parentFileCandidate.isPresent()) {
                    File parentFile = parentFileCandidate.get();
                    parentFile.setSize(parentFile.getSize() - fileRecord.getSize());
                }
            } else {
                throw new IllegalArgumentException("Parent file not found!");
            }
        } else {
            throw new IllegalArgumentException("User not found!");
        }
    }

    /**
     * Implements the method {@link FilesService#addDirectory(DirectoryForm)}
     * @param directoryForm form like {@link DirectoryForm}
     * @see UsersDao#findOneByLogin(String)
     * @see FilesDao#findFileByPath(String)
     * @see FilesDao#save(Object)
     */
    @Override
    public void addDirectory(DirectoryForm directoryForm) {
        String login = directoryForm.getPath().split("/")[0];
        Optional<User> userCandidate = usersDao.findOneByLogin(login);
        if (userCandidate.isPresent()) {
            User user = userCandidate.get();

            String absolutePath = LOAD_PATH + directoryForm.getPath() + directoryForm.getName();
            java.io.File directory = new java.io.File(absolutePath);
            if (!directory.mkdir()) {
                throw new IllegalArgumentException("Directory not created!");
            }

            String absoluteParentPath = LOAD_PATH + directoryForm.getPath().substring(0, directoryForm.getPath().length() - 1);
            Optional<File> parentFileCandidate = filesDao.findFileByPath(absoluteParentPath);
            if (parentFileCandidate.isPresent()) {
                File parentFile = parentFileCandidate.get();

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
                throw new IllegalArgumentException("Parent file not found!");
            }
        } else {
            throw new IllegalArgumentException("User not found!");
        }
    }

    /**
     * Implements the method {@link FilesService#editDirectory(DirectoryForm)}
     * @param directoryForm form like {@link DirectoryForm}
     * @see UsersDao#findOneByLogin(String)
     * @see FilesDao#findFileByPath(String)
     * @see FilesDao#update(Object)
     */
    @Override
    public void editDirectory(DirectoryForm directoryForm) {
        String login = directoryForm.getPath().split("/")[0];
        Optional<User> userCandidate = usersDao.findOneByLogin(login);
        if (userCandidate.isPresent()) {

            String oldAbsolutePath = LOAD_PATH + directoryForm.getPath().substring(0, directoryForm.getPath().length() - 1);
            String absolutePath = LOAD_PATH
                    + directoryForm.getPath().substring(0, directoryForm.getPath().length() - directoryForm.getPath().split("/")[directoryForm.getPath().split("/").length - 1].length() - 1)
                    + directoryForm.getName();

            Optional<File> fileCandidate = filesDao.findFileByPath(oldAbsolutePath);
            if (fileCandidate.isPresent()) {

                java.io.File file = new java.io.File(oldAbsolutePath);
                if (!file.renameTo(new java.io.File(absolutePath))) {
                    throw new IllegalArgumentException("File not renamed!");
                }

                File fileRecord = fileCandidate.get();

                List<File> childFiles = filesDao.findAllByParentFile(fileRecord);
                for(File childFile: childFiles) {
                    childFile.setPath(absolutePath + "/" + childFile.getName());
                    filesDao.update(childFile);
                }

                fileRecord.setName(directoryForm.getName());
                fileRecord.setModificationDate(new Date());
                fileRecord.setPath(absolutePath);
                filesDao.update(fileRecord);

            } else {
                throw new IllegalArgumentException("Parent file not found!");
            }
        } else {
            throw new IllegalArgumentException("User not found!");
        }
    }

    /**
     * Implements the method {@link FilesService#deleteDirectory(DeleteForm)}
     * @param deleteForm form like {@link DeleteForm}
     * @throws IOException if it was not possible to delete the directory
     * @see UsersDao#findOneByLogin(String)
     * @see FilesDao#findFileByPath(String) 
     * @see FilesDao#delete(int) 
     */
    @Override
    public void deleteDirectory(DeleteForm deleteForm) throws IOException {
        String path = deleteForm.getPath().substring(0, deleteForm.getPath().length() - 1);
        String login = path.split("/")[0];

        Optional<User> userCandidate = usersDao.findOneByLogin(login);
        if (userCandidate.isPresent()) {

            String absolutePath = LOAD_PATH + path;
            Optional<File> fileCandidate = filesDao.findFileByPath(absolutePath);
            if (fileCandidate.isPresent()) {

                FileUtils.deleteDirectory(new java.io.File(absolutePath));

                File fileRecord = fileCandidate.get();
                filesDao.delete(fileRecord.getId());

                String absoluteParentPath = LOAD_PATH +
                        path.substring(0, path.length() - path.split("/")[path.split("/").length - 1].length() - 1);
                Optional<File> parentFileCandidate = filesDao.findFileByPath(absoluteParentPath);
                if (parentFileCandidate.isPresent()) {
                    File parentFile = parentFileCandidate.get();
                    parentFile.setSize(parentFile.getSize() - fileRecord.getSize());
                }
            } else {
                throw new IllegalArgumentException("Parent file not found!");
            }
        } else {
            throw new IllegalArgumentException("User not found!");
        }
    }
}
