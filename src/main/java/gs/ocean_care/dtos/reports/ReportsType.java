package gs.ocean_care.dtos.reports;

import lombok.Getter;

@Getter
public enum ReportsType {
    CLOTHES("clothes"),
    PACKAGING("packaging"),
    BOTTLES("bottles"),
    TRASH("trash"),
    OIL("oil"),
    CHEMICALS("chemicals"),
    OTHER("other");

    private final String type;
    ReportsType(String type) {
        this.type = type;
    }
}
