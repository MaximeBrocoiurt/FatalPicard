package exceptions;

public class NotInRangeException extends Exception
{
    /**
     * Constructs a new exception
     */
    public NotInRangeException()
    {
        super();
    }

    /**
     * Construit une nouvelle exception avec un message personnalisé;.
     * @param message message personnalisé pour cette exception.
     */
    public NotInRangeException(String message)
    {
        super(message);
    }

    /**
     * Construit une nouvelle exception à partir d’un message et d’une autre
     * @param message nouveau message
     * @param cause ancienne exception à propager
     */
    public NotInRangeException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Construit une nouvelle exception à partir d’une autre.
     * @param cause ancienne exception à propager.
     */
    public NotInRangeException(Throwable cause)
    {
        super(cause);
    }
}
