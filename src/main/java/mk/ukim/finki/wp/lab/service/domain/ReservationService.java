package mk.ukim.finki.wp.lab.service.domain;

import mk.ukim.finki.wp.lab.model.domain.Reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationService {

    Reservation create(LocalDate dateFrom, LocalDate dateTo, List<Long> userIds, Long accommodationId);

    List<Reservation> findAll();

    Optional<Reservation> findById(Long id);

    void deleteById(Long id);
}