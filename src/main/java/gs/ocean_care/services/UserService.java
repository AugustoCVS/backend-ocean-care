package gs.ocean_care.services;

import gs.ocean_care.dtos.user.RegisterUserDto;

public interface UserService {

    public RegisterUserDto register(RegisterUserDto data);
}
