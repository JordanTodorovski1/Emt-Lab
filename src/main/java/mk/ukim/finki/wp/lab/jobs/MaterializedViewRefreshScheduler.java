package mk.ukim.finki.wp.lab.jobs;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.wp.lab.repository.AccommodationPreviewViewRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class MaterializedViewRefreshScheduler {

    private final AccommodationPreviewViewRepository accommodationPreviewViewRepository;

    @Scheduled(cron = "* * * * * *")
    @Transactional
    public void refreshProductCatalogView() {
        log.info("Refreshing ACCOMMODATION_PREVIEW_VIEW...");
        accommodationPreviewViewRepository.refresh();
        log.info("ACCOMMODATION_PREVIEW_VIEW successfully refreshed.");
    }
}