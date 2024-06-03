package uos.uos25.returns.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uos.uos25.product.entity.Product;
import uos.uos25.product.service.ProductService;
import uos.uos25.returns.dto.request.ReturnsRequestDTO;
import uos.uos25.returns.dto.response.ReturnsResponseDTO;
import uos.uos25.returns.entity.Returns;
import uos.uos25.returns.repository.ReturnsRepository;
import uos.uos25.shop.entity.Shop;
import uos.uos25.shop.service.ShopService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReturnsService {
    private final ReturnsRepository returnsRepository;
    private final ShopService shopService;
    private final ProductService productService;

    public Long createReturns(ReturnsRequestDTO returnsRequestDTO){
        Shop shop = shopService.findShopById(returnsRequestDTO.getShopId());
        Product product = productService.findProductById(returnsRequestDTO.getProductId());

        Returns returns = Returns.builder()
                .shop(shop)
                .product(product)
                .ea(returnsRequestDTO.getEa())
                .returnsStatus("상태 뭘로 할까?")
                .build();

        Returns saved = returnsRepository.save(returns);
        return saved.getReturnsId();
    }

    public List<ReturnsResponseDTO> findAllByShopId(Long shopId){
        Shop shop = shopService.findShopById(shopId);
        List<Returns> allReturnsByShopId = returnsRepository.findAllByShop_ShopId(shopId);

        return allReturnsByShopId.stream().map(returns -> ReturnsResponseDTO.fromEntity(returns)).toList();
    }
}