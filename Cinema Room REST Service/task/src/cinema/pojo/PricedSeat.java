package cinema.pojo;

/**
 * @author professorik
 * @created 03/04/2022 - 15:58
 * @project Cinema Room REST Service
 */
public class PricedSeat extends Seat{
    private int price;

    public PricedSeat(Seat seat){
        super(seat.getRow(), seat.getColumn());
        this.price = seat.getRow() <= 4? 10: 8;
    }

    public PricedSeat(int row, int column){
        super(row, column);
        this.price = row <= 4? 10: 8;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("{");
        sb.append("price=").append(price);
        sb.append(", row=").append(super.getRow());
        sb.append(", col=").append(super.getColumn());
        sb.append('}');
        return sb.toString();
    }
}
