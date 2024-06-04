package uos.uos25.event.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uos.uos25.event.dto.request.EventCreateRequestDTO;
import uos.uos25.event.dto.response.EventCreateResponseDTO;
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
}
