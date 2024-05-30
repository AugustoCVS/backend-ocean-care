package gs.ocean_care.dtos.reports;

import lombok.Getter;

public enum ReportsType {
    CLOTHES("clothes", 5),
    PACKAGING("packaging", 2),
    BOTTLES("bottles", 4),
    TRASH("trash", 10),
    OIL("oil", 20),
    CHEMICALS("chemicals", 30),
    OTHER("other", 1);

    private final String type;
    private final Integer points;

    ReportsType(String type, Integer points) {
        this.type = type;
        this.points = points;
    }

    public static Integer getPoints(ReportsType type) {
        return type.points;
    }
}
