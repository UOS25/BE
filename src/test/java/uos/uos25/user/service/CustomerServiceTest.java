package uos.uos25.user.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import uos.uos25.user.repository.CustomerRepository;

@SpringBootTest
class CustomerServiceTest {

    @Autowired private CustomerService customerService;

    @MockBean private CustomerRepository customerRepository;

    @Test
    void createCustomer() {}

    @Test
    void findAllCustomers() {}

    @Test
    void findById() {}

    @Test
    void updateCustomer() {}

    @Test
    void earnMileage() {}

    @Test
    void deleteCustomer() {}
}
