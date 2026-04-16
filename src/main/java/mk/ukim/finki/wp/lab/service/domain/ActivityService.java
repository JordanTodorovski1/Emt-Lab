package mk.ukim.finki.wp.lab.service.domain;

import mk.ukim.finki.wp.lab.model.domain.Activity;
import mk.ukim.finki.wp.lab.model.dto.DisplayActivityDto;
import org.springframework.data.domain.Page;

public interface ActivityService {
    Page<Activity> findAllWithPagination(int page, int size);
}
