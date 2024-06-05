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
import uos.uos25.event.entity.Event;
import uos.uos25.event.repository.EventRepository;
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
    private final EventRepository eventRepository;

    @PostConstruct
    public void init(){
        Product product = createProductDummy();
        HeadQuarter headQuarter = createHeadQuarter();
        Shop shop = createShop(headQuarter);
        Orders orders = createOrders(shop, product);
        Returns returns = createReturnsDummy(shop, product);
        Employee employee = createEmployeeDummy(shop);
        EmployeeWorkingHistory employeeWorkingHistory = createEmployeeWorkingHistoryDummy(employee);
        Disposal disposal = createDisposalDummy(shop, product);
        Customer customer = createCustomerDummy();
        Inventory inventory = createInventoryDummy(product, shop);
        Event event = createEvent(product);
    }

    private Product createProductDummy(){
        // product dummy
        Product product = Product.builder()
                .barcode("barcode")
                .enterprise("enterprise")
                .productName("name")
                .customerPrice(1000)
                .orderPrice(2000)
                .category("category")
                .description("description")
                .feature("feature")
                .expirationDate(LocalDateTime.now())
                .build();
        return productRepository.save(product);
//        Product product2 = Product.builder()
//                .enterprise("enterprise")
//                .productName("name2")
//                .barcode("barcode2")
//                .customerPrice(3000)
//                .orderPrice(4000)
//                .category("category")
//                .description("description2")
//                .feature("feature")
//                .expirationDate(LocalDateTime.now())
//                .build();
//        Product savedProduct2 = productRepository.save(product2);
    }

    private HeadQuarter createHeadQuarter(){
        // HQ dummy
        HeadQuarter headQuarter = HeadQuarter.builder()
                .hqEmpName("김본사")
                .hqEmpHp("01012341234")
                .build();
        return headQuarterRepository.save(headQuarter);
    }

    private Shop createShop(HeadQuarter headQuarter){
        // Shop dummy
        Shop shop = Shop.builder()
                .shopName("시립점")
                .address("서울특별시 시립로 22")
                .relationship("직영점")
                .headQuarter(headQuarter)
                .build();
        return shopRepository.save(shop);
    }

    private Orders createOrders(Shop shop, Product product){
        // Orders dummy
        Orders orders = Orders.builder()
                .ordersStatus("주문 완료")
                .givenEa(10)
                .ordersEa(10)
                .ordersCheck("검품 이전")
                .shop(shop)
                .product(product)
                .build();
        return ordersRepository.save(orders);
    }

    private Returns createReturnsDummy(Shop shop, Product product){
        // Returns dummy
        Returns returns = Returns.builder()
                .ea(10)
                .returnsStatus("반품 시작")
                .shop(shop)
                .product(product)
                .build();
        return returnsRepository.save(returns);
    }

    private Employee createEmployeeDummy(Shop shop){
        // Employee dummy
        Employee employee = Employee.builder()
                .employeeName("박직원")
                .employmentDate(LocalDateTime.now().withNano(0))
                .position("점원")
                .registrationNumber("123123-1231231")
                .salary(10000)
                .partTime(PartTime.DAY)
                .account("1231234123412")
                .shop(shop)
                .build();
        return employeeRepository.save(employee);
    }

    private EmployeeWorkingHistory createEmployeeWorkingHistoryDummy(Employee employee){
        // EmployeeWorkingHistory dummy
        EmployeeWorkingHistory empWorkingHistory = EmployeeWorkingHistory.builder()
                .employee(employee)
                .startDateTime(LocalDateTime.now().withNano(0))
                .endDateTime(LocalDateTime.now().plusHours(8L).withNano(0))
                .workingHour(8L)
                .build();
        return employeeWorkingHistoryRepository.save(empWorkingHistory);
    }

    private Disposal createDisposalDummy(Shop shop, Product product){
        // Disposal dummy
        Disposal disposal = Disposal.builder()
                .shop(shop)
                .product(product)
                .ea(10)
                .build();
        return disposalRepository.save(disposal);
    }

    private Customer createCustomerDummy(){
        // Customer dummy
        Customer customer = Customer.builder()
                .phoneNumber("01012341234")
                .nickname("김고객")
                .build();
        return customerRepository.save(customer);
    }

    private Inventory createInventoryDummy(Product product, Shop shop){
        // Inventory dummy
        Inventory inventory = Inventory.builder()
                .product(product)
                .shop(shop)
                .ea(10)
                .display(0)
                .warehousingDate(LocalDateTime.now())
                .expirationDate(LocalDateTime.now().plusDays(7L))
                .build();
        return inventoryRepository.save(inventory);
    }
    private Event createEvent(Product product){
        Event event = Event.builder()
                .product(product)
                .eventName("이벤트")
                .eventCategory("카테고리")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(7L))
                .build();
        return eventRepository.save(event);
    }
}
