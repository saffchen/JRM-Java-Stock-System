package saffchen.error;

import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import saffchen.util.validation.ValidationUtil;

import javax.persistence.EntityNotFoundException;
import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.boot.web.error.ErrorAttributeOptions.Include.MESSAGE;

/**
 * @author saffchen created on 16.07.2022
 * https://www.bezkoder.com/spring-boot-restcontrolleradvice/
 */
@RestControllerAdvice
@AllArgsConstructor
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String EXCEPTION_DUPLICATE_EMAIL = "User with this email already exists";

    private final ErrorAttributes errorAttributes;

    @NonNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers, @NonNull HttpStatus status, @NonNull WebRequest request) {
        return handleBindingErrors(ex.getBindingResult(), request);
    }

    @NonNull
    @Override
    protected ResponseEntity<Object> handleBindException(
            BindException ex, @NonNull HttpHeaders headers, @NonNull HttpStatus status, @NonNull WebRequest request) {
        return handleBindingErrors(ex.getBindingResult(), request);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> appException(WebRequest request, AppException ex) {
        log.error("ApplicationException: {}", ex.getMessage());
        return createResponseEntity(getDefaultBody(request, ex.getOptions(), null), ex.getStatus());
    }

    @ExceptionHandler(NoEntityException.class)
    public ResponseEntity<?> handleNoEntityException(WebRequest request, NoEntityException ex) {
        log.error("NoEntityException: {}", ex.getMessage());
        return createResponseEntity(getDefaultBody(request, ErrorAttributeOptions.of(MESSAGE), null), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<?> handleSQLException(WebRequest request, SQLException ex) {
        log.error("SQLException: {}", ex.getMessage());
        return createResponseEntity(getDefaultBody(request, ErrorAttributeOptions.of(MESSAGE), null), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(WebRequest request, AccessDeniedException ex) {
        log.error("AccessDeniedException: {}", ex.getMessage());
        return createResponseEntity(getDefaultBody(request, ErrorAttributeOptions.of(MESSAGE), null), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<?> handleJWTVerificationException(WebRequest request, JWTVerificationException ex) {
        log.error("JWTVerificationException: {}", ex.getMessage());
        return createResponseEntity(getDefaultBody(request, ErrorAttributeOptions.of(MESSAGE), null), HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleConstraintViolation(WebRequest request, EntityNotFoundException ex) {
        log.error("EntityNotFoundException: {}", ex.getMessage());
        return createResponseEntity(getDefaultBody(request, ErrorAttributeOptions.of(MESSAGE), null), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(DataConflictException.class)
    public ResponseEntity<?> dataConflictException(WebRequest request, DataConflictException ex) {
        log.error("DataConflictException: {}", ex.getMessage());
        return createResponseEntity(getDefaultBody(request, ErrorAttributeOptions.of(MESSAGE), null), HttpStatus.CONFLICT);
    }

    private ResponseEntity<Object> handleBindingErrors(BindingResult result, WebRequest request) {
        String msg = result.getFieldErrors().stream()
                .map(fe -> String.format("[%s] %s", fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.joining("\n"));
        return createResponseEntity(getDefaultBody(request, ErrorAttributeOptions.defaults(), msg), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private Map<String, Object> getDefaultBody(WebRequest request, ErrorAttributeOptions options, String msg) {
        Map<String, Object> body = errorAttributes.getErrorAttributes(request, options);
        if (msg != null) {
            body.put("message", msg);
        }
        return body;
    }

    @SuppressWarnings("unchecked")
    private <T> ResponseEntity<T> createResponseEntity(Map<String, Object> body, HttpStatus status) {
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        return (ResponseEntity<T>) ResponseEntity.status(status).body(body);
    }


    @NonNull
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            @NonNull Exception ex, Object body, @NonNull HttpHeaders headers, @NonNull HttpStatus status, @NonNull WebRequest request) {
        log.error("Exception", ex);
        log.info("Exception", ex);
        super.handleExceptionInternal(ex, body, headers, status, request);
        return createResponseEntity(getDefaultBody(request, ErrorAttributeOptions.of(), ValidationUtil.getRootCause(ex)
                .getMessage()), status);
    }
}
