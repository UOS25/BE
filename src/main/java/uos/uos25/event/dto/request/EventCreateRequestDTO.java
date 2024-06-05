package uos.uos25.event.dto.request;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class EventCreateRequestDTO {
    private final String eventName;
    private final String eventCategory;
    private final List<String> productBarcodes;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
}
