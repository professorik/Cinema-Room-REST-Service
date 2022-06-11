package cinema.pojo;

import lombok.Getter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author professorik
 * @created 31/03/2022 - 20:27
 * @project Cinema Room REST Service
 */
public class Theater {
    @Getter private final int totalRows;
    @Getter private final int totalColumns;
    private final Set<Integer> seats;
    private final Map<String, Seat> tickets;

    public Theater(int totalRows, int totalColumns) {
        this.seats = new ConcurrentSkipListSet<>();
        this.tickets = new ConcurrentHashMap<>();
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= totalColumns; j++) {
                seats.add(new Seat(i, j).hashCode());
            }
        }
    }

    public Ticket takeSeat(Seat seat) {
        seats.remove(seat.hashCode());
        String token = UUID.randomUUID().toString();
        tickets.put(token, seat);
        return new Ticket(token, seat);
    }

    public boolean isAvailable(Seat seat) {
        return seats.contains(seat.hashCode());
    }

    public boolean isInBounds(Seat seat) {
        return isInBound(1, totalColumns, seat.getColumn()) && isInBound(1, totalRows, seat.getRow());
    }

    public Seat getTicketByToken(String token) {
        return tickets.get(token);
    }

    public Seat refundTicketByToken(String token) {
        Seat seat = getTicketByToken(token);
        if (seat != null) {
            tickets.remove(token);
            seats.add(seat.hashCode());
        }
        return seat;
    }

    public List<Seat> getAvailableSeats() {
        List<Seat> result = new ArrayList<>();
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= totalColumns; j++) {
                var tmp = new Seat(i, j);
                if (seats.contains(tmp.hashCode())) {
                    result.add(tmp);
                }
            }
        }
        return result;
    }

    private boolean isInBound(int left, int right, int num) {
        return Math.abs(2 * num - left - right) <= right - left;
    }

    public Stats stats() {
        Stats stats = new Stats();
        List<Seat> seatList = getAvailableSeats();
        int sum = 0;
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= totalColumns; j++) {
                var tmp = new Seat(i, j);
                if (!seats.contains(tmp.hashCode())) {
                    sum += tmp.getPrice();
                }
            }
        }
        stats.setCurrentIncome(sum);
        stats.setNumberOfAvailableSeats(seatList.size());
        stats.setNumberOfPurchasedTickets(totalColumns * totalRows - seatList.size());
        return stats;
    }
}
