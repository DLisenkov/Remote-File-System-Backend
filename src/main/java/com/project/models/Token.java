package com.project.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Entity token contains all required fields
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Token {

    /**
     * Unique token id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Token value
     */
    private String value;

    /**
     * Unique user id associated with the entity {@link User}
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}