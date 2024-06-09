package uos.uos25.statistics.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uos.uos25.disposal.dto.response.DisposalGetResponseDTO;
import uos.uos25.employee.entity.Employee;
import uos.uos25.orders.dto.response.OrdersGetResponseDTO;
import uos.uos25.receipt.dto.response.ReceiptGetResponseDTO;
import uos.uos25.receipt.entity.GenderType;
import uos.uos25.receipt.entity.Receipt;
import uos.uos25.returns.dto.response.ReturnsGetResponseDTO;
import uos.uos25.shop.dto.response.DisbursementGetResponseDTO;
import uos.uos25.shop.entity.Shop;
import uos.uos25.shop.service.ShopService;
import uos.uos25.statistics.dto.response.*;
import uos.uos25.statistics.dto.response.StatisticsAgeGetResponseDTO;
import uos.uos25.statistics.dto.response.StatisticsGenderGetResponseDTO;
import uos.uos25.util.DateUtil;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final ShopService shopService;
    private final DateUtil dateUtil;

    public StatisticsSalesGetResponseDTO findSalesByShopId(
            Long shopId, LocalDateTime startDate, LocalDateTime endDate) {
        Shop shop = shopService.findShopById(shopId);
        AtomicInteger totalPrice = new AtomicInteger();

        List<StatisticsDisbursementResponseDTO> disbursements =
                shop.getDisbursements().stream()
                        .filter(
                                disbursement ->
                                        dateUtil.filterBetweenDate(
                                                disbursement, startDate, endDate))
                        .map(
                                disbursement ->
                                        new StatisticsDisbursementResponseDTO(
                                                DisbursementGetResponseDTO.fromEntity(disbursement),
                                                disbursement.getDisburseAmount()))
                        .toList();
        disbursements.stream()
                .forEach(disbursement -> totalPrice.addAndGet(-disbursement.getPrice()));

        List<StatisticsReturnstResponseDTO> returnses =
                shop.getReturnses().stream()
                        .filter(returns -> dateUtil.filterBetweenDate(returns, startDate, endDate))
                        .map(
                                returns ->
                                        new StatisticsReturnstResponseDTO(
                                                ReturnsGetResponseDTO.fromEntity(returns),
                                                returns.getPrice()))
                        .toList();
        returnses.stream().forEach(returns -> totalPrice.addAndGet(returns.getPrice()));

        List<StatisticsOrdersResponseDTO> orderses =
                shop.getOrders().stream()
                        .filter(orders -> dateUtil.filterBetweenDate(orders, startDate, endDate))
                        .map(
                                orders ->
                                        new StatisticsOrdersResponseDTO(
                                                OrdersGetResponseDTO.fromEntity(orders),
                                                orders.getPrice()))
                        .toList();
        orderses.stream().forEach(orders -> totalPrice.addAndGet(-orders.getPrice()));

        List<StatisticsDisposalResponseDTO> disposals =
                shop.getDisposals().stream()
                        .filter(
                                disposal ->
                                        dateUtil.filterBetweenDate(disposal, startDate, endDate))
                        .map(
                                disposal ->
                                        new StatisticsDisposalResponseDTO(
                                                DisposalGetResponseDTO.fromEntity(disposal),
                                                disposal.getPrice()))
                        .toList();
        disposals.stream().forEach(disposal -> totalPrice.addAndGet(-disposal.getPrice()));

        List<StatisticsReceiptResponseDTO> receipts = new ArrayList<>();
        for (Employee employee : shop.getEmployees()) {
            List<StatisticsReceiptResponseDTO> newReceipts =
                    employee.getReceipts().stream()
                            .filter(
                                    receipt ->
                                            dateUtil.filterBetweenDate(receipt, startDate, endDate))
                            .map(
                                    receipt ->
                                            new StatisticsReceiptResponseDTO(
                                                    ReceiptGetResponseDTO.fromEntity(receipt),
                                                    receipt.getPrice()))
                            .toList();
            receipts.addAll(newReceipts);
        }
        receipts.stream().forEach(receipt -> totalPrice.addAndGet(receipt.getPrice()));

        return StatisticsSalesGetResponseDTO.builder()
                .disbursements(disbursements)
                .returnses(returnses)
                .orderses(orderses)
                .disposals(disposals)
                .receipts(receipts)
                .totalPrice(totalPrice.get())
                .build();
    }

    public StatisticsGenderGetResponseDTO findGenderByShopId(
            Long shopId, LocalDateTime startDate, LocalDateTime endDate) {
        Shop shop = shopService.findShopById(shopId);
        AtomicInteger maleTotalPrice = new AtomicInteger();
        AtomicInteger femaleTotalPrice = new AtomicInteger();

        List<Receipt> receipts = new ArrayList<>();
        for (Employee employee : shop.getEmployees()) {
            List<Receipt> newReceipts =
                    employee.getReceipts().stream()
                            .filter(
                                    receipt ->
                                            dateUtil.filterBetweenDate(receipt, startDate, endDate))
                            .toList();

            receipts.addAll(newReceipts);
        }
        receipts.stream()
                .forEach(
                        receipt -> {
                            if (receipt.getGender().equals(GenderType.MALE.getGender()))
                                maleTotalPrice.addAndGet(receipt.getPrice());
                            else femaleTotalPrice.addAndGet(receipt.getPrice());
                        });

        return StatisticsGenderGetResponseDTO.builder()
                .maleTotalPrice(maleTotalPrice.get())
                .femaleTotalPrice(femaleTotalPrice.get())
                .build();
    }

    public StatisticsAgeGetResponseDTO findAgeByShopId(
            Long shopId, LocalDateTime startDate, LocalDateTime endDate) {
        Shop shop = shopService.findShopById(shopId);
        AtomicInteger totalPrice = new AtomicInteger();

        StatisticsAgeGetResponseDTO statisticsAgeGetResponseDTO = new StatisticsAgeGetResponseDTO();
        for (Employee employee : shop.getEmployees()) {
            employee.getReceipts().stream()
                    .filter(receipt -> dateUtil.filterBetweenDate(receipt, startDate, endDate))
                    .forEach(
                            receipt ->
                                    statisticsAgeGetResponseDTO.matchAges(
                                            receipt.getAge(), receipt.getPrice()));
        }

        return statisticsAgeGetResponseDTO;
    }
}
