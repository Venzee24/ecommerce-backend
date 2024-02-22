package com.venzee.ecpj.ECPJ.controller;

import com.venzee.ecpj.ECPJ.dataTransferObject.user.SignInDto;
import com.venzee.ecpj.ECPJ.dataTransferObject.user.SignInResponseDto;
import com.venzee.ecpj.ECPJ.dataTransferObject.user.SignUpDto;
import com.venzee.ecpj.ECPJ.model.Role;
import com.venzee.ecpj.ECPJ.model.User;
import com.venzee.ecpj.ECPJ.repository.RoleRepository;
import com.venzee.ecpj.ECPJ.repository.TokenRepository;
import com.venzee.ecpj.ECPJ.repository.UserRepository;
import com.venzee.ecpj.ECPJ.security.CustomUserDetailsService;
import com.venzee.ecpj.ECPJ.security.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JWTService jwtService;
    private final CustomUserDetailsService customUserDetailsService;
    private final TokenRepository tokenRepository;

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody SignUpDto signUpDto){
        if (userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already existed.", HttpStatus.BAD_REQUEST);

        }
        User user = new User();
        user.setFirst_name(signUpDto.getFirstName());
        user.setLast_name(signUpDto.getLastName());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role role = roleRepository.findByName("USER").orElseThrow();
        user.setRoles(Collections.singletonList(role));
        userRepository.save(user);
        return new ResponseEntity<>("Register Success.",HttpStatus.OK);
    }
    @PostMapping("/signUp/admin")
    public ResponseEntity<String> signUpAdmin(@RequestBody SignUpDto signUpDto){
        if (userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already existed.", HttpStatus.BAD_REQUEST);

        }
        User user = new User();
        user.setFirst_name(signUpDto.getFirstName());
        user.setLast_name(signUpDto.getLastName());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role role = roleRepository.findByName("ADMIN").orElseThrow();
        user.setRoles(Collections.singletonList(role));
        userRepository.save(user);
        return new ResponseEntity<>("Register Success.",HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<SignInResponseDto> login(@RequestBody SignInDto signInDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDto.getEmail(),signInDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(signInDto.getEmail());
        String token = jwtService.generateToken(userDetails);
        String refreshedToken = jwtService.refreshedToken(token);
        SignInResponseDto signInResponseDto = new SignInResponseDto();
        signInResponseDto.setToken(token);
        signInResponseDto.setRefreshedToken(refreshedToken);
        return new ResponseEntity<>(signInResponseDto,HttpStatus.OK);
    }
}
