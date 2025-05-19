package ma.enset.creditbancaire.exceptions;

public class RemboursementException extends BusinessException {
    public static final String ALREADY_PAID = "ALREADY_PAID";
    public static final String PAYMENT_OVERDUE = "PAYMENT_OVERDUE";
    public static final String INVALID_PAYMENT = "INVALID_PAYMENT";
    
    public RemboursementException(String message, String code) {
        super(message, code);
    }
    
    public static RemboursementException alreadyPaid(Long remboursementId) {
        return new RemboursementException("Remboursement with id " + remboursementId + " has already been paid", ALREADY_PAID);
    }
    
    public static RemboursementException paymentOverdue(Long remboursementId) {
        return new RemboursementException("Remboursement with id " + remboursementId + " is overdue", PAYMENT_OVERDUE);
    }
    
    public static RemboursementException invalidPayment(String reason) {
        return new RemboursementException("Invalid payment: " + reason, INVALID_PAYMENT);
    }
}
