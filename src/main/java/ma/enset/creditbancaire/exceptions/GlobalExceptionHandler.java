package ma.enset.creditbancaire.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(
            ResourceNotFoundException ex, WebRequest request) {
        
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND, request);
    }
    
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(
            BusinessException ex, WebRequest request) {
        
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST, request);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllUncaughtException(
            Exception ex, WebRequest request) {
        
        return buildErrorResponse(
            ex, 
            "Unknown error occurred", 
            "INTERNAL_ERROR", 
            HttpStatus.INTERNAL_SERVER_ERROR, 
            request
        );
    }
    
    private ResponseEntity<Object> buildErrorResponse(
            BaseException ex, HttpStatus status, WebRequest request) {
        
        return buildErrorResponse(ex, ex.getMessage(), ex.getCode(), status, request);
    }
    
    private ResponseEntity<Object> buildErrorResponse(
            Exception ex, String message, String code, HttpStatus status, WebRequest request) {
        
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("code", code);
        body.put("message", message);
        body.put("path", request.getDescription(false).replace("uri=", ""));
        
        return new ResponseEntity<>(body, status);
    }
}
