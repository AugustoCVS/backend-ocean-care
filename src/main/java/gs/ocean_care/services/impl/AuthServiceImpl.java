package gs.ocean_care.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import gs.ocean_care.dtos.auth.AuthDto;
import gs.ocean_care.dtos.auth.TokenResponseDto;
import gs.ocean_care.models.User;
import gs.ocean_care.repositories.UserRepository;
import gs.ocean_care.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class AuthServiceImpl implements AuthService {

    @Value("${api.security.token.expiration-time}")
    private Long expirationTime;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }

    @Value("${api.security.token.secret}")
    private String secret;

    public TokenResponseDto getToken(AuthDto data){
        User user = userRepository.findByEmail(data.email());

        return TokenResponseDto.builder()
                .accessToken(generateToken(user))
                .build();
    }

    public String generateToken(User user){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API OceanCare")
                    .withSubject(user.getEmail())
                    .withClaim("id", user.getId())
                    .withClaim("email", user.getEmail())
                    .withClaim("name", user.getName())
                    .withExpiresAt(tokenExpiration())
                    .sign(algorithm);
        }catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token jwt", exception);
        }
    }

    public String validateToken(String tokenJWT) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API OceanCare")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT inválido ou expirado");
        }
    }

    private Instant tokenExpiration(){
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }

    private Instant refreshTokenExpiration(){
        return LocalDateTime.now().plusDays(15).toInstant(ZoneOffset.of("-03:00"));
    }
}
