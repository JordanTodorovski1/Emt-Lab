package mk.ukim.finki.wp.lab.model.dto;

import java.time.LocalDate;
import java.util.List;

public record CreateReservationDto(
        LocalDate dateFrom,
        LocalDate dateTo,
        List<Long> userIds,
        Long accommodationId
) { }