package mk.ukim.finki.wp.lab.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "reservation")
public class Reservation extends BaseAuditableEntity {

    private LocalDate dateFrom;
    private LocalDate dateTo;

    @ManyToMany
    @JoinTable(
            name = "user_reservation",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users = new ArrayList<>();

    @ManyToOne
    private Accommodation accommodation;

    public Reservation(LocalDate dateFrom, LocalDate dateTo, Accommodation accommodation) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.accommodation = accommodation;
    }
}