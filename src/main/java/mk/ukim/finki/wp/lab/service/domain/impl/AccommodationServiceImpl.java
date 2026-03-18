package mk.ukim.finki.wp.lab.service.domain.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.lab.model.domain.Accommodation;
import mk.ukim.finki.wp.lab.model.enums.State;
import mk.ukim.finki.wp.lab.model.exception.AccommodationNotFoundException;
import mk.ukim.finki.wp.lab.model.exception.InvalidOperationException;
import mk.ukim.finki.wp.lab.repository.AccommodationRepository;
import mk.ukim.finki.wp.lab.service.domain.AccommodationService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;

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
    public Accommodation makeUnavailableAccommodation(Long id) {
        Accommodation accommodation = findById(id);
        accommodation.setNumRooms(0);
        return accommodationRepository.save(accommodation);
    }
}
