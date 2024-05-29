package gs.ocean_care.services.impl;

import gs.ocean_care.dtos.events.EventsDto;
import gs.ocean_care.dtos.events.RegisterEventsDto;
import gs.ocean_care.dtos.events.UpdateEventDto;
import gs.ocean_care.models.Events;
import gs.ocean_care.models.User;
import gs.ocean_care.repositories.EventsRepository;
import gs.ocean_care.repositories.UserRepository;
import gs.ocean_care.services.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventsServiceImpl implements EventsService {

    @Autowired
    private EventsRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<EventsDto> findAll(Pageable pageable) {
        Page<Events> events = repository.findAllByActiveTrue(pageable);
        return events.map(Events::toDto);
    }

    @Override
    public Page<EventsDto> findAllByUserId(Long id, Pageable pageable) {
        return repository.findAllByUserId(id, pageable);
    }

    @Override
    public Page<EventsDto> findAllByName(String name, Pageable pageable) {
        return repository.findAllByActiveTrueAndNameContainingIgnoreCase(name, pageable);
    }

    @Override
    public void register(RegisterEventsDto data){
        Events event = new Events(data);
        repository.save(event);
    }

    @Override
    public EventsDto update(Long id, UpdateEventDto data) {
        Events event = repository.getReferenceById(id);
        event.updateEvent(data);

        return new EventsDto(event);
    }

    @Override
    public void delete(Long id) {
        Events event = repository.getReferenceById(id);
        event.softDelete();
    }

    @Override
    public void subscribeUser(Long eventId, Long userId) {
        Events event = repository.getReferenceById(eventId);
        User user = userRepository.getReferenceById(userId);
        event.subscribeUserOnEvent(user);
    }
}
