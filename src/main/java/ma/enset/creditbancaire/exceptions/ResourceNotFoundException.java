package ma.enset.creditbancaire.exceptions;

public class ResourceNotFoundException extends BaseException {
    public static final String DEFAULT_CODE = "RESOURCE_NOT_FOUND";
    
    public ResourceNotFoundException(String message) {
        super(message, DEFAULT_CODE);
    }
    
    public ResourceNotFoundException(String resourceType, Long id) {
        super(String.format("%s not found with id: %d", resourceType, id), DEFAULT_CODE);
    }
    
    public ResourceNotFoundException(String resourceType, String field, Object value) {
        super(String.format("%s not found with %s: %s", resourceType, field, value), DEFAULT_CODE);
    }
}
