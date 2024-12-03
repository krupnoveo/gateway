package ru.krupnoveo.edu.gateway.dto.response.barbershopservice;

import java.util.List;

public record ListBarbershopResponse(
        List<BarbershopResponse> barbershops
) {
}
