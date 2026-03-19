package mk.ukim.finki.wp.lab.model.dto;

import mk.ukim.finki.wp.lab.model.domain.Reservation;

import java.time.LocalDate;
import java.util.List;

public record DisplayReservationDto(
        Long id,
        LocalDate dateFrom,
        LocalDate dateTo,
        List<Long> userIds,
        Long accommodationId
) {

    public static DisplayReservationDto from(Reservation reservation) {
        return new DisplayReservationDto(
                reservation.getId(),
                reservation.getDateFrom(),
                reservation.getDateTo(),
                reservation.getUsers()
                        .stream()
                        .map(user -> user.getId())
                        .toList(),
                reservation.getAccommodation().getId()
        );
    }
}