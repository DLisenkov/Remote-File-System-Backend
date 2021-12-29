package com.project.services;

import com.project.dao.files.FilesDao;
import com.project.dao.users.UsersDao;
import com.project.forms.UserForm;
import com.project.models.File;
import com.project.models.State;
import com.project.models.User;
import com.project.transfer.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService{

    @Autowired
    @Qualifier("UsersDaoDatabase")
    private UsersDao usersDao;

    @Autowired
    @Qualifier("FilesDaoDatabase")
    private FilesDao filesDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto addUser(UserForm userForm) {

        Optional<User> userCandidate = usersDao.findOneByLogin(userForm.getLogin());
        if (userCandidate.isEmpty()) {
            String hashPassword = passwordEncoder.encode(userForm.getPassword());

            User user = User.builder()
                    .firstName(userForm.getFirstName())
                    .lastName(userForm.getLastName())
                    .login(userForm.getLogin())
                    .hashPassword(hashPassword)
                    .state(State.ACTIVE)
                    .build();

            usersDao.save(user);

            java.io.File personalDirectory = new java.io.File(LOAD_PATH + userForm.getLogin());
            if (!personalDirectory.mkdir()) {
                throw new IllegalArgumentException("Personal directory not created!");
            }
            File personalDirectoryRecord = File.builder()
                    .name(userForm.getLogin())
                    .size(0)
                    .creationDate(new Date())
                    .modificationDate(new Date())
                    .path(LOAD_PATH + userForm.getLogin())
                    .owner(user)
                    .build();
            filesDao.save(personalDirectoryRecord);
            return UserDto.from(user);
        } throw new IllegalArgumentException("Login already exists");
    }
}
