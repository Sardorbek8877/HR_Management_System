package com.bek.hr_management_system.repository;

import com.bek.hr_management_system.entity.Attendance;
import com.bek.hr_management_system.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttendanceRepository extends JpaRepository<Attendance, UUID> {

}
