package ru.krupnoveo.edu.gateway.controller.userservice;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.krupnoveo.edu.gateway.dto.request.userservice.UpdatePasswordRequest;
import ru.krupnoveo.edu.gateway.dto.request.userservice.UpdateUserRequest;
import ru.krupnoveo.edu.gateway.dto.response.userservice.ListUserResponse;
import ru.krupnoveo.edu.gateway.dto.response.userservice.UserResponse;
import ru.krupnoveo.edu.gateway.service.UserServiceClient;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/me")
@CrossOrigin
public class UserController {
    private final UserServiceClient userService;


    @PreAuthorize("hasAnyRole('ADMIN', 'BARBER', 'CLIENT', 'ADMINISTRATOR')")
    @GetMapping
    public ResponseEntity<UserResponse> getUserById(@RequestHeader("Authorization") String token) {
        return userService.getUserById(token);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'BARBER', 'CLIENT', 'ADMINISTRATOR')")
    @GetMapping("/all")
    public ResponseEntity<ListUserResponse> getAllUsers(
            @RequestParam(required = false) UUID barbershopId,
            @RequestParam (required = false) String role
    ) {
        return userService.getAllUsers(barbershopId, role);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'BARBER', 'CLIENT', 'ADMINISTRATOR')")
    @DeleteMapping("/delete")
    public ResponseEntity<UserResponse> deleteUser(@RequestHeader("Authorization") String token) {
        return userService.deleteUser(token);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'BARBER', 'CLIENT', 'ADMINISTRATOR')")
    @PostMapping("/update/data")
    public ResponseEntity<UserResponse> updateUser(
            @RequestBody UpdateUserRequest updateUserRequest,
            @RequestHeader("Authorization") String token
    ) {
        return userService.updateUser(updateUserRequest, token);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'BARBER', 'CLIENT', 'ADMINISTRATOR')")
    @PostMapping("/update/password")
    public ResponseEntity<UserResponse> updatePassword(
            @RequestBody UpdatePasswordRequest updatePasswordRequest,
            @RequestHeader("Authorization") String token
    ) {
        return userService.updatePassword(updatePasswordRequest, token);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'BARBER', 'CLIENT', 'ADMINISTRATOR')")
    @GetMapping("/photo")
    public ResponseEntity<InputStreamResource> getPhoto(@RequestHeader("Authorization") String token) {
        return userService.getPhoto(token);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'BARBER', 'CLIENT', 'ADMINISTRATOR')")
    @PostMapping("/photo")
    public ResponseEntity<Void> setPhoto(
            @RequestParam(value = "photo") MultipartFile file,
            @RequestHeader("Authorization") String token
    ) {
        return userService.setPhoto(file, token);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'BARBER', 'CLIENT', 'ADMINISTRATOR')")
    @DeleteMapping("/photo")
    public ResponseEntity<Void> deletePhoto(@RequestHeader("Authorization") String token) {
        return userService.deletePhoto(token);
    }
}
