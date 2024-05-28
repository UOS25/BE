package uos.uos25.disposal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uos.uos25.disposal.entity.Disposal;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DisposalRepository extends JpaRepository<Disposal, Long> {
    List<Disposal> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}
