package gs.ocean_care.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterUserDto(
        @NotBlank(message = "O nome não pode ser vazio")
        String name,
        @NotBlank(message = "O nome não pode ser vazio")
        @Email(message = "Email inválido")
        String email,
        @NotBlank(message = "A senha não pode ser vazia")
        String password,
        @NotBlank(message = "A confirmação de senha não pode ser vazia")
        String confirm_password) {
}
