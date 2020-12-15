package com.changemyminds.unittestnote.exception;

/**
 * Author: Changemyminds.
 * Date: 2020/12/15.
 * Description:
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
