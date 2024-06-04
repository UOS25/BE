package uos.uos25.common;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uos.uos25.employee.entity.Employee;
import uos.uos25.employee.entity.EmployeeWorkingHistory;
import uos.uos25.employee.entity.PartTime;
import uos.uos25.employee.repository.EmployeeRepository;
import uos.uos25.employee.repository.EmployeeWorkingHistoryRepository;
import uos.uos25.customer.entity.Customer;
import uos.uos25.customer.repository.CustomerRepository;
import uos.uos25.disposal.entity.Disposal;
import uos.uos25.disposal.repository.DisposalRepository;
import uos.uos25.headQuarter.entity.HeadQuarter;
import uos.uos25.headQuarter.repository.HeadQuarterRepository;
import uos.uos25.inventory.entity.Inventory;
import uos.uos25.inventory.repository.InventoryRepository;
import uos.uos25.orders.entity.Orders;
import uos.uos25.orders.repository.OrdersRepository;
import uos.uos25.product.entity.Product;
import uos.uos25.product.repository.ProductRepository;
import uos.uos25.returns.entity.Returns;
import uos.uos25.returns.repository.ReturnsRepository;
import uos.uos25.shop.entity.Shop;
import uos.uos25.shop.repository.ShopRepository;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final ProductRepository productRepository;
    private final HeadQuarterRepository headQuarterRepository;
    private final ShopRepository shopRepository;
    private final OrdersRepository ordersRepository;
    private final ReturnsRepository returnsRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployeeWorkingHistoryRepository employeeWorkingHistoryRepository;
    private final DisposalRepository disposalRepository;
    private final CustomerRepository customerRepository;
    private final InventoryRepository inventoryRepository;

    @PostConstruct
    public void init(){
        // product dummy
        Product product = Product.builder()
                .enterprise("enterprise")
                .productName("name")
                .barcode("barcode")
                .customerPrice(1000)
                .orderPrice(2000)
                .category("category")
                .description("description")
                .feature("feature")
                .expirationDate(LocalDateTime.now())
                .build();
        Product savedProduct = productRepository.save(product);

        Product product2 = Product.builder()
                .enterprise("enterprise")
                .productName("name2")
                .barcode("barcode2")
                .customerPrice(3000)
                .orderPrice(4000)
                .category("category")
                .description("description2")
                .feature("feature")
                .expirationDate(LocalDateTime.now())
                .build();
        Product savedProduct2 = productRepository.save(product2);

        // HQ dummy
        HeadQuarter headQuarter = HeadQuarter.builder()
                .hqEmpName("김본사")
                .hqEmpHp("01012341234")
                .build();
        HeadQuarter savedHQ = headQuarterRepository.save(headQuarter);

        // Shop dummy
        Shop shop = Shop.builder()
                .shopName("시립점")
                .address("서울특별시 시립로 22")
                .relationship("직영점")
                .headQuarter(savedHQ)
                .build();
        Shop savedShop = shopRepository.save(shop);

        // Orders dummy
        Orders orders = Orders.builder()
                .ordersStatus("주문 완료")
                .givenEa(10)
                .ordersEa(10)
                .ordersCheck("검품 이전")
                .shop(savedShop)
                .product(savedProduct)
                .build();
        Orders savedOrders = ordersRepository.save(orders);

        // Returns dummy
        Returns returns = Returns.builder()
                .ea(10)
                .returnsStatus("반품 시작")
                .shop(savedShop)
                .product(savedProduct)
                .build();
        Returns savedReturns = returnsRepository.save(returns);

        // Employee dummy
        Employee employee = Employee.builder()
                .employeeName("박직원")
                .employmentDate(LocalDateTime.now().withNano(0))
                .position("점원")
                .registrationNumber("123123-1231231")
                .salary(10000)
                .partTime(PartTime.DAY)
                .account("1231234123412")
                .shop(savedShop)
                .build();
        Employee savedEmployee = employeeRepository.save(employee);

        // EmployeeWorkingHistory dummy
        EmployeeWorkingHistory empWorkingHistory = EmployeeWorkingHistory.builder()
                .employee(savedEmployee)
                .startDateTime(LocalDateTime.now().withNano(0))
                .endDateTime(LocalDateTime.now().plusHours(8L).withNano(0))
                .workingHour(8L)
                .build();
        EmployeeWorkingHistory savedHistory = employeeWorkingHistoryRepository.save(empWorkingHistory);

        // Disposal dummy
        Disposal disposal = Disposal.builder()
                .shop(savedShop)
                .product(savedProduct)
                .ea(10)
                .build();
        Disposal savedDisposal = disposalRepository.save(disposal);

        // Customer dummy
        Customer customer = Customer.builder()
                .phoneNumber("01012341234")
                .nickname("김고객")
                .build();
        Customer savedCustomer = customerRepository.save(customer);

        Customer customer2 = Customer.builder()
                .phoneNumber("01047324348")
                .nickname("유현승")
                .build();
        Customer savedCustomer2 = customerRepository.save(customer2);

        // Inventory dummy
        Inventory inventory = Inventory.builder()
                .product(product)
                .shop(shop)
                .ea(10)
                .display(0)
                .warehousingDate(LocalDateTime.now())
                .expirationDate(LocalDateTime.now().plusDays(7L))
                .build();
        Inventory savedInventory = inventoryRepository.save(inventory);
    }
}
