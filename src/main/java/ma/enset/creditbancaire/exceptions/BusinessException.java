package ma.enset.creditbancaire.exceptions;

public class BusinessException extends BaseException {
    public static final String DEFAULT_CODE = "BUSINESS_EXCEPTION";
    
    public BusinessException(String message) {
        super(message, DEFAULT_CODE);
    }
    
    public BusinessException(String message, String code) {
        super(message, code);
    }
}
