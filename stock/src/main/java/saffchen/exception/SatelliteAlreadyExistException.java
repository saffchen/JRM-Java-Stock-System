package saffchen.exception;

/**
 * @author saffchen created on 16.07.2022
 */
public class SatelliteAlreadyExistException extends RuntimeException {
    public SatelliteAlreadyExistException(String messageError) {
        super(messageError);
    }
}
