package cinema.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author professorik
 * @created 31/03/2022 - 20:27
 * @project Cinema Room REST Service
 */
public class Theater {
    private final int totalRows;
    private final int totalColumns;
    private final Map<Integer, Boolean> seats;
    private final Map<String, Seat> tickets;

    public Theater(int totalRows, int totalColumns) {
        this.seats = new ConcurrentHashMap<>();
        this.tickets = new ConcurrentHashMap<>();
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= totalColumns; j++) {
                seats.put(getIndex(i, j), true);
            }
        }
    }

    public Ticket takeSeat(Seat seat) {
        seats.put(getIndex(seat), false);
        String token = UUID.randomUUID().toString();
        Seat res = new PricedSeat(seat);
        tickets.put(token, res);
        return new Ticket(token, res);
    }

    public boolean isAvailable(Seat seat) {
        return seats.get(getIndex(seat));
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
            seats.put(getIndex(seat), true);
        }
        return seat;
    }

    private Integer getIndex(int i, int j) {
        return i * totalColumns + j;
    }

    private Integer getIndex(Seat seat) {
        return getIndex(seat.getRow(), seat.getColumn());
    }

    public int getTotal_rows() {
        return totalRows;
    }

    public int getTotal_columns() {
        return totalColumns;
    }

    public List<PricedSeat> getAvailable_seats() {
        List<PricedSeat> result = new ArrayList<>();
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= totalColumns; j++) {
                if (seats.get(getIndex(i, j))) {
                    result.add(new PricedSeat(i, j));
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
        List<PricedSeat> seatList = getAvailable_seats();
        int sum = 0;
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= totalColumns; j++) {
                if (!seats.get(getIndex(i, j))) {
                    sum += new PricedSeat(i, j).getPrice();
                }
            }
        }
        stats.setCurrent_income(sum);
        stats.setNumber_of_available_seats(seatList.size());
        stats.setNumber_of_purchased_tickets(totalColumns * totalRows - seatList.size());
        return stats;
    }
}
