package com.bek.hr_management_system.service;

import com.bek.hr_management_system.entity.Role;
import com.bek.hr_management_system.entity.User;
import com.bek.hr_management_system.payload.ApiResponse;
import com.bek.hr_management_system.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class EmployeeService {

    @Autowired
    AttendanceRepository attendanceRepository;
    @Autowired
    AttendanceHistoryRepository attendanceHistoryRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    SalaryRepository salaryRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;

    public ApiResponse getAllEmployee(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Set<Role> roles = user.getRoles();
        for (Role role : roles){
            if (role.getName().name().equals("DIRECTOR") || role.getName().name().equals("HR_MANAGER")){
                List<User> employeeList = userRepository.findAll();
                return new ApiResponse("List of all Employees", true, employeeList);
            }
            return new ApiResponse("Only Director or HR_Manager can see the employee list", false);
        }
        return new ApiResponse("Cannot get the list", false);
    }

}
