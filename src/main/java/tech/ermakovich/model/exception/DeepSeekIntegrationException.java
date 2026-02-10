package tech.ermakovich.model.exception;

public class DeepSeekIntegrationException extends RuntimeException {
    public DeepSeekIntegrationException(String message) {
        super(message);
    }

    public DeepSeekIntegrationException(String message, Throwable cause) {
        super(message, cause);
    }
}