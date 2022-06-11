package cinema.config;

import cinema.pojo.Theater;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author professorik
 * @created 11/06/2022 - 16:11
 * @project Cinema Room REST Service
 */
@Configuration
public class CinemaRoomConfig {
    @Bean
    Theater cinemaRoom(
            @Value("${cinema.room.rows}") int rows,
            @Value("${cinema.room.columns}") int columns){
        return new Theater(rows,columns);
    }
}
