package uos.uos25.entity;

import jakarta.persistence.*;
import uos.uos25.product.entity.Product;

import java.time.LocalDateTime;

@Entity
public class Event {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long eventId;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private String eventName;

    @Column(nullable = false)
    private String eventCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
