package uos.uos25.headquarter.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import uos.uos25.headquarter.entity.Event;

@Data
public class EventCreateResponseDTO {
    private final Long eventId;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final String eventName;
    private final Integer eventPrice;
    private final String eventCategory;
    private final String barcode;

    @Builder
    public EventCreateResponseDTO(
            Long eventId,
            LocalDateTime startDate,
            LocalDateTime endDate,
            String eventName,
            Integer eventPrice,
            String eventCategory,
            String barcode) {
        this.eventId = eventId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventName = eventName;
        this.eventPrice = eventPrice;
        this.eventCategory = eventCategory;
        this.barcode = barcode;
    }

    public static EventCreateResponseDTO fromEntity(Event event) {
        return EventCreateResponseDTO.builder()
                .eventId(event.getEventId())
                .eventName(event.getEventName())
                .eventPrice(event.getEventPrice())
                .eventCategory(event.getEventCategory())
                .barcode(event.getProduct().getBarcode())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .build();
    }
}
