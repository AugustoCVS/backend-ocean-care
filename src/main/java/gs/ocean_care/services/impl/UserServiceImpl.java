package gs.ocean_care.services.impl;

import gs.ocean_care.dtos.user.RegisterUserDto;
import gs.ocean_care.dtos.user.UpdateUserDto;
import gs.ocean_care.dtos.user.UserDto;
import gs.ocean_care.models.User;
import gs.ocean_care.repositories.UserRepository;
import gs.ocean_care.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @Override
    public UserDto update(Long id, UpdateUserDto data) {
        User user = repository.getReferenceById(id);
        user.updateUser(data);

        return new UserDto(user);
    }

    @Override
    public void delete(Long id){
        User user = repository.getReferenceById(id);
        user.softDelete();
    }

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(UserDto::new);
    }
}
