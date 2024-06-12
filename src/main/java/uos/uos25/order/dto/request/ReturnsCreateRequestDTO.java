package uos.uos25.order.dto.request;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ReturnsCreateRequestDTO {
    @Schema(example = "1")
    private final Long shopId;

    private final List<ReturnsProductRequestDTO> products;
}
