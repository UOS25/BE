package uos.uos25.disposal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uos.uos25.disposal.dto.request.DisposalListRequestDTO;
import uos.uos25.disposal.entity.Disposal;
import uos.uos25.disposal.repository.DisposalRepository;
import uos.uos25.product.entity.Product;
import uos.uos25.product.service.ProductService;
import uos.uos25.returns.dto.request.ReturnsRequestDTO;
import uos.uos25.returns.service.ReturnsService;
import uos.uos25.shop.entity.Shop;
import uos.uos25.shop.service.ShopService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisposalService {
    private final DisposalRepository disposalRepository;
    private final ProductService productService;
    private final ShopService shopService;

    public Disposal createDisposal(ReturnsRequestDTO returnsRequestDTO){
        Shop shop = shopService.findShopById(returnsRequestDTO.getShopId());
        Product product = productService.findProductById(returnsRequestDTO.getProductId());

        Disposal disposal = Disposal.builder()
                .shop(shop)
                .product(product)
                .ea(returnsRequestDTO.getEa())
                .build();

        // TODO: 폐기 개수 지워야 하나?? 인벤토리에서 삭제?
        return disposalRepository.save(disposal);
    }

    public List<Disposal> findDisposalsWithDate(DisposalListRequestDTO disposalListRequestDTO){
        List<Disposal> disposalsWithDate = disposalRepository.findByCreatedAtBetween(disposalListRequestDTO.getStartDate(), disposalListRequestDTO.getEndDate());

        return disposalsWithDate;
    }


}
