package uos.uos25.event.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uos.uos25.event.dto.request.EventCreateRequestDTO;
import uos.uos25.event.entity.Event;
import uos.uos25.event.repository.EventRepository;
import uos.uos25.product.entity.Product;
import uos.uos25.product.service.ProductService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final ProductService productService;

    public List<Event> create(EventCreateRequestDTO eventCreateRequestDTO){
        String eventName = eventCreateRequestDTO.getEventName();
        String eventCategory = eventCreateRequestDTO.getEventCategory();
        LocalDateTime startDate = eventCreateRequestDTO.getStartDate();
        LocalDateTime endDate = eventCreateRequestDTO.getEndDate();
        List<String> productBarcodes = eventCreateRequestDTO.getProductBarcodes();

        List<Event> newEvents = new ArrayList<>();

        for (String productBarcode : productBarcodes) {
            Product product = productService.findById(productBarcode);

            Event newEvent = Event.builder()
                    .product(product)
                    .eventName(eventName)
                    .eventCategory(eventCategory)
                    .startDate(startDate)
                    .endDate(endDate)
                    .build();

            newEvents.add(newEvent);
        }
        List<Event> savedEvents = eventRepository.saveAll(newEvents);

        return savedEvents;
    }
}
