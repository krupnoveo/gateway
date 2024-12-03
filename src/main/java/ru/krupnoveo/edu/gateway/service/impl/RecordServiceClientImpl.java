package ru.krupnoveo.edu.gateway.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.krupnoveo.edu.gateway.dto.request.recordservice.ChangeTimeRecordRequest;
import ru.krupnoveo.edu.gateway.dto.request.recordservice.CreateRecordRequest;
import ru.krupnoveo.edu.gateway.dto.response.recordservice.ListAvailableTimeResponse;
import ru.krupnoveo.edu.gateway.dto.response.recordservice.ListRecordResponse;
import ru.krupnoveo.edu.gateway.dto.response.recordservice.RecordResponse;
import ru.krupnoveo.edu.gateway.service.RecordServiceClient;

import java.util.UUID;

@Service
public class RecordServiceClientImpl implements RecordServiceClient {

    private static final String RECORD_SERVICE_URL = "http://localhost:8082";

    private final WebClient client;

    public RecordServiceClientImpl() {
        this.client = WebClient.builder()
                .baseUrl(RECORD_SERVICE_URL)
                .build();
    }

    @Override
    public ResponseEntity<RecordResponse> getRecordById(UUID id) {
        var resp = client
                .get()
                .uri("/record/{id}", id)
                .retrieve()
                .bodyToMono(RecordResponse.class)
                .block();
        return ResponseEntity.ok().body(resp);
    }

    @Override
    public ResponseEntity<ListRecordResponse> getAllRecords(String token) {
        var resp = client
                .get()
                .uri("/record/all")
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(ListRecordResponse.class)
                .block();
        return ResponseEntity.ok().body(resp);
    }

    @Override
    public ResponseEntity<RecordResponse> deleteRecord(UUID id) {
        var resp = client
                .delete()
                .uri("/record/{id}/delete", id)
                .retrieve()
                .bodyToMono(RecordResponse.class)
                .block();
        return ResponseEntity.ok().body(resp);
    }

    @Override
    public ResponseEntity<RecordResponse> createRecord(CreateRecordRequest request, String token) {
        var resp = client
                .post()
                .uri("/record/new")
                .header("Authorization", token)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(RecordResponse.class)
                .block();
        return ResponseEntity.ok().body(resp);
    }

    @Override
    public ResponseEntity<RecordResponse> updateRecordTime(UUID id, ChangeTimeRecordRequest request) {
        var resp = client
                .post()
                .uri("/record/{id}/update/time", id)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(RecordResponse.class)
                .block();
        return ResponseEntity.ok().body(resp);
    }

    @Override
    public ResponseEntity<ListAvailableTimeResponse> getAllAvailableTimes(UUID barberId) {
        var resp = client
                .get()
                .uri("/available_time/all?barberId={barberId}", barberId)
                .retrieve()
                .bodyToMono(ListAvailableTimeResponse.class)
                .block();
        return ResponseEntity.ok().body(resp);
    }
}
