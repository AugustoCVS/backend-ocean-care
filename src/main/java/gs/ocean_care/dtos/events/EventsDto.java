package gs.ocean_care.dtos.events;

import gs.ocean_care.dtos.user.UserDto;
import gs.ocean_care.models.Events;

import java.time.LocalDate;
import java.util.List;

public record EventsDto(
        Long id,
        String name,
        String description,
        String location,
        LocalDate startDate,
        LocalDate endDate,
        List<UserDto> users
) {

    public EventsDto(Events event) {
        this(event.getId(), event.getName(), event.getDescription(), event.getLocation(), event.getStartDate(), event.getEndDate(),
                event.getUsers().stream().map(UserDto::new).toList());
    }
}
