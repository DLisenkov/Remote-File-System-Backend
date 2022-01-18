package com.project.forms;

import lombok.Data;

/**
 * The form contains data about the file to be deleted
 * @see lombok.Data
 */
@Data
public class DeleteForm {

    /**
     * The path to the file
     */
    private String path;
}
