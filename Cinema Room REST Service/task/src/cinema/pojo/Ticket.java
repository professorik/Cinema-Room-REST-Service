package cinema.pojo;

/**
 * @author professorik
 * @created 03/04/2022 - 16:48
 * @project Cinema Room REST Service
 */
public class Ticket {
    private String token;
    private Seat ticket;

    public Ticket(String token, Seat ticket) {
        this.token = token;
        this.ticket = ticket;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }
}
