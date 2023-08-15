package com.bek.hr_management_system.service;

import com.bek.hr_management_system.entity.AttendanceHistory;
import com.bek.hr_management_system.entity.Role;
import com.bek.hr_management_system.entity.User;
import com.bek.hr_management_system.payload.ApiResponse;
import com.bek.hr_management_system.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

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

    //GET ALL EMPLOYEES FOR  DIRECTOR OR MANAGER
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

    //GET ATTENDANCE LIST FOR ONE EMPLOYEE
    public ApiResponse getAllAttendanceForOneEmployee(UUID id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Set<Role> roles = user.getRoles();
        for (Role role : roles){
            if (role.getName().name().equals("MANAGER") || role.getName().name().equals("WORKER")){
                return new ApiResponse("Director and HR_Manager can get Attendance List", false);
            }
        }
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()){
            return new ApiResponse("User not found", false);
        }
        List<AttendanceHistory> attendanceHistoryList = attendanceHistoryRepository.findAllByAttendanceUserId(id);
        if (attendanceHistoryList.isEmpty()){
            return new ApiResponse("Attendance History not found", false);
        }
        return new ApiResponse("Attendance History List: ", true, attendanceHistoryList);
    }

    //GET ATTENDANCE LIST BY EMPLOYEE
    public ApiResponse getAllAttendanceByEmployee(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Set<Role> roles = user.getRoles();
        for (Role role : roles){
            if (role.getName().name().equals("MANAGER") || role.getName().name().equals("WORKER")){
                return new ApiResponse("Only Director and HR_Manager can get List", false);
            }
        }
        List<AttendanceHistory> attendanceHistoryList = attendanceHistoryRepository.findAll();
        if (attendanceHistoryList.isEmpty()){
            return new ApiResponse("User Attendance not found", false);
        }
        return new ApiResponse("Attendance List: ", true, attendanceHistoryList);
    }


}
