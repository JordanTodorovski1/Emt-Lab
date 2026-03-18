package mk.ukim.finki.wp.lab.service.application.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.lab.model.domain.Accommodation;
import mk.ukim.finki.wp.lab.model.domain.Host;
import mk.ukim.finki.wp.lab.model.dto.CreateAccommodationDto;
import mk.ukim.finki.wp.lab.model.dto.DisplayAccommodationDto;
import mk.ukim.finki.wp.lab.service.application.AccommodationApplicationService;
import mk.ukim.finki.wp.lab.service.domain.AccommodationService;
import mk.ukim.finki.wp.lab.service.domain.HostService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {

    private final AccommodationService accommodationService;
    private final HostService hostService;

    @Override
    public DisplayAccommodationDto createAccommodation(CreateAccommodationDto createAccommodationDto) {
        Host host = hostService.findById(createAccommodationDto.hostId());
        Accommodation accommodation = createAccommodationDto.createAccommodation(host);
        Accommodation savedAccommodation = accommodationService.createAccommodation(accommodation);
        return DisplayAccommodationDto.createAccommodationDto(savedAccommodation);
    }

    @Override
    public DisplayAccommodationDto updateAccommodation(Long id, CreateAccommodationDto createAccommodationDto) {
        Host host = hostService.findById(createAccommodationDto.hostId());
        Accommodation accommodation = createAccommodationDto.createAccommodation(host);
        Accommodation savedAccommodation = accommodationService.updateAccommodation(id,accommodation);
        return DisplayAccommodationDto.createAccommodationDto(savedAccommodation);

    }

    @Override
    public DisplayAccommodationDto deleteAccommodation(Long id) {
        Accommodation savedAccommodation = accommodationService.deleteAccommodation(id);
        return DisplayAccommodationDto.createAccommodationDto(savedAccommodation);
    }

    @Override
    public DisplayAccommodationDto makeUnavailableAccommodation(Long id) {
        Accommodation savedAccommodation = accommodationService.makeUnavailableAccommodation(id);
        return DisplayAccommodationDto.createAccommodationDto(savedAccommodation);
    }
}
