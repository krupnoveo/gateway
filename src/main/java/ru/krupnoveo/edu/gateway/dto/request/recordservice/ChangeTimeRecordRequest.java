package ru.krupnoveo.edu.gateway.dto.request.recordservice;

import java.util.UUID;

public record ChangeTimeRecordRequest(
        UUID time
) {
}
