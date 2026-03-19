package mk.ukim.finki.wp.lab.service.application;

import mk.ukim.finki.wp.lab.model.dto.CreateReservationDto;
import mk.ukim.finki.wp.lab.model.dto.DisplayReservationDto;

import java.util.List;
import java.util.Optional;

public interface ReservationApplicationService {

    DisplayReservationDto createReservation(CreateReservationDto createReservationDto);

    List<DisplayReservationDto> findAll();

    Optional<DisplayReservationDto> findById(Long id);

    DisplayReservationDto deleteById(Long id);
}