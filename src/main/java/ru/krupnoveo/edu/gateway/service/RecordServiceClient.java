package ru.krupnoveo.edu.gateway.service;

import org.springframework.http.ResponseEntity;
import ru.krupnoveo.edu.gateway.dto.request.recordservice.ChangeTimeRecordRequest;
import ru.krupnoveo.edu.gateway.dto.request.recordservice.CreateRecordRequest;
import ru.krupnoveo.edu.gateway.dto.response.recordservice.ListAvailableTimeResponse;
import ru.krupnoveo.edu.gateway.dto.response.recordservice.ListRecordResponse;
import ru.krupnoveo.edu.gateway.dto.response.recordservice.RecordResponse;

import java.util.UUID;

public interface RecordServiceClient {

    ResponseEntity<RecordResponse> getRecordById(UUID id);

    ResponseEntity<ListRecordResponse> getAllRecords(String token);

    ResponseEntity<RecordResponse> deleteRecord(UUID id);

    ResponseEntity<RecordResponse> createRecord(CreateRecordRequest request, String token);

    ResponseEntity<RecordResponse> updateRecordTime(UUID id, ChangeTimeRecordRequest request);

    ResponseEntity<ListAvailableTimeResponse> getAllAvailableTimes(UUID barberId);

}
