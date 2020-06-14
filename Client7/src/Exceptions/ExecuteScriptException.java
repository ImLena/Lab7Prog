package Exceptions;

/**
 * Исключение, возникающее при рекурсии в скрипте
 */
public class ExecuteScriptException extends RuntimeException {
    public ExecuteScriptException(String message){ super(message); }
}