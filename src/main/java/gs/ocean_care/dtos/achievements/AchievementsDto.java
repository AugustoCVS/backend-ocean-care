package gs.ocean_care.dtos.achievements;

import gs.ocean_care.models.Achievements;

public record AchievementsDto(
        Long id,
        AchievementsType type
) {
    public AchievementsDto(Achievements achievements) {
        this(achievements.getId(), achievements.getType());
    }
}
