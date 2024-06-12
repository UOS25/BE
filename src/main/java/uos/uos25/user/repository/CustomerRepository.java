package uos.uos25.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uos.uos25.user.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {}
