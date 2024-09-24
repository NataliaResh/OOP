package sys.pro;

/**
 * Class for managing Utils.
 */
public class Utils {
    /**
     * Exits from program with {@code message}.
     *
     * @param message error message.
     */
    public static void exit(String message) {
        System.err.println(message);
        System.exit(1);
    }

    /**
     * Pass instruction.
     */
    public static void pass() {
        return;
    }
}
