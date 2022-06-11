package cinema.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author professorik
 * @created 03/04/2022 - 18:11
 * @project Cinema Room REST Service
 */
@Getter
@Setter
@NoArgsConstructor
public class Stats {
    private int currentIncome;
    private int numberOfAvailableSeats;
    private int numberOfPurchasedTickets;
}
