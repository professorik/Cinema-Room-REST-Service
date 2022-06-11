package cinema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author professorik
 * @created 11/06/2022 - 17:28
 * @project Cinema Room REST Service
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OutOfBoundsException extends ServiceException{
    public OutOfBoundsException() {
        super("The number of a row or a column is out of bounds!");
    }
}
