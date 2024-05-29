package gs.ocean_care.dtos.events;

import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record UpdateEventDto(
        String name,
        String description,
        String location,
        @Past(message = "A data de início não pode ser uma data passada")
        LocalDate startDate,
        LocalDate endDate
) {
}
