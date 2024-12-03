package ru.krupnoveo.edu.gateway.dto.request.barbershopservice;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateBarbershopRequest(
    String name,
    String address,
    @JsonProperty("phone_number") String phoneNumber,
    @JsonProperty("working_time") String workingTime
) {
}
