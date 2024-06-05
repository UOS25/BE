package uos.uos25.returns.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uos.uos25.returns.entity.Returns;

@Repository
public interface ReturnsRepository extends JpaRepository<Returns, Long> {
    public List<Returns> findAllByShop_ShopId(Long shopId);
}
