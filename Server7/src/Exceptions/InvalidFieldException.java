package Exceptions;

/**
 * Ошибка для проверки полей
 */

public class InvalidFieldException extends RuntimeException {
    public InvalidFieldException(String message) {
        super(message);
    }
}