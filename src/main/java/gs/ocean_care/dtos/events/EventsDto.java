package gs.ocean_care.dtos.events;

import gs.ocean_care.models.Events;

import java.time.LocalDateTime;

public record EventsDto(
        Long id,
        String name,
        String description,
        String location,
        LocalDateTime startDate,
        LocalDateTime endDate
) {

    public EventsDto(Events event){
        this(event.getId(), event.getName(), event.getDescription(), event.getLocation(), event.getStartDate(), event.getEndDate());
    }
}
