package ru.krupnoveo.edu.gateway.service.impl;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import ru.krupnoveo.edu.gateway.dto.request.barbershopservice.CreateBarbershopRequest;
import ru.krupnoveo.edu.gateway.dto.request.barbershopservice.CreateServiceRequest;
import ru.krupnoveo.edu.gateway.dto.request.barbershopservice.UpdateBarbershopRequest;
import ru.krupnoveo.edu.gateway.dto.response.barbershopservice.BarbershopResponse;
import ru.krupnoveo.edu.gateway.dto.response.barbershopservice.ListBarbershopResponse;
import ru.krupnoveo.edu.gateway.dto.response.barbershopservice.ListServiceResponse;
import ru.krupnoveo.edu.gateway.dto.response.barbershopservice.ServiceResponse;
import ru.krupnoveo.edu.gateway.service.BarbershopServiceClient;

import java.util.UUID;


@Service
public class BarbershopServiceClientImpl implements BarbershopServiceClient {
    private static final String BARBERSHOP_SERVICE_URL = "http://localhost:8081";

    private final WebClient client;

    public BarbershopServiceClientImpl() {
        this.client = WebClient.builder()
                .baseUrl(BARBERSHOP_SERVICE_URL)
                .build();
    }


    @Override
    public ResponseEntity<BarbershopResponse> getBarbershopById(UUID id) {
        var resp = client
                .get()
                .uri("/barbershop/{id}", id)
                .retrieve()
                .bodyToMono(BarbershopResponse.class)
                .block();
        return ResponseEntity.ok().body(resp);
    }

    @Override
    public ResponseEntity<ListBarbershopResponse> getAllBarbershops() {
        var resp = client
                .get()
                .uri("/barbershop/all")
                .retrieve()
                .bodyToMono(ListBarbershopResponse.class)
                .block();
        return ResponseEntity.ok().body(resp);
    }

    @Override
    public ResponseEntity<BarbershopResponse> createBarbershop(CreateBarbershopRequest request) {
        var resp = client
                .post()
                .uri("/barbershop/new")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(BarbershopResponse.class)
                .block();
        return ResponseEntity.ok().body(resp);
    }

    @Override
    public ResponseEntity<BarbershopResponse> updateBarbershop(UUID id, UpdateBarbershopRequest request) {
        var resp = client
                .post()
                .uri("/barbershop/{id}/update", id)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(BarbershopResponse.class)
                .block();
        return ResponseEntity.ok().body(resp);
    }

    @Override
    public ResponseEntity<BarbershopResponse> deleteBarbershop(UUID id) {
        var resp = client
                .delete()
                .uri("/barbershop/{id}/delete", id)
                .retrieve()
                .bodyToMono(BarbershopResponse.class)
                .block();
        return ResponseEntity.ok().body(resp);
    }

    @Override
    public ResponseEntity<InputStreamResource> getBarbershopPhoto(UUID id) {
        var resp = client
                .get()
                .uri("/barbershop/{id}/photo", id)
                .retrieve()
                .bodyToMono(InputStreamResource.class)
                .block();
        return ResponseEntity.ok().body(resp);
    }

    @Override
    public ResponseEntity<Void> setBarbershopPhoto(UUID id, MultipartFile photo) {
        var resp = client
                .post()
                .uri("/barbershop/{id}/photo", id)
                .bodyValue(photo)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteBarbershopPhoto(UUID id) {
        var resp = client
                .delete()
                .uri("/barbershop/{id}/photo", id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<ServiceResponse> getServiceById(UUID id) {
        var resp = client
                .get()
                .uri("/service/{id}", id)
                .retrieve()
                .bodyToMono(ServiceResponse.class)
                .block();
        return ResponseEntity.ok().body(resp);
    }

    @Override
    public ResponseEntity<ListServiceResponse> getAllServices() {
        var resp = client
                .get()
                .uri("/service/all")
                .retrieve()
                .bodyToMono(ListServiceResponse.class)
                .block();
        return ResponseEntity.ok().body(resp);
    }

    @Override
    public ResponseEntity<ServiceResponse> createService(CreateServiceRequest request) {
        var resp = client
                .post()
                .uri("/service/new")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ServiceResponse.class)
                .block();
        return ResponseEntity.ok().body(resp);
    }

    @Override
    public ResponseEntity<ServiceResponse> updateService(UUID id, CreateServiceRequest request) {
        var resp = client
                .post()
                .uri("/service/{id}/update", id)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ServiceResponse.class)
                .block();
        return ResponseEntity.ok().body(resp);
    }

    @Override
    public ResponseEntity<ServiceResponse> deleteService(UUID id) {
        var resp = client
                .delete()
                .uri("/service/{id}/delete", id)
                .retrieve()
                .bodyToMono(ServiceResponse.class)
                .block();
        return ResponseEntity.ok().body(resp);
    }
}
