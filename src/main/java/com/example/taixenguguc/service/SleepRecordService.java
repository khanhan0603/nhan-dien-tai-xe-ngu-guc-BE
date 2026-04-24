package com.example.taixenguguc.service;

import com.example.taixenguguc.entity.SleepRecord;
import com.example.taixenguguc.repository.SleepRecordRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class SleepRecordService {
    SleepRecordRepository sleepRecordRepository;

    public SleepRecord createRecord(SleepRecord record){
        return sleepRecordRepository.save(record);
    }

    public List<SleepRecord> findRecordById(String id){
        return sleepRecordRepository.findByUserId(id);
    }

}
