package uos.uos25.product.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.uos25.common.BaseEntity;
import uos.uos25.disposal.entity.Disposal;
import uos.uos25.entity.Event;
import uos.uos25.receipt.entity.ReceiptDetail;
import uos.uos25.returns.entity.Returns;
import uos.uos25.inventory.entity.Inventory;
import uos.uos25.orders.entity.Orders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Product extends BaseEntity {
    @Id @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false)
    private String enterprise;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String barcode;

    @Column(nullable = false)
    private Integer customerPrice;

    @Column(nullable = false)
    private Integer orderPrice;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String feature;

    @Column(nullable = false)
    private LocalDateTime expirationDate;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Orders> orders = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Disposal> disposals = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Returns> returnses = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Inventory> inventories = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ReceiptDetail> receiptDetails = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Event> events = new ArrayList<>();

    @Builder
    public Product(Long productId, String enterprise, String productName, String barcode, Integer customerPrice, Integer orderPrice, String category, String description, String feature, LocalDateTime expirationDate) {
        this.productId = productId;
        this.enterprise = enterprise;
        this.productName = productName;
        this.barcode = barcode;
        this.customerPrice = customerPrice;
        this.orderPrice = orderPrice;
        this.category = category;
        this.description = description;
        this.feature = feature;
        this.expirationDate = expirationDate;
    }
}
