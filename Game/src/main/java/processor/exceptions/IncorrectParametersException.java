package processor.exceptions;

public class IncorrectParametersException extends Exception
{
    public IncorrectParametersException() {
        super();
    }

    public IncorrectParametersException(String message) {
        super(message);
    }

    public IncorrectParametersException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectParametersException(Throwable cause) {
        super(cause);
    }

    protected IncorrectParametersException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
