package ma.enset.creditbancaire.exceptions;

public class CreditException extends BusinessException {
    public static final String INVALID_CREDIT = "INVALID_CREDIT";
    public static final String CREDIT_ALREADY_PROCESSED = "CREDIT_ALREADY_PROCESSED";
    public static final String INVALID_AMOUNT = "INVALID_AMOUNT";
    
    public CreditException(String message, String code) {
        super(message, code);
    }
    
    public static CreditException invalidCredit(String reason) {
        return new CreditException("Invalid credit: " + reason, INVALID_CREDIT);
    }
    
    public static CreditException creditAlreadyProcessed(Long creditId) {
        return new CreditException("Credit with id " + creditId + " has already been processed", CREDIT_ALREADY_PROCESSED);
    }
    
    public static CreditException invalidAmount(double amount) {
        return new CreditException("Invalid credit amount: " + amount, INVALID_AMOUNT);
    }
}
