package sys.pro;

/**
 * Class of Not Enough Significations Exception.
 */
public class NotEnoughSignificationsExpression extends Exception {
    /**
     * Construct new exception.
     *
     * @param message error message.
     */
    public NotEnoughSignificationsExpression(String message) {
        super(message);
    }
}