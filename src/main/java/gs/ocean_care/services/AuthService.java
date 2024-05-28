package gs.ocean_care.services;


import gs.ocean_care.dtos.auth.AuthDto;
import gs.ocean_care.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    public String generateToken(User data);

    public String getSubject(String token);
}
