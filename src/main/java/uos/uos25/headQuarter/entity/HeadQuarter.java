package uos.uos25.headQuarter.entity;

import jakarta.persistence.*;
import uos.uos25.common.BaseEntity;
import uos.uos25.shop.entity.Shop;

import java.util.ArrayList;
import java.util.List;

@Entity
public class HeadQuarter extends BaseEntity {
    @Id @GeneratedValue
    @Column(nullable = false)
    private Long hqEmpId;

    @Column(nullable = false)
    private String hqEmpName;

    @Column(nullable = false)
    private String hqEmpHp;

    @OneToMany(mappedBy = "headQuarter")
    private List<Shop> shops = new ArrayList<>();
}
