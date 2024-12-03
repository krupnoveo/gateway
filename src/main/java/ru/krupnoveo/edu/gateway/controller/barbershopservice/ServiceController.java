package ru.krupnoveo.edu.gateway.controller.barbershopservice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.krupnoveo.edu.gateway.dto.request.barbershopservice.CreateServiceRequest;
import ru.krupnoveo.edu.gateway.dto.response.barbershopservice.ListServiceResponse;
import ru.krupnoveo.edu.gateway.dto.response.barbershopservice.ServiceResponse;
import ru.krupnoveo.edu.gateway.service.BarbershopServiceClient;

import java.util.UUID;

@RestController
@RequestMapping("/service")
@RequiredArgsConstructor
@CrossOrigin
public class ServiceController {

    private final BarbershopServiceClient serviceService;

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse> getServiceById(@PathVariable UUID id) {
        return serviceService.getServiceById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<ListServiceResponse> getAllServices() {
        return serviceService.getAllServices();
    }

    @PostMapping("/new")
    public ResponseEntity<ServiceResponse> createService(@RequestBody CreateServiceRequest request) {
        return serviceService.createService(request);
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<ServiceResponse> updateService(@PathVariable UUID id, @RequestBody CreateServiceRequest request) {
        return serviceService.updateService(id, request);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ServiceResponse> deleteService(@PathVariable UUID id) {
        return serviceService.deleteService(id);
    }
}