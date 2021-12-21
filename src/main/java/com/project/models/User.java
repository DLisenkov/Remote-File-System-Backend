package com.project.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;

    private String login;
    private String hashPassword;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @OneToMany(mappedBy = "owner")
    private List<File> files;

    @OneToMany(mappedBy = "file")
    private List<Permission> permissions;

    @OneToMany(mappedBy = "user")
    List<Token> tokens;
}
