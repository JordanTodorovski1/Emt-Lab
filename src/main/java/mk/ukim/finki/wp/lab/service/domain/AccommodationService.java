package mk.ukim.finki.wp.lab.service.domain;

import mk.ukim.finki.wp.lab.model.domain.Accommodation;

public interface AccommodationService {
    Accommodation findById(Long id);
    Accommodation createAccommodation(Accommodation accommodation);
    Accommodation updateAccommodation(Long id,Accommodation accommodation);
    Accommodation deleteAccommodation(Long id);
    Accommodation makeUnavailableAccommodation(Long id);
}
