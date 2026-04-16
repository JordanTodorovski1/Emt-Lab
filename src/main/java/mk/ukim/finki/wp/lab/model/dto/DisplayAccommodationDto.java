package mk.ukim.finki.wp.lab.model.dto;

import mk.ukim.finki.wp.lab.model.domain.Accommodation;
import mk.ukim.finki.wp.lab.model.enums.Category;
import mk.ukim.finki.wp.lab.model.enums.State;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DisplayAccommodationDto(
        String name,
        Category category,
        State state,
        DisplayHostDto host,
        Integer numRooms,
        LocalDate dateStartedWorking
) {
    public static DisplayAccommodationDto createAccommodationDto(Accommodation accommodation ){

       DisplayCountryDto country = new DisplayCountryDto(
               accommodation.getHost().getCountry().getName(),
               accommodation.getHost().getCountry().getContinent()
       );

        DisplayHostDto host = new DisplayHostDto(
                accommodation.getHost().getName(),
                accommodation.getHost().getSurname(),
                country
        );

        return new DisplayAccommodationDto(
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getState(),
                host,
                accommodation.getNumRooms(),
                accommodation.getDateStartedWorking()

        );
    }
}
