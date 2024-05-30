package gs.ocean_care.dtos.reports;

import jakarta.validation.constraints.NotBlank;

public record RegisterReportDto(
    @NotBlank(message = "O nome é obrigatório")
    String name,
    @NotBlank(message = "O tipo é obrigatório")
    ReportsType type,
    @NotBlank(message = "A localização é obrigatória")
    String location,
    @NotBlank(message = "A descrição é obrigatória")
    String description
) {
}
