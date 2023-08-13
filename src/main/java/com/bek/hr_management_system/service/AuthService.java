package com.bek.hr_management_system.service;

import com.bek.hr_management_system.entity.User;
import com.bek.hr_management_system.entity.enums.RoleName;
import com.bek.hr_management_system.payload.ApiResponse;
import com.bek.hr_management_system.payload.LoginDto;
import com.bek.hr_management_system.payload.RegisterDto;
import com.bek.hr_management_system.repository.RoleRepository;
import com.bek.hr_management_system.repository.UserRepository;
import com.bek.hr_management_system.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;

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
        //EMAIL SENDING
        sendEmail(user.getEmail(), user.getEmailCode());

        return new ApiResponse("Successfully registered", true);
    }

    public ApiResponse verifyEmail(String emailCode, String email){
        Optional<User> optionalUser = userRepository.findByEmailAndEmailCode(email, emailCode);
        if (optionalUser.isEmpty())
            return new ApiResponse("Account already accepted", false);

        User user = optionalUser.get();
        user.setEnabled(true);
        user.setEmailCode(null);
        userRepository.save(user);
        return new ApiResponse("Account accepted", true);
    }

    public Boolean sendEmail(String sendingEmail, String emailCode){

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("example@gmail.com");
            message.setTo(sendingEmail);
            message.setSubject("Akkountni tasdiqlash");
            message.setText("<a href='https://bek.com/api/auth/verifyEmail?emailCode=" + emailCode + "&email=" + sendingEmail + "'>Tasdiqlash</a>");
            javaMailSender.send(message);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public ApiResponse login(LoginDto loginDto){
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(),
                    loginDto.getPassword()));
            User user = (User) authenticate.getPrincipal();
            String token = jwtProvider.generateToken(loginDto.getUsername(), user.getRoles());
            return new ApiResponse("Token", true, token);
        }
        catch (BadCredentialsException badCredentialsException){
            return new ApiResponse("Password or email incorrect", false);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}
