package gs.ocean_care.repositories;

import gs.ocean_care.dtos.events.EventsDto;
import gs.ocean_care.models.Events;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EventsRepository extends JpaRepository<Events, Long> {

    Page<Events> findAllByActiveTrue(Pageable pageable);

    @Query("SELECT e FROM Events e JOIN e.users u WHERE u.id = :userId")
    Page<EventsDto> findAllByUserId(@Param("userId") Long id, Pageable pageable);

    Page<EventsDto> findAllByActiveTrueAndNameContainingIgnoreCase(String name, Pageable pageable);

}
