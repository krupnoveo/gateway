package ru.krupnoveo.edu.gateway.controller.barbershopservice;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.krupnoveo.edu.gateway.dto.request.barbershopservice.CreateBarbershopRequest;
import ru.krupnoveo.edu.gateway.dto.request.barbershopservice.UpdateBarbershopRequest;
import ru.krupnoveo.edu.gateway.dto.response.barbershopservice.BarbershopResponse;
import ru.krupnoveo.edu.gateway.dto.response.barbershopservice.ListBarbershopResponse;
import ru.krupnoveo.edu.gateway.service.BarbershopServiceClient;

import java.util.UUID;

@RestController
@RequestMapping("/barbershop")
@RequiredArgsConstructor
@CrossOrigin
public class BarbershopController {

    private final BarbershopServiceClient barbershopService;

    @GetMapping("/{id}")
    public ResponseEntity<BarbershopResponse> getBarbershopById(
            @PathVariable UUID id
    ) {
        return barbershopService.getBarbershopById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<ListBarbershopResponse> getAllBarbershops() {
        return barbershopService.getAllBarbershops();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/new")
    public ResponseEntity<BarbershopResponse> createBarbershop(
            @RequestBody CreateBarbershopRequest request
    ) {
        return barbershopService.createBarbershop(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/update")
    public ResponseEntity<BarbershopResponse> updateBarbershop(
            @PathVariable UUID id,
            @RequestBody UpdateBarbershopRequest request
    ) {
        return barbershopService.updateBarbershop(id, request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<BarbershopResponse> deleteBarbershop(
            @PathVariable UUID id
    ) {
        return barbershopService.deleteBarbershop(id);
    }

    @GetMapping("/{id}/photo")
    public ResponseEntity<InputStreamResource> getBarbershopPhoto(
            @PathVariable UUID id
    ) {
        return barbershopService.getBarbershopPhoto(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/photo")
    public ResponseEntity<Void> setBarbershopPhoto(
            @PathVariable UUID id,
            @RequestParam(value = "photo") MultipartFile photo
    ) {
        return barbershopService.setBarbershopPhoto(id, photo);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}/photo")
    public ResponseEntity<Void> deleteBarbershopPhoto(
            @PathVariable UUID id
    ) {
        return barbershopService.deleteBarbershopPhoto(id);
    }
}

