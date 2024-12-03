package ru.krupnoveo.edu.gateway.controller.recordservice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.krupnoveo.edu.gateway.dto.request.recordservice.ChangeTimeRecordRequest;
import ru.krupnoveo.edu.gateway.dto.request.recordservice.CreateRecordRequest;
import ru.krupnoveo.edu.gateway.dto.response.recordservice.ListRecordResponse;
import ru.krupnoveo.edu.gateway.dto.response.recordservice.RecordResponse;
import ru.krupnoveo.edu.gateway.service.RecordServiceClient;

import java.util.UUID;

@RestController
@RequestMapping("/record")
@RequiredArgsConstructor
@CrossOrigin
public class RecordController {
    private final RecordServiceClient recordService;

    @PreAuthorize("hasAnyRole('ADMIN', 'ADMINISTRATOR', 'BARBER', 'CLIENT')")
    @GetMapping("/{id}")
    public ResponseEntity<RecordResponse> getRecordById(
            @PathVariable UUID id
    ) {
        return recordService.getRecordById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'ADMINISTRATOR', 'BARBER', 'CLIENT')")
    @GetMapping("/all")
    public ResponseEntity<ListRecordResponse> getAllRecords(
            @RequestHeader("Authorization") String token
    ) {
        return recordService.getAllRecords(token);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'ADMINISTRATOR', 'CLIENT')")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<RecordResponse> deleteRecord(
            @PathVariable UUID id
    ) {
        return recordService.deleteRecord(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'ADMINISTRATOR', 'CLIENT')")
    @PostMapping("/new")
    public ResponseEntity<RecordResponse> createRecord(
            @RequestBody CreateRecordRequest request,
            @RequestHeader("Authorization") String token
    ) {
        return recordService.createRecord(request, token);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'ADMINISTRATOR', 'CLIENT')")
    @PostMapping("/{id}/update/time")
    public ResponseEntity<RecordResponse> updateRecordTime(
            @PathVariable UUID id,
            @RequestBody ChangeTimeRecordRequest request
    ) {
        return recordService.updateRecordTime(id, request);
    }
}
