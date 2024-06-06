package uos.uos25.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uos.uos25.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    Optional<Product> findById(String barcode);

    List<Product> findAllByCategory(String utilityServiceCategory);
}
