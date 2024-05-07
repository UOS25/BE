package uos.uos25.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Receipt {
    @Id
    @Column(length = 20)
    private String receiptId;
    private LocalDateTime purchaseDate;
    @Column(length = 18)
    private String purchaseStatus;
    private Integer age;
    @Column(length = 18)
    private String gender;
}
