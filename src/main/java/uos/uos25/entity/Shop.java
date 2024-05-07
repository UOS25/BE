package uos.uos25.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Shop {
    @Id
    @Column(length = 30)
    private String shopId;

    @Column(length = 30)
    private String address;

    @Column(length = 18)
    private String relationship;
}
