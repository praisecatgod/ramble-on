package game_io;

/**
 * The InvalidDataException is a checked exception that represents the occasion
 * where an desired value is not found in an xml file.
 *
 * @author Yvonne DeSousa CSE 219 S13
 * @version 1.0
 */
public class InvalidDataException extends Exception {

    private String error;

    /**
     * Constructor for this exception, with error message.
     *
     * @param errorMessage
     */
    public InvalidDataException(String errorMessage) {
        error = errorMessage;

    }

    /**
     * This method builds returns the textual description of this object.
     *
     * @return This error message
     */
    public String toString() {
        return error;
    }
}
