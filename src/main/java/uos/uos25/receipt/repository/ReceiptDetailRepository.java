package uos.uos25.receipt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uos.uos25.receipt.entity.Receipt;
import uos.uos25.receipt.entity.ReceiptDetail;

import java.util.List;

@Repository
public interface ReceiptDetailRepository extends JpaRepository<ReceiptDetail, Long> {
    public List<ReceiptDetail> findAllByReceiptReceiptId(Long receiptId);
}
