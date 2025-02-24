package ru.krupnoveo.edu.gateway.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import ru.krupnoveo.edu.gateway.dto.request.userservice.AuthenticationRequest;
import ru.krupnoveo.edu.gateway.dto.request.userservice.CreateUserRequest;
import ru.krupnoveo.edu.gateway.dto.request.userservice.UpdatePasswordRequest;
import ru.krupnoveo.edu.gateway.dto.request.userservice.UpdateUserRequest;
import ru.krupnoveo.edu.gateway.dto.response.userservice.ListUserResponse;
import ru.krupnoveo.edu.gateway.dto.response.userservice.UserDetailsResponse;
import ru.krupnoveo.edu.gateway.dto.response.userservice.UserResponse;
import ru.krupnoveo.edu.gateway.service.UserServiceClient;

import java.util.UUID;


@Service
public class UserServiceClientImpl implements UserServiceClient {

    private final WebClient webClient;

    public UserServiceClientImpl(
            @Value("${user.service.host}") String userServiceHost,
            @Value("${user.service.port}") String userServicePort
    ) {
        String userServiceUrl = "http://" + userServiceHost + ":" + userServicePort;
        this.webClient = WebClient.builder()
                .baseUrl(userServiceUrl)
                .build();
    }

    @Override
    public ResponseEntity<UserResponse> registerClient(CreateUserRequest registerUserDto) {
        var user = webClient
                .post()
                .uri("/auth/signup")
                .bodyValue(registerUserDto)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<UserResponse> registerBarber(CreateUserRequest registerUserDto) {
        var user = webClient
                .post()
                .uri("/auth/barber/signup")
                .bodyValue(registerUserDto)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<UserResponse> registerAdministrator(CreateUserRequest registerUserDto) {
        var user = webClient
                .post()
                .uri("/auth/administrator/signup")
                .bodyValue(registerUserDto)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<UserResponse> authenticate(AuthenticationRequest authenticationRequest) {
        var user = webClient
                .post()
                .uri("/auth/signin")
                .bodyValue(authenticationRequest)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();
        return ResponseEntity.ok(user);
    }

    @Override
    public UserDetailsResponse verifyToken(String token) {
        return webClient
                .get()
                .uri("/auth/verify?token={token}", token)
                .retrieve()
                .bodyToMono(UserDetailsResponse.class)
                .block();
    }

    @Override
    public ResponseEntity<UserResponse> getUserById(String token) {
        var resp = webClient
                .get()
                .uri("/me")
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();
        return ResponseEntity.ok().body(resp);
    }

    @Override
    public ResponseEntity<ListUserResponse> getAllUsers(UUID barbershopId, String role) {
        var resp = webClient
                .get()
                .uri("/me/all?barbershopId={barbershopId}&role={role}", barbershopId, role)
                .retrieve()
                .bodyToMono(ListUserResponse.class)
                .block();
        return ResponseEntity.ok().body(resp);
    }

    @Override
    public ResponseEntity<UserResponse> deleteUser(String token) {
        var resp = webClient
                .delete()
                .uri("/me/delete")
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();
        return ResponseEntity.ok().body(resp);
    }

    @Override
    public ResponseEntity<UserResponse> updateUser(UpdateUserRequest updateUserRequest, String token) {
        var resp = webClient
                .post()
                .uri("/me/update/data")
                .header("Authorization", token)
                .bodyValue(updateUserRequest)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();
        return ResponseEntity.ok().body(resp);
    }

    @Override
    public ResponseEntity<UserResponse> updatePassword(UpdatePasswordRequest updatePasswordRequest, String token) {
        var resp = webClient
                .post()
                .uri("/me/update/password")
                .header("Authorization", token)
                .bodyValue(updatePasswordRequest)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();
        return ResponseEntity.ok().body(resp);
    }

    @Override
    public ResponseEntity<InputStreamResource> getPhoto(String token) {
        var resp = webClient
                .get()
                .uri("/me/photo")
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(InputStreamResource.class)
                .block();
        return ResponseEntity.ok().body(resp);
    }

    @Override
    public ResponseEntity<Void> setPhoto(MultipartFile file, String token) {
        var resp = webClient
                .post()
                .uri("/me/photo")
                .header("Authorization", token)
                .bodyValue(file)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deletePhoto(String token) {
        var resp = webClient
                .delete()
                .uri("/me/photo")
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
        return ResponseEntity.ok().build();
    }
}
