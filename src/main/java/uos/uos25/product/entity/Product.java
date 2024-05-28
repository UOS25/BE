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

}
