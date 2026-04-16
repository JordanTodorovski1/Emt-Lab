package mk.ukim.finki.wp.lab.service.domain.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.lab.events.AccommodationNotAvailableEvent;
import mk.ukim.finki.wp.lab.events.ActivityEvent;
import mk.ukim.finki.wp.lab.model.domain.Accommodation;
import mk.ukim.finki.wp.lab.model.domain.Host;
import mk.ukim.finki.wp.lab.model.enums.ActivityType;
import mk.ukim.finki.wp.lab.model.enums.Category;
import mk.ukim.finki.wp.lab.model.enums.State;
import mk.ukim.finki.wp.lab.model.exception.AccommodationNotAvailableException;
import mk.ukim.finki.wp.lab.model.exception.AccommodationNotFoundException;
import mk.ukim.finki.wp.lab.model.exception.InvalidOperationException;
import mk.ukim.finki.wp.lab.model.projections.AccommodationComplexProjection;
import mk.ukim.finki.wp.lab.model.specifications.AccommodationSpecification;
import mk.ukim.finki.wp.lab.model.views.AccommodationPreviewView;
import mk.ukim.finki.wp.lab.model.views.AccommodationView;
import mk.ukim.finki.wp.lab.repository.AccommodationPreviewViewRepository;
import mk.ukim.finki.wp.lab.repository.AccommodationRepository;
import mk.ukim.finki.wp.lab.repository.AccommodationViewRepository;
import mk.ukim.finki.wp.lab.repository.HostRepository;
import mk.ukim.finki.wp.lab.service.domain.AccommodationService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final AccommodationViewRepository accommodationViewRepository;
    private final AccommodationPreviewViewRepository accommodationPreviewViewRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final HostRepository hostRepository;

    @Override
    public Accommodation findById(Long id) {
        Optional<Accommodation> optional = accommodationRepository.findById(id);

        if(optional.isPresent()){
            return optional.get();
        }else{
            throw new AccommodationNotFoundException("AccommodationNotFoundException");
        }
    }

    @Override
    public Accommodation createAccommodation(Accommodation accommodation) {
        return accommodationRepository.save(accommodation);
    }

    @Override
    public Accommodation updateAccommodation(Long id, Accommodation accommodation) {
        Accommodation dbAccommodation = findById(id);

        if(accommodation.getName() != null){
            accommodation.setName(accommodation.getName());
        }
        if(accommodation.getCategory() != null){
            accommodation.setCategory(accommodation.getCategory());
        }
        if(accommodation.getState() != null){
            accommodation.setState(accommodation.getState());
        }
        if(accommodation.getHost() != null){
            accommodation.setHost(accommodation.getHost());
        }
        if(accommodation.getNumRooms() != null){
            accommodation.setNumRooms(accommodation.getNumRooms());
        }
        return accommodationRepository.save(dbAccommodation);
    }

    @Override
    public Accommodation deleteAccommodation(Long id) {
        Accommodation accommodation = findById(id);

        if(accommodation.getState().equals(State.BAD)){
            accommodationRepository.deleteById(id);
            return accommodation;
        }else{
            throw new InvalidOperationException("InvalidOperationException");
        }
    }

    @Override
    @Transactional
    public Accommodation makeUnavailableAccommodation(Long id) {
        Accommodation accommodation = findById(id);
        accommodation.setNumRooms(0);
        Accommodation savedAccommodation =  accommodationRepository.save(accommodation);
        applicationEventPublisher.publishEvent(new AccommodationNotAvailableEvent(savedAccommodation.getId()));
        return savedAccommodation;
    }

    @Override
    public Page<Accommodation> getAllWithPagination(Category category, Long hostId, Long countryId, Integer numRooms, Boolean available, int page, int size, String sortBy) {
        Specification<Accommodation> specification =
                AccommodationSpecification.filtersAccommodations(category, hostId, countryId, numRooms, available);

        Sort sort;

        if ("name".equalsIgnoreCase(sortBy)) {
            sort = Sort.by("name");
        } else {
            sort = Sort.by("createdAt");
        }

        Pageable pageable = PageRequest.of(page, size, sort);

        return accommodationRepository.findAll(specification, pageable);
    }

    @Override
    public List<AccommodationComplexProjection> getAccommodationComplexProjection() {
        return accommodationRepository.getAccommodationComplexProjection();
    }

    @Override
    public Accommodation findByIdWithEntityGraph(Long id) {
        Optional<Accommodation> optional = accommodationRepository.findWithHostById(id);

        if(optional.isPresent()){
            return optional.get();
        }else{
            throw new AccommodationNotFoundException("Accommodation Not Found");
        }
    }

    @Override
    public List<AccommodationView> findAllWithView() {
        return accommodationViewRepository.findAll();
    }

    @Override
    public List<AccommodationPreviewView> findAllWithMaterializedView() {
        return accommodationPreviewViewRepository.findAll();
    }

    @Override
    @Transactional
    public Accommodation rentAccommodation(Long id) {
        Accommodation accommodation = findById(id);
        if(accommodation.getNumRooms() > 0 ){
            accommodation.setNumRooms(accommodation.getNumRooms() - 1);
            Accommodation savedAccommodation = accommodationRepository.save(accommodation);
            applicationEventPublisher.publishEvent(new ActivityEvent(savedAccommodation.getName(), LocalDateTime.now(), ActivityType.ACCOMMODATION_RENTED));
            if(savedAccommodation.getNumRooms() == 0){
                applicationEventPublisher.publishEvent(new AccommodationNotAvailableEvent(savedAccommodation.getId()));
            }

            return savedAccommodation;
        }else{
            throw new AccommodationNotAvailableException("Accommodation is not available.");
        }
    }

    @Override
    public List<Accommodation> findTop10NewestAccommodations() {
        return accommodationRepository.findTop10ByOrderByDateStartedWorkingDesc();
    }

    @Override
    public Accommodation create(String name, Category category, Long hostId, Integer numRooms, LocalDate dateStartedWorking) {
        Host host = hostRepository.findById(hostId)
                .orElseThrow(() -> new RuntimeException("Host not found"));

        Accommodation accommodation = new Accommodation();

        accommodation.setName(name);
        accommodation.setCategory(category);
        accommodation.setNumRooms(numRooms);
        accommodation.setHost(host);
        accommodation.setDateStartedWorking(dateStartedWorking);
        return accommodationRepository.save(accommodation);
    }
    @Override
    public Accommodation update(Long id, String name, Category category, Long hostId, Integer numRooms, LocalDate dateStartedWorking) {
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Accommodation not found"));

        Host host = hostRepository.findById(hostId)
                .orElseThrow(() -> new RuntimeException("Host not found"));

        accommodation.setName(name);
        accommodation.setCategory(category);
        accommodation.setNumRooms(numRooms);
        accommodation.setHost(host);
        accommodation.setDateStartedWorking(dateStartedWorking);

        return accommodationRepository.save(accommodation);
    }
}
