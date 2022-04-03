package cinema;

import cinema.controller.SeatsController;
import cinema.pojo.Theater;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        SeatsController.theater = new Theater(9, 9);
    }
}
