package mk.ukim.finki.wp.lab.model.dto;

import mk.ukim.finki.wp.lab.model.domain.Activity;
import mk.ukim.finki.wp.lab.model.enums.ActivityType;
import mk.ukim.finki.wp.lab.service.application.ActivityApplicationService;

import java.time.LocalDateTime;

public record DisplayActivityDto(String name, LocalDateTime createdAt, ActivityType type) {

    public static DisplayActivityDto createDisplayActivityDto(Activity activity) {
        return new DisplayActivityDto(activity.getName(), activity.getCreatedAt(), activity.getActivityType());
    }
}