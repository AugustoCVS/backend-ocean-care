package gs.ocean_care.services;

import gs.ocean_care.dtos.achievements.AchievementsDto;
import gs.ocean_care.dtos.achievements.RegisterAchievementDto;

import java.util.List;

public interface AchievementsService {
    public List<AchievementsDto> findAllByUserId(Long userId);

    public void register(RegisterAchievementDto data);
}
