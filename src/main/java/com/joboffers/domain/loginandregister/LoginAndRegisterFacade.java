package com.joboffers.domain.loginandregister;

import com.joboffers.domain.loginandregister.dto.RegisterRequestDto;
import com.joboffers.domain.loginandregister.dto.RegisterResultDto;
import com.joboffers.domain.loginandregister.dto.UserDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginAndRegisterFacade {

    public static final String USER_NOT_FOUND_MESSAGE = "User not found!";
    private final LoginRepository loginRepository;

    public UserDto findByUsername(String username) {
        return loginRepository.findByUsername(username)
                .map(user -> new UserDto(user.id(), user.username(), user.password()))
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_MESSAGE));
    }

    public RegisterResultDto register(RegisterRequestDto registerRequestDto) {
        User savedUser = loginRepository.save(UserMapper.mapToUserEntity(registerRequestDto));
        return new RegisterResultDto(savedUser.id(), savedUser.username(), true);
    }
}
