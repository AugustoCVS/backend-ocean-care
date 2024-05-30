package gs.ocean_care.dtos.reports;

public record UpdateReportDto(
    String name,
    ReportsType type,
    String location,
    String description
) {
}
