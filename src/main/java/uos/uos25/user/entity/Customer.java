package uos.uos25.user.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.uos25.user.receipt.entity.Receipt;
import uos.uos25.util.MileageUtil;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @Column(columnDefinition = "CHAR(13)", nullable = false)
    private String phoneNumber;

    @Column(length = 50, nullable = false)
    private String nickname;

    @Column(nullable = false, columnDefinition = "TIMESTAMP(6)")
    private LocalDateTime joinDate;

    @Column(nullable = false)
    private Integer mileage = 0;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Receipt> receipts = new ArrayList<>();

    @Builder
    public Customer(String phoneNumber, String nickname) {
        this.phoneNumber = phoneNumber;
        this.nickname = nickname;
        this.joinDate = LocalDateTime.now().withNano(0);
        this.mileage = 0;
    }

    // 고객 정보를 수정하는 메서드
    public void changeCustomerInfo(String nickname) {
        this.nickname = nickname;
    }

    // 고객 마일리지를 적립하는 메서드
    public void earnMileage(Integer price) {
        Integer mileage = MileageUtil.getMileage(price);
        this.mileage += mileage;
    }
}
