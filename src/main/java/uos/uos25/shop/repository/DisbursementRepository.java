package uos.uos25.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import uos.uos25.shop.entity.Disbursement;

@Repository
public interface DisbursementRepository extends JpaRepository<Disbursement, Long> {
    List<Disbursement> findAllByShopShopId(Long shopId);

    @Query(
            "SELECT d FROM Shop s JOIN Disbursement d ON s.shopId = d.shop.shopId JOIN Employee e ON e.shop.shopId = s.shopId")
    List<Disbursement> findAllByEmployeeId(Long employeeId);

    @Query(
            "SELECT COUNT(d) > 0 FROM Disbursement d JOIN Shop s ON d.shop.shopId = s.shopId JOIN Employee e ON d.shop.shopId = e.shop.shopId WHERE e.employeeId = :employeeId AND YEAR(d.createdAt) = :year AND MONTH(d.createdAt) = :month")
    Boolean existsByEmployeeAndYearAndMonth(
            @Param("employeeId") Long employeeId,
            @Param("year") int year,
            @Param("month") int month);
}
