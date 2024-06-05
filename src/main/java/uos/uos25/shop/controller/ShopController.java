package uos.uos25.shop.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import uos.uos25.shop.service.ShopService;

@Controller
@RequestMapping("/shop")
@RequiredArgsConstructor
@Tag(name = "지점")
public class ShopController {

    private final ShopService shopService;


}
