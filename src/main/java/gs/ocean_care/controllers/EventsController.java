package gs.ocean_care.controllers;

import gs.ocean_care.dtos.events.EventsDto;
import gs.ocean_care.dtos.events.RegisterEventsDto;
import gs.ocean_care.dtos.events.UpdateEventDto;
import gs.ocean_care.services.EventsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventsController {

    @Autowired
    private EventsService eventService;

    @PostMapping("/register")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterEventsDto data) {
        eventService.register(data);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody @Valid UpdateEventDto data) {
        var updatedEvent = eventService.update(id, data);

        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        eventService.delete(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Page<EventsDto>> list(
            @PageableDefault(size = 20, page = 0) Pageable pageable,
            @RequestParam(value = "name", required = false) String name
    ) {
        Page<EventsDto> events;

        if (name != null) {
            events = eventService.findAllByName(name);
        } else {
            events = eventService.findAll(pageable);
        }

        return ResponseEntity.ok(events);
    }

    @GetMapping("/list/user/{userId}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Page<EventsDto>> listByUserId(
            @PathVariable Long userId,
            @PageableDefault(size = 20, page = 0) Pageable pageable
    ) {
        var events = eventService.findAllByUserId(userId, pageable);

        return ResponseEntity.ok(events);
    }

}
