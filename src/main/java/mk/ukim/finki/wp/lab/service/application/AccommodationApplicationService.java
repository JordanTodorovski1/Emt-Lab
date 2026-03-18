package mk.ukim.finki.wp.lab.service.application;

import mk.ukim.finki.wp.lab.model.dto.CreateAccommodationDto;
import mk.ukim.finki.wp.lab.model.dto.DisplayAccommodationDto;

public interface AccommodationApplicationService {
    DisplayAccommodationDto createAccommodation(CreateAccommodationDto createAccommodationDto);
    DisplayAccommodationDto updateAccommodation(Long id,CreateAccommodationDto createAccommodationDto);
    DisplayAccommodationDto deleteAccommodation(Long id);
    DisplayAccommodationDto makeUnavailableAccommodation(Long id);
}
