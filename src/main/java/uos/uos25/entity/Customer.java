package uos.uos25.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Customer {
    @Id
    @Column(length = 30)
    private String customerId;

    @Column(length = 30)
    private String passwd;
    @Column(length = 30)
    private String nickname;
    private LocalDateTime joinDate;
    private Integer mileage;
}
