package uos.uos25.shop.service;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uos.uos25.employee.entity.Employee;
import uos.uos25.shop.entity.Disbursement;
import uos.uos25.shop.entity.Shop;
import uos.uos25.shop.exception.DisbursementAlreadyExistsException;
import uos.uos25.shop.repository.DisbursementRepository;

@Service
@RequiredArgsConstructor
public class DisbursementService {
    private final DisbursementRepository disbursementRepository;

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
    public Disbursement create(Employee employee, Long disburseAmount, LocalDateTime disburseDate) {
        if (disbursementRepository.existsByEmployeeAndYearAndMonth(
                employee.getEmployeeId(), disburseDate.getYear(), disburseDate.getMonthValue()))
            throw new DisbursementAlreadyExistsException();

        Shop shop = employee.getShop();

        Disbursement disbursement =
                Disbursement.builder()
                        .shop(shop)
                        .disburseAmount(disburseAmount)
                        .disburseDate(disburseDate)
                        .build();
        return disbursementRepository.save(disbursement);
    }
}
