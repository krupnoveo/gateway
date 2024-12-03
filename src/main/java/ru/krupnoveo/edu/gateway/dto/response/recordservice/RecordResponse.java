package ru.krupnoveo.edu.gateway.dto.response.recordservice;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.UUID;

public record RecordResponse(
        UUID id,
        @JsonProperty("client_id") UUID clientId,
        @JsonProperty("client_first_name") String clientFirstName,
        @JsonProperty("barber_id") UUID barberId,
        @JsonProperty("barber_first_name") String barberFirstName,
        String grade,
        OffsetDateTime time,
        @JsonProperty("barbershop_id") UUID barbershopId,
        @JsonProperty("barbershop_address") String barbershopAddress,
        @JsonProperty("barbershop_phone_number") String barbershopPhoneNumber,
        @JsonProperty("service_id") UUID serviceId,
        @JsonProperty("service_name") String serviceName,
        Integer price
) {}
