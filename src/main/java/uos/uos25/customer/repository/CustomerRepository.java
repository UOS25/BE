package uos.uos25.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uos.uos25.customer.entity.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
