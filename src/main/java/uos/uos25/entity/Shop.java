package uos.uos25.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Shop {
    @Id
    @Column(length = 30)
    private String shopId;

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
    private List<Purchase> purchases = new ArrayList<>();

    @OneToMany(mappedBy = "shop")
    private List<Disposal> disposals = new ArrayList<>();

    @OneToMany(mappedBy = "shop")
    private List<Returns> returnses = new ArrayList<>();

    @OneToMany(mappedBy = "shop")
    private List<Inventory> inventories = new ArrayList<>();

}
