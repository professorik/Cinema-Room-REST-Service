package cinema.exception;

/**
 * @author professorik
 * @created 11/06/2022 - 16:58
 * @project Cinema Room REST Service
 */
public class ServiceException extends Exception {
    public ServiceException(String message) {
        super(message);
    }
}
