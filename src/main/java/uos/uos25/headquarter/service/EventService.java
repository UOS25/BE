package uos.uos25.headquarter.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uos.uos25.headquarter.dto.request.EventCreateRequestDTO;
import uos.uos25.headquarter.entity.Event;
import uos.uos25.headquarter.entity.Product;
import uos.uos25.headquarter.exception.EventNotFound;
import uos.uos25.headquarter.repository.EventRepository;

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
