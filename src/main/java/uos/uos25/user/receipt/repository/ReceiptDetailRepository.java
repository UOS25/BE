package uos.uos25.user.receipt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uos.uos25.user.receipt.entity.ReceiptDetail;

@Repository
public interface ReceiptDetailRepository extends JpaRepository<ReceiptDetail, Long> {
    public List<ReceiptDetail> findAllByReceiptReceiptId(Long receiptId);
}
