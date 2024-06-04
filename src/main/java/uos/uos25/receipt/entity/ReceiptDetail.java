package uos.uos25.receipt.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.uos25.product.entity.Product;
import uos.uos25.purchase.dto.ItemInfo;

@Entity
@Getter
@NoArgsConstructor
public class ReceiptDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Builder
    public ReceiptDetail(Long receiptDetail, Integer ea, Product product, Receipt receipt) {
        this.receiptDetail = receiptDetail;
        this.ea = ea;
        this.product = product;
        this.receipt = receipt;
    }
}
