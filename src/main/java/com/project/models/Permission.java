package com.project.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Entity permission contains all required fields
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permission")
public class Permission {

    /**
     * Unique permission id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Unique file id associated with the entity {@link File}
     */
    @ManyToOne
    @JoinColumn(name="file_id")
    private File file;

    /**
     * Unique user id associated with the entity {@link User}
     */
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
