package cinema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author professorik
 * @created 11/06/2022 - 16:59
 * @project Cinema Room REST Service
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidTokenException extends ServiceException {
    public InvalidTokenException() {
        super("Wrong token!");
    }
}
