package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}