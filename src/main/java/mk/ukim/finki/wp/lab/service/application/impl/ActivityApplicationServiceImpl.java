package mk.ukim.finki.wp.lab.service.application.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.lab.model.dto.DisplayActivityDto;
import mk.ukim.finki.wp.lab.service.application.ActivityApplicationService;
import mk.ukim.finki.wp.lab.service.domain.ActivityService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityApplicationServiceImpl implements ActivityApplicationService {

    private final ActivityService activityService;

    @Override
    public Page<DisplayActivityDto> findAllWithPagination(int page, int size) {
        return activityService.findAllWithPagination(page, size).map(DisplayActivityDto::createDisplayActivityDto);
    }
}