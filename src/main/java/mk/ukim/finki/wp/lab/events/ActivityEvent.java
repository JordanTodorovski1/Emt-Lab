package mk.ukim.finki.wp.lab.events;

import mk.ukim.finki.wp.lab.model.enums.ActivityType;

import java.time.LocalDateTime;

public record ActivityEvent(String name, LocalDateTime createdAt, ActivityType type) {
}
