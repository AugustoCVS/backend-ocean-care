package gs.ocean_care.services;

import gs.ocean_care.dtos.events.EventsDto;
import gs.ocean_care.dtos.events.RegisterEventsDto;
import gs.ocean_care.dtos.events.UpdateEventDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventsService {

    public Page<EventsDto> findAll(Pageable pageable);

    public Page<EventsDto> findAllByUserId(Long id, Pageable pageable);

    public Page<EventsDto> findAllByName(String name, Pageable pageable);

    public void delete(Long id);

    public EventsDto update(Long id, UpdateEventDto data);

    public void register(RegisterEventsDto data);
}
