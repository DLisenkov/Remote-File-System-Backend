package com.project.forms;

import lombok.Data;

/**
 * The form contains data about the file
 *
 * @see lombok.Data
 */
@Data
public class FileForm {

    /**
     * The path to the file
     */
    private String path;

    /**
     * File name
     */
    private String name;

    /**
     * File content
     */
    private String content;
}
