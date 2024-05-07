package uos.uos25.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "customer")
    private List<Receipt> receipts = new ArrayList<>();
}
