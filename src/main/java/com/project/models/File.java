package com.project.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "file")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String type;
    private double size;
    private Date creationDate;
    private Date modificationDate;

    private String path;

    @OneToOne
    @JoinColumn(name="parent_id")
    private File parentFile;

    @ManyToOne
    @JoinColumn (name="owner_id")
    private User owner;  //связано с user, в user список файлов

    //private List<User> access;

    //таблица permissions с полями idFile и idUser
    //idFile связано с file
    //idUser связано с user
}