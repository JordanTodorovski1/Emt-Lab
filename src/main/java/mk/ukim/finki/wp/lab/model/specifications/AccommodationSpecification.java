package mk.ukim.finki.wp.lab.model.specifications;

import jakarta.persistence.criteria.Predicate;
import mk.ukim.finki.wp.lab.model.domain.Accommodation;
import mk.ukim.finki.wp.lab.model.enums.Category;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class AccommodationSpecification {

    public static Specification<Accommodation> filtersAccommodations(
            Category category, Long hostId, Long countryId, Integer numRooms, Boolean available
    ) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (category != null) {
                predicates.add(cb.equal(root.get("category"), category));
            }

            if (hostId != null) {
                predicates.add(cb.equal(root.get("host").get("id"), hostId));
            }

            if (countryId != null) {
                predicates.add(cb.equal(root.get("host").get("country").get("id"), countryId));
            }

            if (numRooms != null) {
                predicates.add(cb.equal(root.get("numRooms"), numRooms));
            }

            if (available != null) {
                if (available) {
                    predicates.add(cb.greaterThan(root.get("numRooms"), 0));
                } else {
                    predicates.add(cb.equal(root.get("numRooms"), 0));
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}