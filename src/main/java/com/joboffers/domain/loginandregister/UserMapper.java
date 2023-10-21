package com.joboffers.domain.loginandregister;

import com.joboffers.domain.loginandregister.dto.RegisterRequestDto;

public class UserMapper {
    public static User mapToUserEntity(RegisterRequestDto registerRequestDto) {
        return User.builder()
                .username(registerRequestDto.username())
                .password(registerRequestDto.password())
                .build();
    }
}
