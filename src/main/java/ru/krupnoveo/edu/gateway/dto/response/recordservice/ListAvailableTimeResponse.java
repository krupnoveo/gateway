package ru.krupnoveo.edu.gateway.dto.response.recordservice;

import java.util.List;

public record ListAvailableTimeResponse(
        List<AvailableTimeResponse> times
) {
}
