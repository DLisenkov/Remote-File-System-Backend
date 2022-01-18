package com.project.transfer;

import com.project.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * User data transfer object
 */
@Data
@AllArgsConstructor
public class UserDto {

    /**
     * User login
     */
    private String login;

    /**
     * Method for getting user DTO by user
     * @param user user
     * @return user DTO like {@link UserDto}
     */
    public static UserDto from(User user) {
        return new UserDto(user.getLogin());
    }
}
