package cinema.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author professorik
 * @created 03/04/2022 - 16:48
 * @project Cinema Room REST Service
 */
@Getter
@Setter
@AllArgsConstructor
public class Ticket {
    private String token;
    private Seat ticket;
}
