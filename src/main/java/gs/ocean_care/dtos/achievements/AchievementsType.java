package gs.ocean_care.dtos.achievements;

import lombok.Getter;

@Getter
public enum AchievementsType {
    FiftyReports("fiftyReports", 50),
    HundredReports("hundredReports", 100),
    TwoHundredReports("twoHundredReports", 200),
    FiveHundredReports("fiveHundredReports", 500),
    ThousandReports("thousandReports", 1000),
    TwoThousandReports("twoThousandReports", 2000),
    ThreeThousandReports("threeThousandReports", 3000),
    FiveThousandReports("fiveThousandReports", 5000),
    TenThousandReports("tenThousandReports", 10000);

    private final String type;
    private final Integer points;

    AchievementsType(String type, Integer points) {
        this.type = type;
        this.points = points;
    }
}
