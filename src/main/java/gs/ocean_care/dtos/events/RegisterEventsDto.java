package gs.ocean_care.dtos.events;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDateTime;

public record RegisterEventsDto(
        @NotBlank(message = "O nome do evento é obrigatório")
        String name,
        @NotBlank(message = "A descrição do evento é obrigatória")
        String description,
        @NotBlank(message = "A localização do evento é obrigatória")
        String location,
        @Past(message = "A data de início não pode ser uma data passada")
        @NotNull(message = "A data de início do evento é obrigatória")
        LocalDateTime startDate,
        @NotNull(message = "A data de término do evento é obrigatória")
        LocalDateTime endDate
) {
}
