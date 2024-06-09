package uos.uos25.disposal.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uos.uos25.disposal.entity.Disposal;

@Repository
public interface DisposalRepository extends JpaRepository<Disposal, Long> {
    List<Disposal> findByCreatedAtBetweenAndShopShopId(
            LocalDateTime start, LocalDateTime end, Long shopId);

    List<Disposal> findAllByShopShopId(Long shopId);
}
