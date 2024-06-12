package uos.uos25.user.receipt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uos.uos25.headquarter.entity.Product;
import uos.uos25.headquarter.service.ProductService;
import uos.uos25.user.receipt.entity.Receipt;
import uos.uos25.user.receipt.entity.ReceiptDetail;
import uos.uos25.user.receipt.exception.ReceiptDetailNotFound;
import uos.uos25.user.receipt.repository.ReceiptDetailRepository;

@Service
@RequiredArgsConstructor
public class ReceiptDetailService {
    private final ProductService productService;
    private final ReceiptDetailRepository receiptDetailRepository;

    public ReceiptDetail create(Receipt receipt, String productId, Integer ea) {
        Product product = productService.findById(productId);

        ReceiptDetail receiptDetail =
                ReceiptDetail.builder().product(product).receipt(receipt).ea(ea).build();

        return receiptDetailRepository.save(receiptDetail);
    }

    public ReceiptDetail findById(Long receiptDetailId) {
        return receiptDetailRepository
                .findById(receiptDetailId)
                .orElseThrow(() -> new ReceiptDetailNotFound());
    }

    public List<ReceiptDetail> findAllByReciptId(Long receiptId) {
        return receiptDetailRepository.findAllByReceiptReceiptId(receiptId);
    }
}
