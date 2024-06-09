package uos.uos25.event.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uos.uos25.event.dto.request.EventCreateRequestDTO;
import uos.uos25.event.entity.Event;
import uos.uos25.event.exception.EventNotFound;
import uos.uos25.event.repository.EventRepository;
import uos.uos25.product.entity.Product;
import uos.uos25.product.service.ProductService;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final ProductService productService;

    public List<Event> create(EventCreateRequestDTO eventCreateRequestDTO) {
        String eventName = eventCreateRequestDTO.getEventName();
        Integer eventPrice = eventCreateRequestDTO.getEventPrice();
        String eventCategory = eventCreateRequestDTO.getEventCategory();
        LocalDateTime startDate = eventCreateRequestDTO.getStartDate();
        LocalDateTime endDate = eventCreateRequestDTO.getEndDate();
        List<String> productBarcodes = eventCreateRequestDTO.getProductBarcodes();

        List<Event> newEvents = new ArrayList<>();

        for (String productBarcode : productBarcodes) {
            Product product = productService.findById(productBarcode);

            Event newEvent =
                    Event.builder()
                            .product(product)
                            .eventName(eventName)
                            .eventPrice(eventPrice)
                            .eventCategory(eventCategory)
                            .startDate(startDate)
                            .endDate(endDate)
                            .build();

            newEvents.add(newEvent);
        }
        List<Event> savedEvents = eventRepository.saveAll(newEvents);

        return savedEvents;
    }

    public List<Event> findAll() {
        List<Event> events = eventRepository.findAll();
        return events;
    }

    public Event getById(Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFound());

        return event;
    }
}
