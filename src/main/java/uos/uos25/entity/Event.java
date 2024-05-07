package uos.uos25.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Event {
    @Id
    @Column(length = 20)
    private String eventId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Column(length = 18)
    private String eventName;
    @Column(length = 18)
    private String eventCategory;
}
