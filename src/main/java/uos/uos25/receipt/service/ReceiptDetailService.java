package uos.uos25.receipt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uos.uos25.product.entity.Product;
import uos.uos25.product.service.ProductService;
import uos.uos25.receipt.entity.Receipt;
import uos.uos25.receipt.entity.ReceiptDetail;
import uos.uos25.receipt.exception.ReceiptDetailNotFound;
import uos.uos25.receipt.repository.ReceiptDetailRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceiptDetailService {
    private final ProductService productService;
    private final ReceiptService receiptService;
    private final ReceiptDetailRepository receiptDetailRepository;

    public ReceiptDetail create(Long productId, Long receiptId, Integer ea){
        Product product = productService.findProductById(productId);
        Receipt receipt = receiptService.findById(receiptId);

        ReceiptDetail receiptDetail = ReceiptDetail.builder()
                .product(product)
                .receipt(receipt)
                .ea(ea)
                .build();

        return receiptDetailRepository.save(receiptDetail);
    }

    public ReceiptDetail findById(Long receiptDetailId){
        return receiptDetailRepository.findById(receiptDetailId).orElseThrow(() -> new ReceiptDetailNotFound());
    }

    public List<ReceiptDetail> findAllByReciptId(Long receiptId){
        return receiptDetailRepository.findAllByReceiptReceiptId(receiptId);
    }


}
