package uos.uos25.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class HeadQuarter {
    @Id
    @Column(length = 20)
    private String hqEmpId;

    @Column(length = 10)
    private String hqEmpName;

    @Column(length = 30)
    private String hqEmpHp;

}
