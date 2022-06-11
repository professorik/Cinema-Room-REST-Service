package cinema.pojo;

/**
 * @author professorik
 * @created 31/03/2022 - 20:28
 * @project Cinema Room REST Service
 */

public class Seat {
    private int row;
    private int column;

    public Seat(){}

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return row <= 4? 10: 8;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("{");
        sb.append("row=").append(row);
        sb.append(", column=").append(column);
        sb.append('}');
        return sb.toString();
    }
}
