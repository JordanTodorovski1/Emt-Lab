package mk.ukim.finki.wp.lab.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.wp.lab.model.enums.ActivityType;

import java.time.LocalDateTime;

@Entity
@Table(name = "activities")
@Data
@NoArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private ActivityType activityType;

    public Activity( String name, LocalDateTime createdAt, ActivityType type) {
        this.name = name;
        this.createdAt = createdAt;
        this.activityType = type;
    }
}
