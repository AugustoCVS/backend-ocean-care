package gs.ocean_care.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import gs.ocean_care.dtos.achievements.AchievementsType;
import gs.ocean_care.dtos.auth.AuthDto;
import gs.ocean_care.dtos.auth.TokenResponseDto;
import gs.ocean_care.infra.exception.UnauthorizedException;
import gs.ocean_care.models.User;
import gs.ocean_care.repositories.UserRepository;
import gs.ocean_care.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Value("${api.security.token.secret}")
    private String secret;

    @Value("${api.security.token.expiration-time}")
    private Integer tokenExpiration;

    @Value("${api.security.refresh-token.expiration-time}")
    private Integer refreshTokenExpiration;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }

    @Override
    public TokenResponseDto getToken(AuthDto data){
        User user = userRepository.findByEmail(data.email());

        return TokenResponseDto.builder()
                .token(generateToken(user, tokenExpiration))
                .refreshToken(generateToken(user, refreshTokenExpiration))
                .build();
    }

    public String generateToken(User user, Integer expiration){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            List<String> achievementsAsString = user.getAchievements().stream()
                    .map(AchievementsType::name)
                    .collect(Collectors.toList());

            return JWT.create()
                    .withIssuer("API OceanCare")
                    .withSubject(user.getEmail())
                    .withClaim("id", user.getId())
                    .withClaim("email", user.getEmail())
                    .withClaim("name", user.getName())
                    .withClaim("reportedTrash", user.getReportedTrash())
                    .withClaim("achievements", achievementsAsString)
                    .withExpiresAt(tokenExpiration(expiration))
                    .sign(algorithm);
        }catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token jwt", exception);
        }
    }

    public String validateToken(String tokenJWT) {
        try {
            System.out.println("Token JWT: " + tokenJWT);
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API OceanCare")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTVerificationException exception){
            throw new UnauthorizedException("Token JWT inválido ou expirado");
        }
    }

    private Instant tokenExpiration(Integer expiration){
        return LocalDateTime.now().plusHours(expiration).toInstant(ZoneOffset.of("-03:00"));
    }

    public TokenResponseDto getRefreshToken(String refreshToken){

        String subject = validateToken(refreshToken);
        User user = userRepository.findByEmail(subject);

        if(user == null) {
            throw new UnauthorizedException("Falha ao gerar o refresh token");
        }

        var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(auth);

        return TokenResponseDto.builder()
                .token(generateToken(user, tokenExpiration))
                .refreshToken(generateToken(user, refreshTokenExpiration))
                .build();
    }
}
