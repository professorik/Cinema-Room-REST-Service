package cinema.pojo;

import lombok.*;

/**
 * @author professorik
 * @created 31/03/2022 - 20:28
 * @project Cinema Room REST Service
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
    private int row;
    private int column;

    public int getPrice() {
        return row <= 4? 10: 8;
    }
}
