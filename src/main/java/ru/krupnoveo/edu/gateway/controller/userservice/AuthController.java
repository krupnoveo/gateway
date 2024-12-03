package ru.krupnoveo.edu.gateway.controller.userservice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.krupnoveo.edu.gateway.dto.request.userservice.AuthenticationRequest;
import ru.krupnoveo.edu.gateway.dto.request.userservice.CreateUserRequest;
import ru.krupnoveo.edu.gateway.dto.response.userservice.UserResponse;
import ru.krupnoveo.edu.gateway.service.UserServiceClient;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    private final UserServiceClient userServiceClient;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> registerClient(@RequestBody CreateUserRequest registerUserDto) {
        return userServiceClient.registerClient(registerUserDto);
    }

    @PostMapping("/barber/signup")
    @PreAuthorize("hasAnyRole('ADMIN', 'ADMINISTRATOR')")
    public ResponseEntity<UserResponse> registerBarber(@RequestBody CreateUserRequest registerUserDto) {
        return userServiceClient.registerBarber(registerUserDto);
    }

    @PostMapping("/administrator/signup")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> registerAdministrator(@RequestBody CreateUserRequest registerUserDto) {
        return userServiceClient.registerAdministrator(registerUserDto);
    }

    @PostMapping("/signin")
    public ResponseEntity<UserResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return userServiceClient.authenticate(authenticationRequest);
    }
}
