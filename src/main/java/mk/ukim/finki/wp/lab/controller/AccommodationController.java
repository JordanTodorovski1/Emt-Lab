package mk.ukim.finki.wp.lab.controller;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.lab.model.domain.Reservation;
import mk.ukim.finki.wp.lab.model.dto.CreateAccommodationDto;
import mk.ukim.finki.wp.lab.model.dto.CreateReservationDto;
import mk.ukim.finki.wp.lab.model.dto.DisplayAccommodationDto;
import mk.ukim.finki.wp.lab.model.dto.DisplayReservationDto;
import mk.ukim.finki.wp.lab.service.application.AccommodationApplicationService;
import mk.ukim.finki.wp.lab.service.application.ReservationApplicationService;
import mk.ukim.finki.wp.lab.service.domain.AccommodationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationApplicationService accommodationApplicationService;
    private final ReservationApplicationService reservationApplicationService;

    @PostMapping("/")
    public ResponseEntity<DisplayAccommodationDto> createAccommodation(@RequestBody CreateAccommodationDto createAccommodationDto) {
        DisplayAccommodationDto dto = accommodationApplicationService.createAccommodation(createAccommodationDto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisplayAccommodationDto> updateAccommodation(@PathVariable Long id, @RequestBody CreateAccommodationDto createAccommodationDto) {
        DisplayAccommodationDto dto = accommodationApplicationService.updateAccommodation(id, createAccommodationDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DisplayAccommodationDto> deleteAccommodation(@PathVariable Long id) {
        DisplayAccommodationDto dto = accommodationApplicationService.deleteAccommodation(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}/make-unavailable")
    public ResponseEntity<DisplayAccommodationDto> makeUnavailableAccommodation(@PathVariable Long id, @RequestBody CreateAccommodationDto createAccommodationDto) {
        DisplayAccommodationDto dto = accommodationApplicationService.makeUnavailableAccommodation(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/reservations")
    public ResponseEntity<DisplayReservationDto> createReservation(@RequestBody CreateReservationDto createReservationDto) {
        DisplayReservationDto dto = reservationApplicationService.createReservation(createReservationDto);
        return ResponseEntity.ok(dto);
    }
}
