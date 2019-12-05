package com.staxter.register;

import com.staxter.user.UserDto;
import com.staxter.user.UserDtoConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
class RegistrationController {

    private final RegistrationService registrationService;
    private final UserDtoConverter userDtoConverter;
    private final UserRegistrationDtoConverter userRegistrationDtoConverter;

    RegistrationController(RegistrationService registrationService, UserDtoConverter userDtoConverter, UserRegistrationDtoConverter userRegistrationDtoConverter) {
        this.registrationService = registrationService;
        this.userDtoConverter = userDtoConverter;
        this.userRegistrationDtoConverter = userRegistrationDtoConverter;
    }

    @PostMapping("/userservice/register")
    UserDto register(@RequestBody @Valid UserRegistrationDto user) {
        return userDtoConverter.convertToDto(
                registrationService.register(
                        userRegistrationDtoConverter.convertFromDto(user)
                )
        );
    }


}
