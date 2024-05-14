package uos.uos25.entity;

import jakarta.persistence.*;
import uos.uos25.product.entity.Product;

import java.time.LocalDateTime;

@Entity
public class Event {
    @Id
    @Column(length = 20)
    private String eventId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Column(length = 18)
    private String eventName;
    @Column(length = 18)
    private String eventCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
