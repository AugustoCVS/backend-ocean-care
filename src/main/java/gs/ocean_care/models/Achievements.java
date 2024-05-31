package gs.ocean_care.models;

import gs.ocean_care.dtos.achievements.AchievementsType;
import gs.ocean_care.dtos.achievements.RegisterAchievementDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "achievements")
public class Achievements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private AchievementsType type;

    @ManyToMany
    @JoinTable(
        name = "users_achievements",
        joinColumns = @JoinColumn(name = "achievementsId"),
        inverseJoinColumns = @JoinColumn(name = "userId")
    )
    private List<User> users;

    public Achievements(RegisterAchievementDto data){
        this.type = data.type();
    }
}
