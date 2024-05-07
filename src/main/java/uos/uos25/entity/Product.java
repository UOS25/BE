package uos.uos25.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Product {
    @Id
    @Column(length = 20)
    private String productId;

    @Column(length = 20)
    private String enterprise;
    @Column(length = 20)
    private String productName;
    @Column(length = 20)
    private String barcode;
    private Long customerPrice;
    private Long orderPrice;
    @Column(length = 30)
    private String category;
    @Column(length = 50)
    private String description;
    @Column(length = 20)
    private String feature;
    private LocalDateTime expirationDate;

}
