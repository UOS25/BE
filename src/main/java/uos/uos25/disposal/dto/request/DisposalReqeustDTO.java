package uos.uos25.disposal.dto.request;

import lombok.Data;

@Data
public class DisposalReqeustDTO {
    private final Long shopId;
    private final String barcode;
    private final Integer ea;
}
