package uos.uos25.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Disposal {
    @Id
    private Integer disposalId;

    private LocalDateTime disposalDate;
}
