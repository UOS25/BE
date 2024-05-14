package uos.uos25.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "product")
    private List<Purchase> purchases = new ArrayList<>();
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
