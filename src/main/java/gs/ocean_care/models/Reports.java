package gs.ocean_care.models;

import gs.ocean_care.dtos.reports.RegisterReportDto;
import gs.ocean_care.dtos.reports.ReportsDto;
import gs.ocean_care.dtos.reports.ReportsType;
import gs.ocean_care.dtos.reports.UpdateReportDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "reports")
public class Reports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private ReportsType type;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private String description;
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    public Reports(RegisterReportDto data){
        this.name = data.name();
        this.type = data.type();
        this.location = data.location();
        this.description = data.description();
        this.active = true;
    }

    public void update(UpdateReportDto data){
        if(data.name() != null) {
            this.name = data.name();
        }
        if(data.type() != null) {
            this.type = data.type();
        }
        if(data.location() != null) {
            this.location = data.location();
        }
        if(data.description() != null) {
            this.description = data.description();
        }
    }
    public void softDelete(){
        this.active = false;
    }

}
