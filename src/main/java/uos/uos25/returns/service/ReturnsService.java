package uos.uos25.returns.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uos.uos25.product.entity.Product;
import uos.uos25.product.service.ProductService;
import uos.uos25.returns.dto.request.ReturnsCreateRequestDTO;
import uos.uos25.returns.entity.Returns;
import uos.uos25.returns.repository.ReturnsRepository;
import uos.uos25.shop.entity.Shop;
import uos.uos25.shop.service.ShopService;

@Service
@RequiredArgsConstructor
public class ReturnsService {
    private final ReturnsRepository returnsRepository;
    private final ShopService shopService;
    private final ProductService productService;

    public Returns createReturns(ReturnsCreateRequestDTO returnsCreateRequestDTO) {
        Shop shop = shopService.findShopById(returnsCreateRequestDTO.getShopId());
        Product product = productService.findById(returnsCreateRequestDTO.getBarcode());

        Returns returns =
                Returns.builder()
                        .shop(shop)
                        .product(product)
                        .ea(returnsCreateRequestDTO.getEa())
                        .returnsStatus("반품요청")
                        .build();

        return returnsRepository.save(returns);
    }

    public List<Returns> findAllByShopId(Long shopId) {
        Shop shop = shopService.findShopById(shopId);
        return returnsRepository.findAllByShop_ShopId(shopId);
    }
}
