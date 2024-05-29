package uos.uos25.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uos.uos25.Employee.entity.Employee;
import uos.uos25.common.BaseEntity;
import uos.uos25.disposal.entity.Disposal;
import uos.uos25.headQuarter.entity.HeadQuarter;
import uos.uos25.inventory.entity.Inventory;
import uos.uos25.orders.entity.Orders;
import uos.uos25.returns.entity.Returns;
import uos.uos25.sale.entity.Sales;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Shop extends BaseEntity {
    @Id @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shopId;

    @Column(nullable = false)
    private String shopName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String relationship;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HQ_Emp_id", nullable = false)
    private HeadQuarter headQuarter;

    @OneToMany(mappedBy = "shop")
    private List<Sales> sales = new ArrayList<>();

    @OneToMany(mappedBy = "shop")
    private List<Employee> employees = new ArrayList<>();

    @OneToMany(mappedBy = "shop")
    private List<Orders> orders = new ArrayList<>();

    @OneToMany(mappedBy = "shop")
    private List<Disposal> disposals = new ArrayList<>();

    @OneToMany(mappedBy = "shop")
    private List<Returns> returnses = new ArrayList<>();

    @OneToMany(mappedBy = "shop")
    private List<Inventory> inventories = new ArrayList<>();

}
