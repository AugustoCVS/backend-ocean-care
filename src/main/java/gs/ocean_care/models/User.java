package gs.ocean_care.models;

import gs.ocean_care.dtos.user.RegisterUserDto;
import gs.ocean_care.dtos.user.UpdateUserDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String confirm_password;
    @Column(nullable = false)
    private Integer reportedTrash;
    private boolean active;

    @ManyToMany(mappedBy = "users")
    private List<Events> events;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reports> reports;

    @ManyToMany(mappedBy = "users")
    private List<Achievements> achievements;

    public User(RegisterUserDto data) {
        this.name = data.name();
        this.email = data.email();
        this.password = data.password();
        this.confirm_password = data.confirm_password();
        this.reportedTrash = 0;
    };

    public void updateUser(UpdateUserDto data) {
        if (data.name() != null) {
            this.name = data.name();
        }
        if (data.email() != null) {
            this.email = data.email();
        }
        if (data.reportedTrash() != null) {
            this.reportedTrash = data.reportedTrash();
        }
    }

    public void softDelete() {
        this.active = false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
