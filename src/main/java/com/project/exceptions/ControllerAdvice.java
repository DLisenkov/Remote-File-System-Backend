package com.project.exceptions;

import com.project.models.ExceptionBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

/**
 * Class handler for exceptions raised in controllers
 */
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    /**
     * Method exception handler when {@link IllegalArgumentException}
     *
     * @param illegalArgumentException thrown exception
     * @return {@link ResponseEntity} structure that contains the body of the exception as {@link ExceptionBody}
     */
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ExceptionBody> illegalArgumentException(IllegalArgumentException illegalArgumentException) {
        return new ResponseEntity<>(
                ExceptionBody.builder().status(HttpStatus.BAD_REQUEST.value()).message(illegalArgumentException.getMessage()).build(),
                HttpStatus.BAD_REQUEST
        );
    }

    /**
     * Method exception handler when {@link IOException}
     *
     * @param ioException thrown exception
     * @return {@link ResponseEntity} structure that contains the body of the exception as {@link ExceptionBody}
     */
    @ExceptionHandler({IOException.class})
    public ResponseEntity<ExceptionBody> ioException(IOException ioException) {
        return new ResponseEntity<>(
                ExceptionBody.builder().message(ioException.getMessage()).build(),
                HttpStatus.BAD_REQUEST
        );
    }

}
