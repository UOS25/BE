package uos.uos25.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer salesId;

    private LocalDateTime salesDate;
    @Column(length = 18)
    private String salesType;
    private Long amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;
}
