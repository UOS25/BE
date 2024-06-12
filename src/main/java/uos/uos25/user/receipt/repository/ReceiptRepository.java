package uos.uos25.user.receipt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uos.uos25.user.receipt.entity.Receipt;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    List<Receipt> findAllByCustomerPhoneNumber(String phoneNumber);
}
