package gs.ocean_care.services;


import gs.ocean_care.dtos.auth.AuthDto;
import gs.ocean_care.dtos.auth.TokenResponseDto;
import gs.ocean_care.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    public String validateToken(String token);
    public TokenResponseDto getToken(AuthDto data);
    public TokenResponseDto getRefreshToken(String refreshToken);
}
