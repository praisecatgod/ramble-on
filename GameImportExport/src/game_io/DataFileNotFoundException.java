package game_io;

/**
 * The DataFileNotFoundException is a checked exception that represents the
 * occasion where an desired file is not found in the directory.
 *
 * @author Yvonne DeSousa CSE 219 S13
 * @version 1.0
 */
public class DataFileNotFoundException extends Exception {

    private String error;

    /**
     * Constructor for this exception, with error message.
     *
     * @param errorMessage
     */
    public DataFileNotFoundException(String errorMessage) {
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
