package sys.pro;

/**
 * Class for managing useful functions.
 */
public class Utils {
    /**
     * Exits from program.
     *
     * @param message message before exit.
     */
    public static void exit(String message) {
        System.err.println(message);
        System.exit(1);
    }
}
