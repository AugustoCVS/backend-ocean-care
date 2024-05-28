package gs.ocean_care.dtos.user;

import jakarta.validation.constraints.Min;

public record UpdateUserDto(
        String name,
        String email,
        @Min(value = 0, message = "O valor mínimo para lixo reportado é 0")
        Integer reportedTrash
) {

}
