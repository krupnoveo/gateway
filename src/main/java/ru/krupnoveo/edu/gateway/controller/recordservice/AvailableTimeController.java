package ru.krupnoveo.edu.gateway.controller.recordservice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.krupnoveo.edu.gateway.dto.response.recordservice.ListAvailableTimeResponse;
import ru.krupnoveo.edu.gateway.service.RecordServiceClient;

import java.util.UUID;

@RestController
@RequestMapping("/available_time")
@RequiredArgsConstructor
@CrossOrigin
public class AvailableTimeController {

    private final RecordServiceClient availableTimeService;

    @GetMapping("/all")
    public ResponseEntity<ListAvailableTimeResponse> getAllAvailableTimes(@RequestParam(required = false) UUID barberId) {
        return availableTimeService.getAllAvailableTimes(barberId);
    }
}
