package com.bek.hr_management_system.repository;

import com.bek.hr_management_system.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}
