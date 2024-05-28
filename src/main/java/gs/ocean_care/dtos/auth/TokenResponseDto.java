package gs.ocean_care.dtos.auth;

import lombok.Builder;

@Builder
public record TokenResponseDto(String token, String refreshToken) {
}
