package gs.ocean_care.dtos.user;

import gs.ocean_care.models.User;

public record UserDto (Long id, String name, String email, Integer reportedTrash) {

    public UserDto(User user){
        this(user.getId(), user.getName(), user.getEmail(), user.getReportedTrash());
    }
}
