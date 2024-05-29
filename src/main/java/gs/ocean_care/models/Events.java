package gs.ocean_care.models;

import gs.ocean_care.dtos.events.RegisterEventsDto;
import gs.ocean_care.dtos.events.UpdateEventDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private LocalDateTime startDate;
    @Column(nullable = false)
    private LocalDateTime endDate;
    @Column(nullable = false)
    private boolean active;

    @ManyToMany
    @JoinTable(
            name = "events_participants",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    public Events(RegisterEventsDto data){
        this.name = data.name();
        this.description = data.description();
        this.location = data.location();
        this.startDate = data.startDate();
        this.endDate = data.endDate();
        this.active = true;
    }

    public void updateEvent(UpdateEventDto data){
        if(data.name() != null){
            this.name = data.name();
        }
        if(data.description() != null){
            this.description = data.description();
        }
        if(data.location() != null){
            this.location = data.location();
        }
        if(data.startDate() != null){
            this.startDate = data.startDate();
        }
        if(data.endDate() != null){
            this.endDate = data.endDate();
        }
    }

    public void softDelete(){
        this.active = false;
    }
}
