package exceptions;

public class BankExchangeException extends RuntimeException {
    public BankExchangeException(String message) {
        super(message);
    }
}
