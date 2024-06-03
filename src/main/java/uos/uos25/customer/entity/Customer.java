package uos.uos25.customer.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import uos.uos25.common.BaseEntity;
import uos.uos25.receipt.entity.Receipt;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Customer {

    @Id @GeneratedValue
    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private String customerHP;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private LocalDateTime joinDate;

    @Column(nullable = false)
    private Integer mileage;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Receipt> receipts = new ArrayList<>();

    @Builder
    public Customer(String customerHP, String nickname) {
        this.customerHP = customerHP;
        this.nickname = nickname;
        this.joinDate = LocalDateTime.now().withNano(0);
        this.mileage = 0;
    }

    // 고객 정보를 수정하는 메서드
    public void changeCustomerInfo(String customerHP, String nickname) {
        this.customerHP = customerHP;
        this.nickname = nickname;
    }

    // 고객 마일리지를 적립하는 메서드
    public void earnMileage(Integer mileage) {
        this.mileage += mileage;
    }

}
