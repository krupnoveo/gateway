package ru.krupnoveo.edu.gateway.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.krupnoveo.edu.gateway.dto.request.barbershopservice.CreateBarbershopRequest;
import ru.krupnoveo.edu.gateway.dto.request.barbershopservice.CreateServiceRequest;
import ru.krupnoveo.edu.gateway.dto.request.barbershopservice.UpdateBarbershopRequest;
import ru.krupnoveo.edu.gateway.dto.response.barbershopservice.BarbershopResponse;
import ru.krupnoveo.edu.gateway.dto.response.barbershopservice.ListBarbershopResponse;
import ru.krupnoveo.edu.gateway.dto.response.barbershopservice.ListServiceResponse;
import ru.krupnoveo.edu.gateway.dto.response.barbershopservice.ServiceResponse;

import java.util.UUID;

public interface BarbershopServiceClient {

    ResponseEntity<BarbershopResponse> getBarbershopById(UUID id);

    ResponseEntity<ListBarbershopResponse> getAllBarbershops();

    ResponseEntity<BarbershopResponse> createBarbershop(CreateBarbershopRequest request);

    ResponseEntity<BarbershopResponse> updateBarbershop(UUID id, UpdateBarbershopRequest request);

    ResponseEntity<BarbershopResponse> deleteBarbershop(UUID id);

    ResponseEntity<InputStreamResource> getBarbershopPhoto(UUID id);

    ResponseEntity<Void> setBarbershopPhoto(UUID id, MultipartFile photo);

    ResponseEntity<Void> deleteBarbershopPhoto(UUID id);

    ResponseEntity<ServiceResponse> getServiceById(UUID id);

    ResponseEntity<ListServiceResponse> getAllServices();

    ResponseEntity<ServiceResponse> createService(CreateServiceRequest request);

    ResponseEntity<ServiceResponse> updateService(UUID id, CreateServiceRequest request);

    ResponseEntity<ServiceResponse> deleteService(UUID id);
}
