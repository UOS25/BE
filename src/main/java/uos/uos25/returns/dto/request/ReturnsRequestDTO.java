package uos.uos25.returns.dto.request;

import lombok.Data;

@Data
public class ReturnsRequestDTO {
    private final Long shopId;
    private final String barcode;
    private final Integer ea;
}
