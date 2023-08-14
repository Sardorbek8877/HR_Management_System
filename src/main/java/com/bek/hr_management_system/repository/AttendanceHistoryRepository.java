package com.bek.hr_management_system.repository;

import com.bek.hr_management_system.entity.AttendanceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttendanceHistoryRepository extends JpaRepository<AttendanceHistory, UUID> {

}
