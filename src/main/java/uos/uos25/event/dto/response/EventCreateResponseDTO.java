package uos.uos25.event.dto.response;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import uos.uos25.event.entity.Event;
import uos.uos25.product.entity.Product;

import java.time.LocalDateTime;

@Data
public class EventCreateResponseDTO {
    private final Long eventId;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final String eventName;
    private final String eventCategory;
    private final String barcode;

    @Builder
    public EventCreateResponseDTO(Long eventId, LocalDateTime startDate, LocalDateTime endDate, String eventName, String eventCategory, String barcode) {
        this.eventId = eventId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventName = eventName;
        this.eventCategory = eventCategory;
        this.barcode = barcode;
    }

    public static EventCreateResponseDTO fromEntity(Event event){
        return EventCreateResponseDTO.builder()
                .eventId(event.getEventId())
                .eventName(event.getEventName())
                .eventCategory(event.getEventCategory())
                .barcode(event.getProduct().getBarcode())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .build();
    }
}
