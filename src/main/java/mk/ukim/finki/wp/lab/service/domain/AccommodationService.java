package mk.ukim.finki.wp.lab.service.domain;

import mk.ukim.finki.wp.lab.model.domain.Accommodation;
import mk.ukim.finki.wp.lab.model.enums.Category;
import mk.ukim.finki.wp.lab.model.projections.AccommodationComplexProjection;
import mk.ukim.finki.wp.lab.model.views.AccommodationPreviewView;
import mk.ukim.finki.wp.lab.model.views.AccommodationView;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AccommodationService {
    Accommodation findById(Long id);
    Accommodation createAccommodation(Accommodation accommodation);
    Accommodation updateAccommodation(Long id,Accommodation accommodation);
    Accommodation deleteAccommodation(Long id);
    Accommodation makeUnavailableAccommodation(Long id);
    Page<Accommodation> getAllWithPagination(Category category, Long hostId, Long countryId, Integer numRooms, Boolean available, int page, int size, String sortBy);
    List<AccommodationComplexProjection> getAccommodationComplexProjection();
    Accommodation findByIdWithEntityGraph(Long id);
    List<AccommodationView> findAllWithView();
    List<AccommodationPreviewView> findAllWithMaterializedView();
    Accommodation rentAccommodation(Long id);
}
