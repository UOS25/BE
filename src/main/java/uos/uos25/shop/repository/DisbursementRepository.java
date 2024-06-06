package uos.uos25.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uos.uos25.shop.entity.Disbursement;

@Repository
public interface DisbursementRepository extends JpaRepository<Disbursement, Long> {
    List<Disbursement> findAllByShopShopId(Long shopId);
}
