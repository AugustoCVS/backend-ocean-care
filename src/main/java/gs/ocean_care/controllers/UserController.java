package gs.ocean_care.controllers;

import gs.ocean_care.dtos.auth.AuthDto;
import gs.ocean_care.dtos.user.RegisterUserDto;
import gs.ocean_care.infra.security.dataTokenJwt;
import gs.ocean_care.models.User;
import gs.ocean_care.services.AuthService;
import gs.ocean_care.services.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<dataTokenJwt> login(@RequestBody AuthDto data){
        var userAuthToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = manager.authenticate(userAuthToken);

        var tokenJwt = authService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new dataTokenJwt(tokenJwt));
    }

}
