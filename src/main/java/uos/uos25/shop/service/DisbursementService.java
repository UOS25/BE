package uos.uos25.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uos.uos25.shop.entity.Disbursement;
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
}
