package sys.pro;

/**
 * Class of Incorrect Format Exception.
 */
public class IncorrectFormatException extends Exception {
    /**
     * Construct new exception.
     *
     * @param fileName name of file;
     * @param line     line with incorrect format.
     */
    public IncorrectFormatException(String fileName, String line) {
        super("Incorrect format of file " + fileName + " in line:\n" + line);
    }
}
