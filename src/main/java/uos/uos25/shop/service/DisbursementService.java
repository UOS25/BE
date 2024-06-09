package uos.uos25.shop.service;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uos.uos25.employee.entity.Employee;
import uos.uos25.employee.entity.EmployeeWorkingHistory;
import uos.uos25.employee.service.EmployeeService;
import uos.uos25.shop.dto.request.DisbursementCreateRequestDTO;
import uos.uos25.shop.dto.request.SalaryCalculationRequestDTO;
import uos.uos25.shop.entity.Disbursement;
import uos.uos25.shop.entity.DisbursementType;
import uos.uos25.shop.entity.Shop;
import uos.uos25.shop.exception.DisbursementAlreadyExistsException;
import uos.uos25.shop.repository.DisbursementRepository;
import uos.uos25.util.DateUtil;

@Service
@RequiredArgsConstructor
public class DisbursementService {
    private final DisbursementRepository disbursementRepository;
    private final ShopService shopService;
    private final EmployeeService employeeService;
    private final DateUtil dateUtil;

    public List<Disbursement> findAll() {
        return disbursementRepository.findAll();
    }

    public List<Disbursement> findAllByShopId(Long shopId) {
        return disbursementRepository.findAllByShopShopId(shopId);
    }

    public List<Disbursement> findAllByEmployeeId(Long employeeId) {
        return disbursementRepository.findAllByEmployeeId(employeeId);
    }

    @Transactional
    public Disbursement create(
            Long employeeId, Integer disburseAmount, String disburseType, LocalDateTime dateTime) {
        Employee employee = employeeService.findById(employeeId);

        if (disbursementRepository.existsByEmployeeAndYearAndMonth(
                employee.getEmployeeId(), dateTime.getYear(), dateTime.getMonthValue()))
            throw new DisbursementAlreadyExistsException();

        Shop shop = employee.getShop();

        Disbursement disbursement =
                Disbursement.builder()
                        .shop(shop)
                        .disburseAmount(disburseAmount)
                        .disburseType(disburseType)
                        .build();
        return disbursementRepository.save(disbursement);
    }

    @Transactional
    public Disbursement calculateSalary(SalaryCalculationRequestDTO salaryCalculationRequestDTO) {
        LocalDateTime startOfMonth =
                dateUtil.getStartOfMonth(salaryCalculationRequestDTO.getDate());
        LocalDateTime endOfMonth = dateUtil.getEndOfMonth(salaryCalculationRequestDTO.getDate());

        // 한 달에 한 번만
        Employee employee = employeeService.findById(salaryCalculationRequestDTO.getEmployeeId());
        List<EmployeeWorkingHistory> employeeWorkingHistories =
                employee.getWorkingHistories().stream()
                        .filter(
                                employeeWorkingHistory ->
                                        dateUtil.filterBetweenDate(
                                                employeeWorkingHistory, startOfMonth, endOfMonth))
                        .toList();

        Long totalWorkingHour =
                employeeWorkingHistories.stream()
                        .mapToLong(
                                employeeWorkingHistory -> employeeWorkingHistory.getWorkingHour())
                        .sum();
        Integer salary = employee.calculateSalary(totalWorkingHour);

        Disbursement disbursement =
                create(
                        employee.getEmployeeId(),
                        salary,
                        DisbursementType.SALARY.getType(),
                        salaryCalculationRequestDTO.getDate());

        return disbursement;
    }

    // 본사로 자금 출납
    public Disbursement disburse(DisbursementCreateRequestDTO disbursementCreateRequestDTO) {
        Shop shop = shopService.findShopById(disbursementCreateRequestDTO.getShopId());
        LocalDateTime startOfMonth =
                dateUtil.getStartOfMonth(disbursementCreateRequestDTO.getDate());
        LocalDateTime endOfMonth = dateUtil.getEndOfMonth(disbursementCreateRequestDTO.getDate());

        // 한 달에 한 번만
        if (!disbursementRepository.existsByShopIdAndDisburseTypeAndYearAndMonth(
                shop.getShopId(),
                DisbursementType.ROYALTY.getType(),
                startOfMonth.getYear(),
                endOfMonth.getMonthValue())) throw new DisbursementAlreadyExistsException();

        Disbursement disbursement =
                Disbursement.builder()
                        .shop(shop)
                        .disburseAmount(disbursementCreateRequestDTO.getDisburseAmount())
                        .disburseType(disbursementCreateRequestDTO.getDisburseType())
                        .build();
        return disbursementRepository.save(disbursement);
    }
}
