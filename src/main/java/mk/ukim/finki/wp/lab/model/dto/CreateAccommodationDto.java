package mk.ukim.finki.wp.lab.model.dto;

import mk.ukim.finki.wp.lab.model.domain.Accommodation;
import mk.ukim.finki.wp.lab.model.domain.Host;
import mk.ukim.finki.wp.lab.model.enums.Category;
import mk.ukim.finki.wp.lab.model.enums.State;

public record CreateAccommodationDto(
        String name,
        Category category,
        State state,
        Long hostId,
        Integer numRooms
) {

    public Accommodation createAccommodation(Host host){
        return new Accommodation(
            name,
            category,
            state,
            host,
            numRooms
        );
    }
}
