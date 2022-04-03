package cinema.controller;

import cinema.pojo.Seat;
import cinema.pojo.Stats;
import cinema.pojo.Theater;
import cinema.pojo.Ticket;
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
    public static Theater theater;

    @GetMapping("/seats")
    public Theater getSeatsInfo(){
        return theater;
    }

    @PostMapping("/purchase")
    public ResponseEntity<Ticket> purchaseSeat(@RequestBody Seat seat) {
        if (!theater.isInBounds(seat)){
            return new ResponseEntity(Map.of("error", "The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
        }else if (!theater.isAvailable(seat)){
            return new ResponseEntity(Map.of("error", "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(theater.takeSeat(seat));
    }

    @PostMapping("/return")
    public ResponseEntity<Map<String, Seat>> returnTicket(@RequestBody Map<String, String> token){
        Seat temp = theater.refundTicketByToken(token.get("token"));
        if (temp == null){
            return new ResponseEntity(Map.of("error", "Wrong token!"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(Map.of("returned_ticket", temp));
    }

    @PostMapping("/stats")
    public ResponseEntity<Stats> getStats(@RequestParam(required = false) String password){
        if (password != null && password.equals("super_secret")){
            return ResponseEntity.ok(theater.stats());
        }
        return new ResponseEntity(Map.of("error", "The password is wrong!"), HttpStatus.UNAUTHORIZED);
    }
}
