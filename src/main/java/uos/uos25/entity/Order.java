package uos.uos25.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Order {
    @Id
    private Integer orderId;

    private LocalDateTime orderDate;
    @Column(length = 20)
    private String orderStatus;
    private Integer givenEa;
    private Integer orderEa;
    @Column(length = 18)
    private String check;
}
