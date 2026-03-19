package mk.ukim.finki.wp.lab.service.domain.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.lab.model.domain.Accommodation;
import mk.ukim.finki.wp.lab.model.domain.Reservation;
import mk.ukim.finki.wp.lab.model.domain.User;
import mk.ukim.finki.wp.lab.repository.AccommodationRepository;
import mk.ukim.finki.wp.lab.repository.ReservationRepository;
import mk.ukim.finki.wp.lab.repository.UserRepository;
import mk.ukim.finki.wp.lab.service.domain.ReservationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final AccommodationRepository accommodationRepository;

    @Override
    public Reservation create(LocalDate dateFrom, LocalDate dateTo, List<Long> userIds, Long accommodationId) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId)
                .orElseThrow(() -> new RuntimeException("Accommodation not found"));

        List<User> users = userRepository.findAllById(userIds);

        if (users.isEmpty()) {
            throw new RuntimeException("Users not found");
        }

        Reservation reservation = new Reservation(dateFrom, dateTo, accommodation);
        reservation.getUsers().addAll(users);

        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }
}