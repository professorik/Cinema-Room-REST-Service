package cinema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author professorik
 * @created 03/04/2022 - 16:03
 * @project Cinema Room REST Service
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class PurchaseException extends Exception {
    public PurchaseException(String message) {
        super(message);
    }
}
