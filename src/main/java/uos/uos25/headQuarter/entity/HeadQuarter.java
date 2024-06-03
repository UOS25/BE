package uos.uos25.headQuarter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.uos25.common.BaseEntity;
import uos.uos25.shop.entity.Shop;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class HeadQuarter {
    @Id @GeneratedValue
    @Column(nullable = false)
    private Long hqEmpId;

    @Column(nullable = false)
    private String hqEmpName;

    @Column(nullable = false)
    private String hqEmpHp;

    @OneToMany(mappedBy = "headQuarter", fetch = FetchType.LAZY)
    private List<Shop> shops = new ArrayList<>();

    @Builder
    public HeadQuarter(String hqEmpName, String hqEmpHp) {
        this.hqEmpName = hqEmpName;
        this.hqEmpHp = hqEmpHp;
    }
}
