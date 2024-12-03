package ru.krupnoveo.edu.gateway.dto.request.userservice;

public record AuthenticationRequest(
        String email,
        String password
) {
}
