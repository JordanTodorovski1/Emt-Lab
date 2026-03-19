package mk.ukim.finki.wp.lab.service.application.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.lab.model.domain.Reservation;
import mk.ukim.finki.wp.lab.model.dto.CreateReservationDto;
import mk.ukim.finki.wp.lab.model.dto.DisplayReservationDto;
import mk.ukim.finki.wp.lab.service.application.ReservationApplicationService;
import mk.ukim.finki.wp.lab.service.domain.ReservationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationApplicationServiceImpl implements ReservationApplicationService {

    private final ReservationService reservationService;

    @Override
    public DisplayReservationDto createReservation(CreateReservationDto dto) {
        Reservation reservation = reservationService.create(
                dto.dateFrom(),
                dto.dateTo(),
                dto.userIds(),
                dto.accommodationId()
        );

        return DisplayReservationDto.from(reservation);
    }

    @Override
    public List<DisplayReservationDto> findAll() {
        return reservationService.findAll()
                .stream()
                .map(DisplayReservationDto::from)
                .toList();
    }

    @Override
    public Optional<DisplayReservationDto> findById(Long id) {
        return reservationService.findById(id)
                .map(DisplayReservationDto::from);
    }

    @Override
    public DisplayReservationDto deleteById(Long id) {
        Optional<Reservation> reservation = reservationService.findById(id);

        if (reservation.isEmpty()) {
            throw new RuntimeException("Reservation not found");
        }

        reservationService.deleteById(id);

        return DisplayReservationDto.from(reservation.get());
    }
}