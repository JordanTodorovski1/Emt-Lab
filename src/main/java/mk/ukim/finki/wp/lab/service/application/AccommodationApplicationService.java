package mk.ukim.finki.wp.lab.service.application;

import mk.ukim.finki.wp.lab.model.domain.Accommodation;
import mk.ukim.finki.wp.lab.model.dto.CreateAccommodationDto;
import mk.ukim.finki.wp.lab.model.dto.DisplayAccommodationDto;
import mk.ukim.finki.wp.lab.model.enums.Category;
import mk.ukim.finki.wp.lab.model.projections.AccommodationComplexProjection;
import mk.ukim.finki.wp.lab.model.views.AccommodationPreviewView;
import mk.ukim.finki.wp.lab.model.views.AccommodationView;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AccommodationApplicationService {
    DisplayAccommodationDto createAccommodation(CreateAccommodationDto createAccommodationDto);
    DisplayAccommodationDto updateAccommodation(Long id,CreateAccommodationDto createAccommodationDto);
    DisplayAccommodationDto deleteAccommodation(Long id);
    DisplayAccommodationDto makeUnavailableAccommodation(Long id);
    Page<DisplayAccommodationDto> getAllWithPagination(Category category, Long hostId, Long countryId, Integer numRooms, Boolean available, int page, int size, String sortBy);
    List<AccommodationComplexProjection> getAccommodationComplexProjection();
    DisplayAccommodationDto findByIdWithEntityGraph(Long id);
    List<AccommodationView> findAllWithView();
    List<AccommodationPreviewView> findAllWithMaterializedView();
    DisplayAccommodationDto rentAccommodation(Long id);
}
