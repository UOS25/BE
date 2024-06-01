package uos.uos25.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uos.uos25.customer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
