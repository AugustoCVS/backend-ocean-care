package gs.ocean_care.dtos.achievements;

import jakarta.validation.constraints.NotNull;

public record RegisterAchievementDto(
        @NotNull(message = "Tipo da conquista é obrigatório")
        AchievementsType type
) {
}
