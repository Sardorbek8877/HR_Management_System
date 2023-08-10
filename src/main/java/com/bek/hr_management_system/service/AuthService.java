package com.bek.hr_management_system.service;

import com.bek.hr_management_system.entity.User;
import com.bek.hr_management_system.entity.enums.RoleName;
import com.bek.hr_management_system.payload.ApiResponse;
import com.bek.hr_management_system.payload.RegisterDto;
import com.bek.hr_management_system.repository.RoleRepository;
import com.bek.hr_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;

    public ApiResponse registerUser(RegisterDto registerDto){
        boolean existsByEmail = userRepository.existsByEmail(registerDto.getEmail());
        if (existsByEmail)
            return new ApiResponse("Email already exist", false);

        User user = new User();
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(registerDto.getPassword());
        user.setRoles(roleRepository.findAllByName(RoleName.ROLE_DIRECTOR));

        user.setEmailCode(UUID.randomUUID().toString());

        userRepository.save(user);
        return new ApiResponse("Registered", true);
    }
}
