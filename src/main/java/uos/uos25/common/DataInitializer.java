package uos.uos25.common;

import java.time.LocalDateTime;

import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import uos.uos25.headquarter.entity.Event;
import uos.uos25.headquarter.entity.HeadQuarter;
import uos.uos25.headquarter.entity.Product;
import uos.uos25.headquarter.repository.EventRepository;
import uos.uos25.headquarter.repository.HeadQuarterRepository;
import uos.uos25.headquarter.repository.ProductRepository;
import uos.uos25.order.entity.Disposal;
import uos.uos25.order.entity.Orders;
import uos.uos25.order.entity.OrdersStatus;
import uos.uos25.order.entity.Returns;
import uos.uos25.order.entity.ReturnsStatus;
import uos.uos25.order.repository.DisposalRepository;
import uos.uos25.order.repository.OrdersRepository;
import uos.uos25.order.repository.ReturnsRepository;
import uos.uos25.shop.entity.Disbursement;
import uos.uos25.shop.entity.DisbursementType;
import uos.uos25.shop.entity.Inventory;
import uos.uos25.shop.entity.Shop;
import uos.uos25.shop.repository.DisbursementRepository;
import uos.uos25.shop.repository.InventoryRepository;
import uos.uos25.shop.repository.ShopRepository;
import uos.uos25.user.entity.Customer;
import uos.uos25.user.entity.Employee;
import uos.uos25.user.entity.EmployeePosition;
import uos.uos25.user.entity.EmployeeWorkingHistory;
import uos.uos25.user.entity.PartTime;
import uos.uos25.user.receipt.entity.Receipt;
import uos.uos25.user.receipt.entity.ReceiptDetail;
import uos.uos25.user.receipt.repository.ReceiptDetailRepository;
import uos.uos25.user.receipt.repository.ReceiptRepository;
import uos.uos25.user.repository.CustomerRepository;
import uos.uos25.user.repository.EmployeeRepository;
import uos.uos25.user.repository.EmployeeWorkingHistoryRepository;

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
    private final ReceiptRepository receiptRepository;
    private final ReceiptDetailRepository receiptDetailRepository;
    private final DisbursementRepository disbursementRepository;

    @PostConstruct
    public void init() {
        Product product = createProductDummy();
        HeadQuarter headQuarter = createHeadQuarter();
        Shop shop = createShop(headQuarter);
        Orders orders = createOrders(shop, product);
        Returns returns = createReturnsDummy(shop, product);
        Employee employee = createEmployeeDummy(shop);
        createEmployeeBossDummy(shop);
        //        EmployeeWorkingHistory employeeWorkingHistory =
        // createEmployeeWorkingHistoryDummy(employee);
        Disposal disposal = createDisposalDummy(shop, product);
        Customer customer = createCustomerDummy();
        Inventory inventory = createInventoryDummy(product, shop);
        Event event = createEvent(product);
        Receipt receipt = createReceiptDummy(employee, customer);
        ReceiptDetail receiptDetail = createReceiptDetailDummy(receipt, product);
        ReceiptDetail receiptDetail2 = createReceiptDetailDummy(receipt, product);
        Disbursement disbursementSalary = createDisbursementSalaryDummy(shop);
        Disbursement disbursementRoyalty = createDisbursementRoyaltyDummy(shop);

        // 생활 서비스 데이터 추가
        createLivingServiceDummy();
    }

    private void createLivingServiceDummy() {
        Product lottery =
                Product.builder()
                        .barcode("Lottery")
                        .enterprise("Corp. Lottery")
                        .productName("로또")
                        .customerPrice(1000)
                        .orderPrice(0)
                        .category("생활서비스")
                        .description("로또이다.")
                        .feature("생활서비스")
                        .expirationDate(LocalDateTime.now().plusYears(1L))
                        .build();
        Product delivery =
                Product.builder()
                        .barcode("Delivery")
                        .enterprise("Corp. Delivery")
                        .productName("택배")
                        .customerPrice(5000)
                        .orderPrice(0)
                        .category("생활서비스")
                        .description("택배 서비스이다.")
                        .feature("생활서비스")
                        .expirationDate(LocalDateTime.now().plusYears(1L))
                        .build();
        Product atm =
                Product.builder()
                        .barcode("ATM")
                        .enterprise("Corp. ATM")
                        .productName("ATM")
                        .customerPrice(1000)
                        .orderPrice(0)
                        .category("생활서비스")
                        .description("ATM 서비스이다.")
                        .feature("생활서비스")
                        .expirationDate(LocalDateTime.now().plusYears(1L))
                        .build();
        Product electricityBill =
                Product.builder()
                        .barcode("ElectricityBill")
                        .enterprise("Corp. UtilityBill")
                        .productName("전기요금")
                        .customerPrice(200000)
                        .orderPrice(0)
                        .category("생활서비스")
                        .description("전기요금 수납이다.")
                        .feature("생활서비스")
                        .expirationDate(LocalDateTime.now().plusYears(1L))
                        .build();
        Product waterBill =
                Product.builder()
                        .barcode("WaterBill")
                        .enterprise("Corp. UtilityBill")
                        .productName("수도요금")
                        .customerPrice(10000)
                        .orderPrice(0)
                        .category("생활서비스")
                        .description("수도요금 수납이다.")
                        .feature("생활서비스")
                        .expirationDate(LocalDateTime.now().plusYears(1L))
                        .build();
        Product gasBill =
                Product.builder()
                        .barcode("GasBill")
                        .enterprise("Corp. UtilityBill")
                        .productName("가스요금")
                        .customerPrice(30000)
                        .orderPrice(0)
                        .category("생활서비스")
                        .description("가스요금 수납이다.")
                        .feature("생활서비스")
                        .expirationDate(LocalDateTime.now().plusYears(1L))
                        .build();

        productRepository.save(lottery);
        productRepository.save(delivery);
        productRepository.save(atm);
        productRepository.save(electricityBill);
        productRepository.save(waterBill);
        productRepository.save(gasBill);
    }

    private Product createProductDummy() {
        // product dummy
        Product product =
                Product.builder()
                        .barcode("barcode")
                        .enterprise("enterprise")
                        .productName("name")
                        .customerPrice(2000)
                        .orderPrice(1000)
                        .category("category")
                        .description("description")
                        .feature("feature")
                        .expirationDate(LocalDateTime.now())
                        .build();
        return productRepository.save(product);
    }

    private HeadQuarter createHeadQuarter() {
        // HQ dummy
        HeadQuarter headQuarter =
                HeadQuarter.builder().hqEmpName("김본사").hqEmpHp("010-1234-1234").build();
        return headQuarterRepository.save(headQuarter);
    }

    private Shop createShop(HeadQuarter headQuarter) {
        // Shop dummy
        Shop shop =
                Shop.builder()
                        .shopName("시립점")
                        .address("서울특별시 시립로 22")
                        .relationship("직영점")
                        .headQuarter(headQuarter)
                        .build();
        return shopRepository.save(shop);
    }

    private Orders createOrders(Shop shop, Product product) {
        // Orders dummy
        Orders orders =
                Orders.builder()
                        .ordersStatus(OrdersStatus.REQUEST.getStatus())
                        .givenEa(10)
                        .ordersEa(10)
                        .shop(shop)
                        .product(product)
                        .build();
        return ordersRepository.save(orders);
    }

    private Returns createReturnsDummy(Shop shop, Product product) {
        // Returns dummy
        Returns returns =
                Returns.builder()
                        .ea(10)
                        .returnsStatus(ReturnsStatus.REFUNDED.getStatus())
                        .shop(shop)
                        .product(product)
                        .build();
        return returnsRepository.save(returns);
    }

    private Employee createEmployeeDummy(Shop shop) {
        // Employee dummy
        Employee employee =
                Employee.builder()
                        .employeeName("박직원")
                        .employmentDate(LocalDateTime.now().withNano(0))
                        .position(EmployeePosition.EMPLOYEE.getPosition())
                        .registrationNumber("9902221056715")
                        .salary(10000)
                        .partTime(PartTime.DAY)
                        .account("1231234123412")
                        .bank("농협은행")
                        .shop(shop)
                        .build();
        return employeeRepository.save(employee);
    }

    private Employee createEmployeeBossDummy(Shop shop) {
        // Employee dummy
        Employee employee =
                Employee.builder()
                        .employeeName("시립대사장")
                        .employmentDate(LocalDateTime.now().withNano(0))
                        .position(EmployeePosition.BOSS.getPosition())
                        .registrationNumber("9902221056715")
                        .salary(10000)
                        .partTime(PartTime.DAY)
                        .account("1231234123412")
                        .bank("농협은행")
                        .shop(shop)
                        .build();
        return employeeRepository.save(employee);
    }

    private EmployeeWorkingHistory createEmployeeWorkingHistoryDummy(Employee employee) {
        // EmployeeWorkingHistory dummy
        EmployeeWorkingHistory empWorkingHistory =
                EmployeeWorkingHistory.builder()
                        .employee(employee)
                        .startDateTime(LocalDateTime.now().withNano(0))
                        .endDateTime(LocalDateTime.now().plusHours(8L).withNano(0))
                        .workingHour(8L)
                        .build();
        return employeeWorkingHistoryRepository.save(empWorkingHistory);
    }

    private Disposal createDisposalDummy(Shop shop, Product product) {
        // Disposal dummy
        Disposal disposal = Disposal.builder().shop(shop).product(product).ea(10).build();
        return disposalRepository.save(disposal);
    }

    private Customer createCustomerDummy() {
        // Customer dummy
        Customer customer = Customer.builder().phoneNumber("010-4732-4348").nickname("김고객").build();
        return customerRepository.save(customer);
    }

    private Inventory createInventoryDummy(Product product, Shop shop) {
        // Inventory dummy
        Inventory inventory =
                Inventory.builder()
                        .product(product)
                        .shop(shop)
                        .ea(10)
                        .display(5)
                        .warehousingDate(LocalDateTime.now())
                        .expirationDate(LocalDateTime.now().plusDays(7L))
                        .build();
        return inventoryRepository.save(inventory);
    }

    private Event createEvent(Product product) {
        Event event =
                Event.builder()
                        .product(product)
                        .eventName("이벤트")
                        .eventPrice(500)
                        .eventCategory("카테고리")
                        .startDate(LocalDateTime.now())
                        .endDate(LocalDateTime.now().plusDays(7L))
                        .build();
        return eventRepository.save(event);
    }

    private Receipt createReceiptDummy(Employee employee, Customer customer) {
        Receipt receipt =
                Receipt.builder()
                        .employee(employee)
                        .customer(customer)
                        .age(20)
                        .gender("FEMALE")
                        .purchaseStatus("구매완료")
                        .build();
        return receiptRepository.save(receipt);
    }

    private ReceiptDetail createReceiptDetailDummy(Receipt receipt, Product product) {
        ReceiptDetail receiptDetail =
                ReceiptDetail.builder().product(product).receipt(receipt).ea(10).build();
        return receiptDetailRepository.save(receiptDetail);
    }

    private Disbursement createDisbursementSalaryDummy(Shop shop) {
        Disbursement disbursement =
                Disbursement.builder()
                        .disburseAmount(10000)
                        .disburseType(DisbursementType.SALARY.getType())
                        .shop(shop)
                        .build();
        return disbursementRepository.save(disbursement);
    }

    private Disbursement createDisbursementRoyaltyDummy(Shop shop) {
        Disbursement disbursement =
                Disbursement.builder()
                        .disburseAmount(5000000)
                        .disburseType(DisbursementType.ROYALTY.getType())
                        .shop(shop)
                        .build();
        return disbursementRepository.save(disbursement);
    }
}
