package uos.uos25.returns.dto.request;

import lombok.Data;

@Data
public class ReturnsRequestDTO {
    private final Long shopId;
    private final Long productId;
    private final Integer ea;
}
