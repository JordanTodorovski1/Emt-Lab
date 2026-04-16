package mk.ukim.finki.wp.lab.controller;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.lab.model.dto.DisplayActivityDto;
import mk.ukim.finki.wp.lab.service.application.ActivityApplicationService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityApplicationService activityApplicationService;

    @GetMapping("/activities")
    public ResponseEntity<Page<DisplayActivityDto>> findAllWithPagination(            @RequestParam(defaultValue = "0") int page,
                                                                                      @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(activityApplicationService.findAllWithPagination(page, size));

    }
}