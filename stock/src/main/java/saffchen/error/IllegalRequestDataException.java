package saffchen.error;

/**
 * @author alex_jd on 8/10/22
 * @project JRM-Java-Stock-System
 */

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.HttpStatus;

import static org.springframework.boot.web.error.ErrorAttributeOptions.Include.*;

public class IllegalRequestDataException extends AppException {
    public IllegalRequestDataException(String msg) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, msg, ErrorAttributeOptions.of(MESSAGE));
    }
}
