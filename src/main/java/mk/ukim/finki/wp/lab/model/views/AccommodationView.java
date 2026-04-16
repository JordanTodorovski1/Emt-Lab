package mk.ukim.finki.wp.lab.model.views;

import jakarta.persistence.*;
import lombok.Getter;
import mk.ukim.finki.wp.lab.model.enums.Category;
import org.hibernate.annotations.Immutable;

@Entity
@Getter
@Immutable
@Table(name = "accommodation_view")
public class AccommodationView {

    @Id
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Integer numRooms;

    private String hostName;

    private String hostSurname;

    private String countryName;
}