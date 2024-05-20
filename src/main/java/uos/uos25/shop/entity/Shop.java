package uos.uos25.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uos.uos25.Employee.entity.Employee;
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
public class Shop {
    @Id @GeneratedValue
    @Column(length = 30)
    private Long shopId;

    @Column(length = 20) // shopName 조회를 위해 추가. 00점
    private String shopName;

    @Column(length = 30)
    private String address;

    @Column(length = 18)
    private String relationship;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HQ_Emp_id")
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
