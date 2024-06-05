package uos.uos25.event.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uos.uos25.event.dto.request.EventCreateRequestDTO;
import uos.uos25.event.dto.response.EventCreateResponseDTO;
import uos.uos25.event.dto.response.EventGetResponseDTO;
import uos.uos25.event.entity.Event;
import uos.uos25.event.service.EventService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<List<EventCreateResponseDTO>> create(@RequestBody EventCreateRequestDTO eventCreateRequestDTO){
        List<Event> events = eventService.create(eventCreateRequestDTO);
        List<EventCreateResponseDTO> eventCreateResponseDTOS = events.stream().map(event -> EventCreateResponseDTO.fromEntity(event)).toList();

        return ResponseEntity.ok(eventCreateResponseDTOS);
    }

    @GetMapping
    public ResponseEntity<List<EventGetResponseDTO>> getAll(){
        List<Event> events = eventService.findAll();
        List<EventGetResponseDTO> eventGetResponseDTOS = events.stream().map(event -> EventGetResponseDTO.fromEntity(event)).toList();

        return ResponseEntity.ok(eventGetResponseDTOS);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventGetResponseDTO> getByEventId(@PathVariable Long eventId) {
        Event event = eventService.getById(eventId);
        EventGetResponseDTO eventGetResponseDTO = EventGetResponseDTO.fromEntity(event);

        return ResponseEntity.ok(eventGetResponseDTO);
    }
}
