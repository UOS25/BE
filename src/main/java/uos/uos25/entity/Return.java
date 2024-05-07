package uos.uos25.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Return {
    @Id
    private Integer returnId;

    private LocalDateTime returnDate;
    private Integer ea;
    @Column(length = 18)
    private String returnStatus;
}
