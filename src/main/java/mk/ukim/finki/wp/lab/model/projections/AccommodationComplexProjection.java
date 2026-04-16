package mk.ukim.finki.wp.lab.model.projections;

import mk.ukim.finki.wp.lab.model.enums.Category;

public interface AccommodationComplexProjection {
    Long getId();
    String getName();
    Category getCategory();
    Integer getNumRooms();
    String getHostName();
    String getHostSurname();
    String getHostCountryName();
}
