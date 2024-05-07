package uos.uos25.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class HeadQuarter {
    @Id
    @Column(length = 20)
    private String hqEmpId;

    @Column(length = 10)
    private String hqEmpName;

    @Column(length = 30)
    private String hqEmpHp;

    @OneToMany(mappedBy = "headQuarter")
    private List<Shop> shops = new ArrayList<>();
}
