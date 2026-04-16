package mk.ukim.finki.wp.lab.service.domain.impl;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.lab.model.domain.Activity;
import mk.ukim.finki.wp.lab.model.dto.DisplayActivityDto;
import mk.ukim.finki.wp.lab.repository.ActivityRepository;
import mk.ukim.finki.wp.lab.service.domain.ActivityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;
    @Override
    public Page<Activity> findAllWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return activityRepository.findAll(pageable);
    }
}
