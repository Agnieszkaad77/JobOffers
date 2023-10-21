package com.joboffers.domain.loginandregister;

import com.joboffers.domain.loginandregister.dto.RegisterRequestDto;
import com.joboffers.domain.loginandregister.dto.RegisterResultDto;
import com.joboffers.domain.loginandregister.dto.UserDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class LoginAndRegisterFacadeTest {

    LoginAndRegisterFacade loginAndRegisterFacade = new LoginAndRegisterFacade(new LoginRepositoryImpl());

    @Test
    public void should_find_user_by_username() {
        // given
        RegisterRequestDto registerRequestDto = new RegisterRequestDto("username", "password");
        RegisterResultDto registerResultDto = loginAndRegisterFacade.register(registerRequestDto);

        // when
        UserDto userByUsername = loginAndRegisterFacade.findByUsername(registerResultDto.username());

        // then
        assertThat(userByUsername).isEqualTo(
                new UserDto(registerResultDto.id(), "username", "password"));
    }

    @Test
    public void should_throw_exception_if_user_not_found() {
        // when
        String username = "username";

        // then
        assertThrows(UserNotFoundException.class,
                () -> loginAndRegisterFacade.findByUsername(username), "User not found!");
    }

    @Test
    public void should_register_user() {
        // given
        RegisterRequestDto registerRequestDto = new RegisterRequestDto("username", "password");

        // when
        RegisterResultDto registerResultDto = loginAndRegisterFacade.register(registerRequestDto);

        // then
        assertAll(
                () -> assertThat(registerResultDto.username()).isEqualTo("username"),
                () -> assertThat(registerResultDto.created()).isTrue()
        );
    }
}