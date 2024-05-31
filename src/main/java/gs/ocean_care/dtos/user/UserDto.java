package gs.ocean_care.dtos.user;

import gs.ocean_care.dtos.achievements.AchievementsType;
import gs.ocean_care.models.User;

import java.util.List;

public record UserDto (Long id, String name, String email, Integer reportedTrash, List<AchievementsType> achievements) {

    public UserDto(User user){
        this(user.getId(), user.getName(), user.getEmail(), user.getReportedTrash(), user.getAchievements());
    }
}
