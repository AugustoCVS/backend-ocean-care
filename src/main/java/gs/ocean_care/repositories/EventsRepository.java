package gs.ocean_care.repositories;

import gs.ocean_care.dtos.events.EventsDto;
import gs.ocean_care.models.Events;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository<Events, Long> {

    Page<EventsDto> findAllByActiveTrue(Pageable pageable);

    Page<EventsDto> finAllByUserId(Long id, Pageable pageable);

    Page<EventsDto> findAllByActiveTrueAndNameContainingIgnoreCase(String name, Pageable pageable);

}
