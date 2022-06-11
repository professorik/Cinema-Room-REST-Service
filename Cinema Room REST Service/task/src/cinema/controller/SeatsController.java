package cinema.controller;

import cinema.exception.*;
import cinema.pojo.Seat;
import cinema.pojo.Stats;
import cinema.pojo.Theater;
import cinema.pojo.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author professorik
 * @created 31/03/2022 - 20:26
 * @project Cinema Room REST Service
 */
@RestController
public class SeatsController {
    @Autowired
    Theater theater;
    @Value("${cinema.room.password}")
    String password;

    @GetMapping("/seats")
    public Theater getSeatsInfo() {
        return theater;
    }

    @PostMapping("/purchase")
    public ResponseEntity<Ticket> purchaseSeat(@RequestBody Seat seat) throws ServiceException {
        if (!theater.isInBounds(seat)) {
            throw new OutOfBoundsException();
        } else if (!theater.isAvailable(seat)) {
            throw new AlreadySoldException();
        }
        return ResponseEntity.ok(theater.takeSeat(seat));
    }

    @PostMapping("/return")
    public ResponseEntity<Map<String, Seat>> returnTicket(@RequestBody Map<String, String> token) throws InvalidTokenException {
        Seat temp = theater.refundTicketByToken(token.get("token"));
        if (temp == null) {
            throw new InvalidTokenException();
        }
        return ResponseEntity.ok(Map.of("returned_ticket", temp));
    }

    @PostMapping("/stats")
    public ResponseEntity<Stats> getStats(@RequestParam(required = false) String password) throws BadAuthException {
        if (password != null && password.equals(this.password)) {
            return ResponseEntity.ok(theater.stats());
        }
        throw new BadAuthException();
    }

    @ExceptionHandler(BadAuthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    Map<String, String> badAuthHandler(Exception e) {
        return Map.of("error", e.getMessage());
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Map<String, String> errorHandler(Exception e) {
        return Map.of("error", e.getMessage());
    }
}
