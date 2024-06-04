package uos.uos25.event.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.uos25.product.entity.Product;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
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

    @Builder
    public Event(LocalDateTime startDate, LocalDateTime endDate, String eventName, String eventCategory, Product product) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventName = eventName;
        this.eventCategory = eventCategory;
        this.product = product;
    }
}
