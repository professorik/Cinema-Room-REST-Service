package cinema.pojo;

/**
 * @author professorik
 * @created 03/04/2022 - 18:11
 * @project Cinema Room REST Service
 */
public class Stats {
    private int currentIncome;
    private int numberOfAvailableSeats;
    private int numberOfPurchasedTickets;

    public Stats() {
    }

    public int getCurrentIncome() {
        return currentIncome;
    }

    public void setCurrentIncome(int current_income) {
        this.currentIncome = current_income;
    }

    public int getNumberOfAvailableSeats() {
        return numberOfAvailableSeats;
    }

    public void setNumberOfAvailableSeats(int number_of_available_seats) {
        this.numberOfAvailableSeats = number_of_available_seats;
    }

    public int getNumberOfPurchasedTickets() {
        return numberOfPurchasedTickets;
    }

    public void setNumberOfPurchasedTickets(int number_of_purchased_tickets) {
        this.numberOfPurchasedTickets = number_of_purchased_tickets;
    }
}
