package com.btt.pay.service.impl;

import com.btt.pay.config.JwtUtils;
import com.btt.pay.domain.dto.UserDTO;
import com.btt.pay.payload.request.LoginRequest;
import com.btt.pay.payload.request.RegisterRequest;
import com.btt.pay.payload.response.JwtResponse;
import com.btt.pay.service.AuthService;
import com.btt.pay.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final String BEARER = "Bearer";

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    public JwtResponse authenticateUser(LoginRequest request) {
        log.debug("AUTH SERVICE authenticateUser: {}", request);

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDTO userDTO = (UserDTO) authentication.getPrincipal();

        JwtResponse response = new JwtResponse(
                jwtUtils.generateToken(userDTO),
                BEARER,
                userDTO.getId(),
                userDTO.getUsername(),
                userDTO.getEmail());

        return response;
    }

    @Override
    public UserDTO registerUser(RegisterRequest request) {
        log.debug("AUTH SERVICE registerUser: {}", request);

        request.setPassword(passwordEncoder.encode(request.getPassword()));

        UserDTO newUserDTO = userService.create(request);

        return newUserDTO;
    }

    @Override
    public boolean isUsernameUsed(String username) {
        return userService.isUsernameUsed(username);
    }

    @Override
    public boolean isEmailUsed(String email) {
        return userService.isEmailUsed(email);
    }

}
