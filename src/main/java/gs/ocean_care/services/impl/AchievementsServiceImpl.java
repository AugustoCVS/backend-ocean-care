package gs.ocean_care.services.impl;

import gs.ocean_care.dtos.achievements.AchievementsDto;
import gs.ocean_care.dtos.achievements.RegisterAchievementDto;
import gs.ocean_care.models.Achievements;
import gs.ocean_care.repositories.AchievementsRepository;
import gs.ocean_care.services.AchievementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AchievementsServiceImpl implements AchievementsService {

    @Autowired
    private AchievementsRepository repository;

    @Override
    public List<AchievementsDto> findAllByUserId(Long userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public void register(RegisterAchievementDto data) {
        Achievements achievement = new Achievements(data);
        repository.save(achievement);
    }
}
