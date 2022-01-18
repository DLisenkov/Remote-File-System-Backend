package com.project.models;

import lombok.Builder;
import lombok.Data;

/**
 * Entity contains exception body
 */
@Data
@Builder
public class ExceptionBody {
    /**
     * Exception status
     */
    private int status;

    /**
     * Exception message
     */
    private String message;
}
