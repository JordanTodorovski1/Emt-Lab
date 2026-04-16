package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.domain.Accommodation;
import mk.ukim.finki.wp.lab.model.projections.AccommodationComplexProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long>, JpaSpecificationExecutor<Accommodation> {
    @Query(value = "select a.id, a.name, a.category, a.num_rooms, h.name as host_name, h.surname as host_surname, c.name as host_country_name\n" +
            "from accommodations a join hosts h on a.host_id = h.id\n" +
            "join countries c on h.country_id = c.id\n", nativeQuery = true)
    List<AccommodationComplexProjection> getAccommodationComplexProjection();

    @EntityGraph(value = "accommodation-entity-graph", type = EntityGraph.EntityGraphType.FETCH)
    Optional<Accommodation> findWithHostById(Long id);

    List<Accommodation> findTop10ByOrderByDateStartedWorkingDesc();
}