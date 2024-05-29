package gs.ocean_care.dtos.events;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record RegisterEventsDto(
        @NotBlank(message = "O nome do evento é obrigatório")
        String name,
        @NotBlank(message = "A descrição do evento é obrigatória")
        String description,
        @NotBlank(message = "A localização do evento é obrigatória")
        String location,
        @NotNull(message = "A data de início do evento é obrigatória")
        LocalDate startDate,
        @NotNull(message = "A data de término do evento é obrigatória")
        LocalDate endDate
) {
}
