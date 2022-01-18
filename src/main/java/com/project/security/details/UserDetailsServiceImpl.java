package com.project.security.details;

import com.project.dao.users.UsersDao;
import com.project.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service provides a method to get user details extends the class {@link UserDetailsService}
 * @see UserDetailsImpl
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * Field for accessing users DAO methods
     * @see UsersDao
     */
    @Autowired
    @Qualifier ("UsersDaoDatabase")
    private UsersDao usersDao;

    /**
     * Method represents user details by login
     * @param login user login
     * @return user details as {@link UserDetailsImpl}
     * @throws IllegalArgumentException if user doesn't exist
     * @see UsersDao#findOneByLogin(String) 
     */
    @Override
    public UserDetails loadUserByUsername(String login) throws IllegalArgumentException {
        Optional<User> userCandidate = usersDao.findOneByLogin(login);

        if (userCandidate.isPresent()) {
            return new UserDetailsImpl(userCandidate.get());
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }
}
