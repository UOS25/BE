package uos.uos25.event.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import uos.uos25.event.dto.request.EventCreateRequestDTO;
import uos.uos25.event.dto.response.EventCreateResponseDTO;
import uos.uos25.event.dto.response.EventGetResponseDTO;
import uos.uos25.event.entity.Event;
import uos.uos25.event.service.EventService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
@Tag(name = "이벤트")
public class EventController {
    private final EventService eventService;

    @Operation(summary = "이벤트 추가")
    @PostMapping
    public ResponseEntity<List<EventCreateResponseDTO>> create(
            @RequestBody EventCreateRequestDTO eventCreateRequestDTO) {
        List<Event> events = eventService.create(eventCreateRequestDTO);
        List<EventCreateResponseDTO> eventCreateResponseDTOS =
                events.stream().map(event -> EventCreateResponseDTO.fromEntity(event)).toList();

        return ResponseEntity.ok(eventCreateResponseDTOS);
    }

    @GetMapping
    public ResponseEntity<List<EventGetResponseDTO>> getAll() {
        List<Event> events = eventService.findAll();
        List<EventGetResponseDTO> eventGetResponseDTOS =
                events.stream().map(event -> EventGetResponseDTO.fromEntity(event)).toList();

        return ResponseEntity.ok(eventGetResponseDTOS);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventGetResponseDTO> getByEventId(@PathVariable Long eventId) {
        Event event = eventService.getById(eventId);
        EventGetResponseDTO eventGetResponseDTO = EventGetResponseDTO.fromEntity(event);

        return ResponseEntity.ok(eventGetResponseDTO);
    }
}
