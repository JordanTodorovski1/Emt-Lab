package mk.ukim.finki.wp.lab.service.application;

import mk.ukim.finki.wp.lab.model.dto.DisplayActivityDto;
import org.springframework.data.domain.Page;

public interface ActivityApplicationService {
    Page<DisplayActivityDto> findAllWithPagination(int page,int size);
}
