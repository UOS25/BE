package uos.uos25.customer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import uos.uos25.common.BaseEntity;
import uos.uos25.receipt.entity.Receipt;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer extends BaseEntity {
    @Id
    @Column(length = 30)
    private Long customerId;

    @Column(length = 30)
    private String passwd;
    @Column(length = 30)
    private String nickname;
    private LocalDateTime joinDate;
    private Integer mileage;

    @OneToMany(mappedBy = "customer")
    private List<Receipt> receipts = new ArrayList<>();
}
