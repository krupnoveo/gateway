package ru.krupnoveo.edu.gateway.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.krupnoveo.edu.gateway.dto.request.userservice.AuthenticationRequest;
import ru.krupnoveo.edu.gateway.dto.request.userservice.CreateUserRequest;
import ru.krupnoveo.edu.gateway.dto.request.userservice.UpdatePasswordRequest;
import ru.krupnoveo.edu.gateway.dto.request.userservice.UpdateUserRequest;
import ru.krupnoveo.edu.gateway.dto.response.userservice.ListUserResponse;
import ru.krupnoveo.edu.gateway.dto.response.userservice.UserDetailsResponse;
import ru.krupnoveo.edu.gateway.dto.response.userservice.UserResponse;

import java.util.UUID;

public interface UserServiceClient {
    ResponseEntity<UserResponse> registerClient(CreateUserRequest registerUserDto);

    ResponseEntity<UserResponse> registerBarber(CreateUserRequest registerUserDto);

    ResponseEntity<UserResponse> registerAdministrator(CreateUserRequest registerUserDto);

    ResponseEntity<UserResponse> authenticate(AuthenticationRequest authenticationRequest);

    UserDetailsResponse verifyToken(String token);

    ResponseEntity<UserResponse> getUserById(String token);

    ResponseEntity<ListUserResponse> getAllUsers(UUID barbershopId, String role);

    ResponseEntity<UserResponse> deleteUser(String token);

    ResponseEntity<UserResponse> updateUser(UpdateUserRequest updateUserRequest, String token);

    ResponseEntity<UserResponse> updatePassword(UpdatePasswordRequest updatePasswordRequest, String token);

    ResponseEntity<InputStreamResource> getPhoto(String token);

    ResponseEntity<Void> setPhoto(MultipartFile file, String token);

    ResponseEntity<Void> deletePhoto(String token);
}
