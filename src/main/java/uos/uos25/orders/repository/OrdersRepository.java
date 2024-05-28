package uos.uos25.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uos.uos25.orders.entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
