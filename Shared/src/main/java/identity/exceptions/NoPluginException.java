package identity.exceptions;

/**
 * exception levée lorsqu’un plugin est manquant
 */
public class NoPluginException extends Exception
{
    public NoPluginException() {
        super();
    }

    public NoPluginException(String message) {
        super(message);
    }

    public NoPluginException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoPluginException(Throwable cause) {
        super(cause);
    }
}
