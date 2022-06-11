package cinema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author professorik
 * @created 11/06/2022 - 17:29
 * @project Cinema Room REST Service
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AlreadySoldException extends ServiceException{
    public AlreadySoldException() {
        super("The ticket has been already purchased!");
    }
}

