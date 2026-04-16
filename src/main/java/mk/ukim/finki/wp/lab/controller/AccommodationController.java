package mk.ukim.finki.wp.lab.controller;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.lab.model.domain.Reservation;
import mk.ukim.finki.wp.lab.model.dto.CreateAccommodationDto;
import mk.ukim.finki.wp.lab.model.dto.CreateReservationDto;
import mk.ukim.finki.wp.lab.model.dto.DisplayAccommodationDto;
import mk.ukim.finki.wp.lab.model.dto.DisplayReservationDto;
import mk.ukim.finki.wp.lab.model.enums.Category;
import mk.ukim.finki.wp.lab.model.projections.AccommodationComplexProjection;
import mk.ukim.finki.wp.lab.model.views.AccommodationPreviewView;
import mk.ukim.finki.wp.lab.model.views.AccommodationView;
import mk.ukim.finki.wp.lab.service.application.AccommodationApplicationService;
import mk.ukim.finki.wp.lab.service.application.ReservationApplicationService;
import mk.ukim.finki.wp.lab.service.domain.AccommodationService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/pagination")
    public ResponseEntity<Page<DisplayAccommodationDto>> getBooksWithPagination(
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) Long hostId,
            @RequestParam(required = false) Long countryId,
            @RequestParam(required = false) Integer numRooms,
            @RequestParam(required = false) Boolean available,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy
    ) {
        return ResponseEntity.ok(accommodationApplicationService.getAllWithPagination(category, hostId, countryId, numRooms, available, page, size, sortBy));
    }
    @GetMapping("/projection")
    public ResponseEntity<List<AccommodationComplexProjection>> AccommodationComplexProjection(){
        return ResponseEntity.ok(accommodationApplicationService.getAccommodationComplexProjection());
    }

    @GetMapping("/entity-graph/{id}")
    public ResponseEntity<DisplayAccommodationDto> findByIdWithEntityGraph(@PathVariable Long id){
        return ResponseEntity.ok(accommodationApplicationService.findByIdWithEntityGraph(id));
    }
    @GetMapping("/view")
    public ResponseEntity<List<AccommodationView>> findAllWithView(){
        return ResponseEntity.ok(accommodationApplicationService.findAllWithView());
    }
    @GetMapping("/materialized-view")
    public ResponseEntity<List<AccommodationPreviewView>> findAllWithMaterializedView(){
        return ResponseEntity.ok(accommodationApplicationService.findAllWithMaterializedView());
    }
    @PostMapping("/rent-accommodation/{id}")
    public ResponseEntity<DisplayAccommodationDto> rentAccommodation(@PathVariable Long id){
        return  ResponseEntity.ok(accommodationApplicationService.rentAccommodation(id));
    }
    @GetMapping("/newest10")
    public ResponseEntity<?> findTop10NewestAccommodations() {
        try {
            return ResponseEntity.ok(accommodationApplicationService.findTop10NewestAccommodations());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
