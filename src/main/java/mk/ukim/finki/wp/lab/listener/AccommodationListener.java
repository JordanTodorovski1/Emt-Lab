package mk.ukim.finki.wp.lab.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.wp.lab.events.AccommodationNotAvailableEvent;
import mk.ukim.finki.wp.lab.events.ActivityEvent;
import mk.ukim.finki.wp.lab.model.domain.Activity;
import mk.ukim.finki.wp.lab.repository.ActivityRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class AccommodationListener {

    private final ActivityRepository activityRepository;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void logBookNotAvailable(AccommodationNotAvailableEvent event){
        log.info("Accommodation with id " + event.id() + " is not available.");
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void logBookNotAvailable(ActivityEvent event){
        Activity activity = new Activity(event.name(),event.createdAt(),event.type());
        activityRepository.save(activity);
        log.info("Accommodation rented.");
    }
}
