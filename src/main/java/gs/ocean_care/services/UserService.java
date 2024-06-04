package gs.ocean_care.services;

import gs.ocean_care.dtos.user.RegisterUserDto;
import gs.ocean_care.dtos.user.UpdateUserDto;
import gs.ocean_care.dtos.user.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

public interface UserService {

    public RegisterUserDto register(RegisterUserDto data);

    public UserDto update(Long id, UpdateUserDto data);

    public void delete(Long id);

    public Page<UserDto> findAll(Pageable pageable);

    public UserDto findById(Long id);
}
