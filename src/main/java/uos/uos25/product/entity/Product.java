package uos.uos25.product.entity;

import jakarta.persistence.*;
import lombok.Getter;
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

}
