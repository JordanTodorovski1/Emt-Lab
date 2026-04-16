package mk.ukim.finki.wp.lab.service.application.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.lab.model.domain.Accommodation;
import mk.ukim.finki.wp.lab.model.domain.Host;
import mk.ukim.finki.wp.lab.model.dto.CreateAccommodationDto;
import mk.ukim.finki.wp.lab.model.dto.DisplayAccommodationDto;
import mk.ukim.finki.wp.lab.model.enums.Category;
import mk.ukim.finki.wp.lab.model.projections.AccommodationComplexProjection;
import mk.ukim.finki.wp.lab.model.views.AccommodationPreviewView;
import mk.ukim.finki.wp.lab.model.views.AccommodationView;
import mk.ukim.finki.wp.lab.service.application.AccommodationApplicationService;
import mk.ukim.finki.wp.lab.service.domain.AccommodationService;
import mk.ukim.finki.wp.lab.service.domain.HostService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Override
    public Page<DisplayAccommodationDto> getAllWithPagination(Category category, Long hostId, Long countryId, Integer numRooms, Boolean available, int page, int size, String sortBy) {
        return accommodationService.getAllWithPagination(category, hostId, countryId, numRooms, available, page, size, sortBy).map(DisplayAccommodationDto::createAccommodationDto);
    }

    @Override
    public List<AccommodationComplexProjection> getAccommodationComplexProjection() {
        return accommodationService.getAccommodationComplexProjection();
    }

    @Override
    public DisplayAccommodationDto findByIdWithEntityGraph(Long id) {
        Accommodation accommodation = accommodationService.findById(id);
        return DisplayAccommodationDto.createAccommodationDto(accommodation);
    }

    @Override
    public List<AccommodationView> findAllWithView() {
        return accommodationService.findAllWithView();
    }

    @Override
    public List<AccommodationPreviewView> findAllWithMaterializedView() {
        return accommodationService.findAllWithMaterializedView();
    }

    @Override
    public DisplayAccommodationDto rentAccommodation(Long id) {
        Accommodation accommodation = accommodationService.rentAccommodation(id);
        return DisplayAccommodationDto.createAccommodationDto(accommodation);
    }

    @Override
    public List<DisplayAccommodationDto> findTop10NewestAccommodations() {
        return accommodationService.findTop10NewestAccommodations()
                .stream()
                .map(DisplayAccommodationDto::createAccommodationDto)
                .toList();
    }
}
