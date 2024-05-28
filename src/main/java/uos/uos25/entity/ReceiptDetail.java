package uos.uos25.entity;

import jakarta.persistence.*;
import uos.uos25.product.entity.Product;
import uos.uos25.receipt.entity.Receipt;

@Entity
public class ReceiptDetail {

    @Id @GeneratedValue
    @Column(nullable = false)
    private Long receiptDetail;

    @Column(nullable = false)
    private Integer ea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receipt_id", nullable = false)
    private Receipt receipt;
}
