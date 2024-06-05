package uos.uos25.event.dto.response;

import lombok.Builder;
import lombok.Data;
import uos.uos25.event.entity.Event;

import java.time.LocalDateTime;

@Data
public class EventGetResponseDTO {
    private final Long eventId;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final String eventName;
    private final String eventCategory;
    private final String barcode;

    @Builder
    public EventGetResponseDTO(Long eventId, LocalDateTime startDate, LocalDateTime endDate, String eventName, String eventCategory, String barcode) {
        this.eventId = eventId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventName = eventName;
        this.eventCategory = eventCategory;
        this.barcode = barcode;
    }

    public static EventGetResponseDTO fromEntity(Event event){
        return EventGetResponseDTO.builder()
                .eventId(event.getEventId())
                .eventName(event.getEventName())
                .eventCategory(event.getEventCategory())
                .barcode(event.getProduct().getBarcode())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .build();
    }
}