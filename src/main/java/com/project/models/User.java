package com.project.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Entity user contains all required fields
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

    /**
     * List of tokens associated with the entity {@link Token}
     */
    @OneToMany(mappedBy = "user")
    List<Token> tokens;
    /**
     * Unique user id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * User first name
     */
    private String firstName;
    /**
     * User last name
     */
    private String lastName;
    /**
     * User login
     */
    private String login;
    /**
     * User password
     */
    private String hashPassword;
    /**
     * User state
     *
     * @see State
     */
    @Enumerated(value = EnumType.STRING)
    private State state;
    /**
     * List of files associated with the entity {@link File}
     */
    @OneToMany(mappedBy = "owner")
    private List<File> files;
    /**
     * List of permissions associated with the entity {@link Permission}
     */
    @OneToMany(mappedBy = "file")
    private List<Permission> permissions;
}
