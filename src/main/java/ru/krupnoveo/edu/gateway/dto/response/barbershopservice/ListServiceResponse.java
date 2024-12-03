package ru.krupnoveo.edu.gateway.dto.response.barbershopservice;

import java.util.List;

public record ListServiceResponse(
        List<ServiceResponse> services
) {
}
