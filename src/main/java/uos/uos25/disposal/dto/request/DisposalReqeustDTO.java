package uos.uos25.disposal.dto.request;

import lombok.Data;

@Data
public class DisposalReqeustDTO {
    private final Long shopId;
    private final Long productId;
    private final Integer ea;
}
