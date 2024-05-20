package uos.uos25.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uos.uos25.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
