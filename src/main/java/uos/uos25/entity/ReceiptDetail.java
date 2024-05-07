package uos.uos25.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ReceiptDetail {
    @Id
    @Column(length = 20)
    private String receiptDetail;
    private Integer ea;
}
