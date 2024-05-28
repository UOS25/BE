package uos.uos25.customer.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import uos.uos25.common.BaseEntity;
import uos.uos25.receipt.entity.Receipt;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer extends BaseEntity {

    @Id @GeneratedValue
    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private String passwd;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private LocalDateTime joinDate;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Integer mileage;

    @OneToMany(mappedBy = "customer")
    private List<Receipt> receipts = new ArrayList<>();
}
