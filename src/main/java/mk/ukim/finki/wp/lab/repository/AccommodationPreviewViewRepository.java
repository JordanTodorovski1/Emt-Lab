package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.enums.Category;
import mk.ukim.finki.wp.lab.model.views.AccommodationPreviewView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationPreviewViewRepository extends JpaRepository<AccommodationPreviewView, Category> {
    @Modifying
    @Query(value = "call accommodation_preview_view()", nativeQuery = true)
    void refresh();
}