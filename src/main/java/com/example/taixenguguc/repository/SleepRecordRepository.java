package com.example.taixenguguc.repository;

import com.example.taixenguguc.entity.SleepRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SleepRecordRepository extends JpaRepository<SleepRecord,Long> {
    List<SleepRecord> findByUserId(String id);
}
