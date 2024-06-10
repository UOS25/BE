package uos.uos25.product.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.uos25.common.BaseEntity;
import uos.uos25.disposal.entity.Disposal;
import uos.uos25.event.entity.Event;
import uos.uos25.inventory.entity.Inventory;
import uos.uos25.orders.entity.Orders;
import uos.uos25.receipt.entity.ReceiptDetail;
import uos.uos25.returns.entity.Returns;

@Entity
@Table(indexes = {@Index(name = "I_PRODUCT_01", columnList = "product_name")})
@Getter
@NoArgsConstructor
public class Product extends BaseEntity {
    @Id
    @Column(length = 50, nullable = false)
    private String barcode;

    @Column(length = 50, nullable = false)
    private String productName;

    @Column(length = 50, nullable = false)
    private String enterprise;

    @Column(nullable = false)
    private Integer customerPrice;

    @Column(nullable = false)
    private Integer orderPrice;

    @Column(length = 50, nullable = false)
    private String category;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String feature;

    @Column(nullable = false, columnDefinition = "TIMESTAMP(6)")
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
    public Product(
            String barcode,
            String productName,
            String enterprise,
            Integer customerPrice,
            Integer orderPrice,
            String category,
            String description,
            String feature,
            LocalDateTime expirationDate) {
        this.barcode = barcode;
        this.enterprise = enterprise;
        this.productName = productName;
        this.customerPrice = customerPrice;
        this.orderPrice = orderPrice;
        this.category = category;
        this.description = description;
        this.feature = feature;
        this.expirationDate = expirationDate;
    }

    public Integer getPrice() {
        int sumOfEventPrice = events.stream().mapToInt(Event::getEventPrice).sum();

        return customerPrice - sumOfEventPrice;
    }
}
