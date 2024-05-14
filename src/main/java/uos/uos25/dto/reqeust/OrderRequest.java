package uos.uos25.dto.reqeust;

import lombok.Data;

@Data
public class OrderRequest {
    private Integer productId;
    private String productName;
    private Integer ea;
    private Integer totalPrice;
}
