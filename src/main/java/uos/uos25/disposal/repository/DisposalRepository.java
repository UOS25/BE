package uos.uos25.disposal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uos.uos25.disposal.entity.Disposal;

@Repository
public interface DisposalRepository extends JpaRepository<Disposal, Long> {
}
