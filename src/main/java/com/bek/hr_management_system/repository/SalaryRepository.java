package com.bek.hr_management_system.repository;

import com.bek.hr_management_system.entity.Salary;
import com.bek.hr_management_system.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepository extends JpaRepository<Salary, Integer> {

}
