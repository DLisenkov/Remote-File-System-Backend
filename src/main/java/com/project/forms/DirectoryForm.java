package com.project.forms;

import lombok.Data;

/**
 * The form contains data about the directory
 * @see lombok.Data
 */
@Data
public class DirectoryForm {

    /**
     * The path to the file
     */
    private String path;

    /**
     * Directory name
     */
    private String name;
}
