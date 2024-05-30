package gs.ocean_care.models;

import gs.ocean_care.dtos.events.EventsDto;
import gs.ocean_care.dtos.events.RegisterEventsDto;
import gs.ocean_care.dtos.events.UpdateEventDto;
import gs.ocean_care.dtos.user.UserDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private LocalDate startDate;
    @Column(nullable = false)
    private LocalDate endDate;
    @Column(nullable = false)
    private boolean active;

    @ManyToMany
    @JoinTable(
            name = "events_participants",
            joinColumns = @JoinColumn(name = "eventId"),
            inverseJoinColumns = @JoinColumn(name = "userId")
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

    public EventsDto toDto() {
        return new EventsDto(
                this.id,
                this.name,
                this.description,
                this.location,
                this.startDate,
                this.endDate,
                this.users.stream().map(UserDto::new).toList()
        );
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


    public void subscribeUserOnEvent(User user){
        this.users.add(user);
    }
}
