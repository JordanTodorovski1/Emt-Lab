package mk.ukim.finki.wp.lab.model.views;

import jakarta.persistence.*;
import lombok.Getter;
import mk.ukim.finki.wp.lab.model.enums.Category;
import org.hibernate.annotations.Immutable;

@Entity
@Getter
@Immutable
@Table(name = "accommodation_preview_view")
public class AccommodationPreviewView {

    @Id
    @Enumerated(EnumType.STRING)
    private Category category;

    private Integer numberOfAccommodations;

    private Integer numberOfRooms;

    private float averageNumberOfRooms;
}