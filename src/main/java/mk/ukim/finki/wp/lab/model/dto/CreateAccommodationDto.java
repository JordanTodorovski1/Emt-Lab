package mk.ukim.finki.wp.lab.model.dto;

import mk.ukim.finki.wp.lab.model.domain.Accommodation;
import mk.ukim.finki.wp.lab.model.domain.Host;
import mk.ukim.finki.wp.lab.model.enums.Category;
import mk.ukim.finki.wp.lab.model.enums.State;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateAccommodationDto(
        String name,
        Category category,
        State state,
        Long hostId,
        Integer numRooms,
        LocalDate dateStartedWorking
) {

    public Accommodation createAccommodation(Host host){
        return new Accommodation(
            name,
            category,
            state,
            host,
            numRooms,
                dateStartedWorking
        );
    }
}
