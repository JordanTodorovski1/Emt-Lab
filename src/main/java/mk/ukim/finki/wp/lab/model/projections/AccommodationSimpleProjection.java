package mk.ukim.finki.wp.lab.model.projections;

import mk.ukim.finki.wp.lab.model.enums.Category;

public interface AccommodationSimpleProjection {
    Long getId();
    String getName();
    Category getCategory();
    Integer getNumRooms();
}
