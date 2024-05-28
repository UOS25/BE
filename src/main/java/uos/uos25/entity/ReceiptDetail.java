package uos.uos25.entity;

import jakarta.persistence.*;
import uos.uos25.product.entity.Product;
import uos.uos25.receipt.entity.Receipt;

@Entity
public class ReceiptDetail {
    @Id
    @Column(length = 20)
    private String receiptDetail;
    private Integer ea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receipt_id")
    private Receipt receipt;
}
