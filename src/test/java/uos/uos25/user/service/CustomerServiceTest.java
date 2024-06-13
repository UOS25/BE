package uos.uos25.user.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import uos.uos25.user.dto.request.CustomerCreateRequestDTO;
import uos.uos25.user.entity.Customer;
import uos.uos25.user.exception.CustomerAlreadyExists;
import uos.uos25.user.repository.CustomerRepository;

@SpringBootTest
@Transactional
class CustomerServiceTest {
    @Autowired CustomerService customerService;
    @Autowired CustomerRepository customerRepository;

    @BeforeEach
    void beforeEach() {
        customerRepository.save(new Customer("010-4732-4347", "유현승1"));
        customerRepository.save(new Customer("010-4732-4348", "유현승2"));
        customerRepository.save(new Customer("010-4732-4349", "유현승3"));
    }

    @Test
    public void createUser() {
        // given
        CustomerCreateRequestDTO customerCreateRequestDTO =
                new CustomerCreateRequestDTO("010-4732-4342", "유현승");

        // when
        Customer savedCustomer = customerService.createCustomer(customerCreateRequestDTO);

        // then
        assertThat(savedCustomer.getPhoneNumber()).isEqualTo("010-4732-4342");
        assertThat(savedCustomer.getNickname()).isEqualTo("유현승");
    }

    @Test
    public void 유저_생성_시_전화번호가_중복되면_예외를_발생한다() {
        // given
        CustomerCreateRequestDTO customerCreateRequestDTO =
                new CustomerCreateRequestDTO("010-4732-4348", "유현승");

        // when, then
        org.assertj.core.api.Assertions.assertThatThrownBy(
                        () -> customerService.createCustomer(customerCreateRequestDTO))
                .isInstanceOf(CustomerAlreadyExists.class);
    }

    @Test
    public void 유저_조회() {
        // when
        Customer customer = customerService.findById("010-4732-4348");

        // then
        assertThat(customer.getNickname()).isEqualTo("유현승2");
    }

    @Test
    public void 유저_리스트_조회() {
        // given

        // when
        List<Customer> customers = customerService.findAllCustomers();

        // then
        assertThat(customers.size()).isEqualTo(3);
    }
}
