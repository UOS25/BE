package uos.uos25.disposal.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uos.uos25.disposal.dto.request.DisposalCreateReqeustDTO;
import uos.uos25.disposal.entity.Disposal;
import uos.uos25.disposal.repository.DisposalRepository;
import uos.uos25.product.entity.Product;
import uos.uos25.product.service.ProductService;
import uos.uos25.shop.entity.Shop;
import uos.uos25.shop.service.ShopService;

@Service
@RequiredArgsConstructor
public class DisposalService {
    private final DisposalRepository disposalRepository;
    private final ProductService productService;
    private final ShopService shopService;

    public Disposal createDisposal(DisposalCreateReqeustDTO disposalCreateReqeustDTO) {
        Shop shop = shopService.findShopById(disposalCreateReqeustDTO.getShopId());
        Product product = productService.findById(disposalCreateReqeustDTO.getBarcode());

        Disposal disposal =
                Disposal.builder()
                        .shop(shop)
                        .product(product)
                        .ea(disposalCreateReqeustDTO.getEa())
                        .build();

        // TODO: 폐기 개수 지워야 하나?? 인벤토리에서 삭제?
        return disposalRepository.save(disposal);
    }

    public List<Disposal> findDisposalsWithDate(
            Long shopId, LocalDateTime startDate, LocalDateTime endDate) {
        List<Disposal> disposalsWithDate =
                disposalRepository.findByCreatedAtBetweenAndShopShopId(startDate, endDate, shopId);

        return disposalsWithDate;
    }
}
