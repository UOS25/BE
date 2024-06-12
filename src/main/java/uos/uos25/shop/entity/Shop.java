package uos.uos25.shop.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.uos25.common.BaseEntity;
import uos.uos25.headquarter.entity.HeadQuarter;
import uos.uos25.order.entity.Disposal;
import uos.uos25.order.entity.Orders;
import uos.uos25.order.entity.Returns;
import uos.uos25.user.entity.Employee;

@Entity
@Getter
@NoArgsConstructor
public class Shop extends BaseEntity {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shopId;

    @Column(length = 50, nullable = false)
    private String shopName;

    @Column(nullable = false)
    private String address;

    @Column(length = 50, nullable = false)
    private String relationship;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HQ_Emp_id", nullable = false)
    private HeadQuarter headQuarter;

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    private List<Employee> employees = new ArrayList<>();

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    private List<Orders> orders = new ArrayList<>();

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    private List<Disposal> disposals = new ArrayList<>();

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    private List<Returns> returnses = new ArrayList<>();

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    private List<Inventory> inventories = new ArrayList<>();

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    private List<Disbursement> disbursements = new ArrayList<>();

    @Builder
    public Shop(String shopName, String address, String relationship, HeadQuarter headQuarter) {
        this.shopName = shopName;
        this.address = address;
        this.relationship = relationship;
        this.headQuarter = headQuarter;
    }
}
