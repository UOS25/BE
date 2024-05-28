package uos.uos25.product.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.uos25.common.BaseEntity;
import uos.uos25.disposal.entity.Disposal;
import uos.uos25.entity.Event;
import uos.uos25.entity.ReceiptDetail;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(length = 20)
    private String enterprise;
    @Column(length = 20)
    private String productName;
    @Column(length = 20)
    private String barcode;
    private Integer customerPrice;
    private Integer orderPrice;
    @Column(length = 30)
    private String category;
    @Column(length = 50)
    private String description;
    @Column(length = 20)
    private String feature;
    private LocalDateTime expirationDate;

    @OneToMany(mappedBy = "product")
    private List<Orders> orders = new ArrayList<>();
    @OneToMany(mappedBy = "product")
    private List<Disposal> disposals = new ArrayList<>();
    @OneToMany(mappedBy = "product")
    private List<Returns> returnses = new ArrayList<>();
    @OneToMany(mappedBy = "product")
    private List<Inventory> inventories = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<ReceiptDetail> receiptDetails = new ArrayList<>();

    @OneToMany(mappedBy = "product")
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
