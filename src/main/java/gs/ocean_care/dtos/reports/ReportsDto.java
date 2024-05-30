package gs.ocean_care.dtos.reports;

import gs.ocean_care.models.Reports;

public record ReportsDto(
    Long id,
    String name,
    ReportsType type,
    String location,
    String description
) {
    public ReportsDto(Reports reports){
        this(reports.getId(), reports.getName(), reports.getType(), reports.getLocation(), reports.getDescription());
    }
}
