package gs.ocean_care.services.impl;

import gs.ocean_care.dtos.user.RegisterUserDto;
import gs.ocean_care.models.User;
import gs.ocean_care.repositories.UserRepository;
import gs.ocean_care.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public RegisterUserDto register(RegisterUserDto data) {
        User user = new User(data);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        String encryptedConfirmPassword = bCryptPasswordEncoder.encode(user.getConfirm_password());
        user.setPassword(encryptedPassword);
        user.setConfirm_password(encryptedConfirmPassword);

        repository.save(user);

        return null;
    }
}
