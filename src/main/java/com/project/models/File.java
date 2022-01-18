package com.project.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Entity file contains all required fields
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "file")
public class File {
    /**
     * Unique file id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * File name
     */
    private String name;

    /**
     * File size
     */
    private double size;

    /**
     * File creation date
     */
    private Date creationDate;

    /**
     * File modification date
     */
    private Date modificationDate;

    /**
     * The path to the file
     */
    private String path;

    /**
     * Unique parent id associated with the entity {@link File}
     */
    @OneToOne
    @JoinColumn(name="parent_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private File parentFile;

    /**
     * Unique user id associated with the entity {@link User}
     */
    @ManyToOne
    @JoinColumn (name="owner_id")
    private User owner;

    /**
     * List of permissions associated with the entity {@link Permission}
     */
    @OneToMany(mappedBy = "user")
    private List<Permission> permissions;
}