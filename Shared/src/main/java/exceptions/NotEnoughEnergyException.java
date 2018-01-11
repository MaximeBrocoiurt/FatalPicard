package exceptions;

public class NotEnoughEnergyException extends Exception
{
    /**
     * Constructs a new exception
     */
    public NotEnoughEnergyException()
    {
        super();
    }

    /**
     * Construit une nouvelle exception avec un message personnalisé;.
     * @param message message personnalisé pour cette exception.
     */
    public NotEnoughEnergyException(String message)
    {
        super(message);
    }

    /**
     * Construit une nouvelle exception à partir d’un message et d’une autre
     * @param message nouveau message
     * @param cause ancienne exception à propager
     */
    public NotEnoughEnergyException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Construit une nouvelle exception à partir d’une autre.
     * @param cause ancienne exception à propager.
     */
    public NotEnoughEnergyException(Throwable cause)
    {
        super(cause);
    }
}
