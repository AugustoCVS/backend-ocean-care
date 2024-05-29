package gs.ocean_care.dtos.events;

import java.time.LocalDateTime;

public record EventsDto(
        Long id,
        String name,
        String description,
        String location,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
