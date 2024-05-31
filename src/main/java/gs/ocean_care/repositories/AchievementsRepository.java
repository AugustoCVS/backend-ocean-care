package gs.ocean_care.repositories;

import gs.ocean_care.dtos.achievements.AchievementsDto;
import gs.ocean_care.models.Achievements;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AchievementsRepository extends JpaRepository<Achievements, Long> {
    List<AchievementsDto> findAllByUserId(Long userId);
}
