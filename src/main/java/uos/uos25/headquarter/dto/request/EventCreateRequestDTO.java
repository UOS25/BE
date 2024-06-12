package uos.uos25.headquarter.dto.request;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class EventCreateRequestDTO {
    @Schema(example = "newEvent")
    private final String eventName;

    @Schema(example = "500")
    private final Integer eventPrice;

    @Schema(example = "newCategory")
    private final String eventCategory;

    @Schema(example = """
            ["barcode", "ATM"]
            """)
    private final List<String> productBarcodes;

    @Schema(example = "2024-06-01T00:00:00")
    private final LocalDateTime startDate;

    @Schema(example = "2024-06-30T23:59:59")
    private final LocalDateTime endDate;
}
