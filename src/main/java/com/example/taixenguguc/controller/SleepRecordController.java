package com.example.taixenguguc.controller;

import com.example.taixenguguc.dbo.response.ApiResponse;
import com.example.taixenguguc.entity.SleepRecord;
import com.example.taixenguguc.service.AuthService;
import com.example.taixenguguc.service.SleepRecordService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/records")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class SleepRecordController {
    SleepRecordService sleepRecordService;
    AuthService authService;

    @PostMapping
    ApiResponse<SleepRecord> createRecord(@RequestBody @Valid SleepRecord record){
        ApiResponse<SleepRecord> apiResponse=new ApiResponse<>();

        apiResponse.setResult(sleepRecordService.createRecord(record));

        return apiResponse;
    }

    @GetMapping
    List<SleepRecord> findAllRecord(@RequestHeader("Authorization") String authHeader){
        // format: Bearer token
        String token = authHeader.replace("Bearer ", "");

        String id = authService.extractUserId(token);

        return sleepRecordService.findRecordById(id);
    }
}
