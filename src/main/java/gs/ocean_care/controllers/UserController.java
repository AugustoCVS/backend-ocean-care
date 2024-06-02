package gs.ocean_care.controllers;

import gs.ocean_care.dtos.auth.AuthDto;
import gs.ocean_care.dtos.auth.RequestRefreshTokenDto;
import gs.ocean_care.dtos.auth.TokenResponseDto;
import gs.ocean_care.dtos.user.RegisterUserDto;
import gs.ocean_care.dtos.user.UpdateUserDto;
import gs.ocean_care.dtos.user.UserDto;
import gs.ocean_care.services.AuthService;
import gs.ocean_care.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterUserDto data) {
        userService.register(data);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody AuthDto data){
        var userAuthToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        manager.authenticate(userAuthToken);

        return ResponseEntity.ok(authService.getToken(data));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<TokenResponseDto> login(@RequestBody RequestRefreshTokenDto data){
        var token = authService.getRefreshToken(data.refreshToken());

        return ResponseEntity.ok(token);
    }

    @PutMapping("/update/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody @Valid UpdateUserDto data) {
        var updatedUser = userService.update(id, data);

        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        userService.delete(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Page<UserDto>> list(@PageableDefault(size = 20, page = 0) Pageable pageable) {
        var users = userService.findAll(pageable);

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        var user = userService.findById(id);

        return ResponseEntity.ok(user);
    }
}
