package com.btt.pay.service;

import com.btt.pay.domain.dto.UserDTO;
import com.btt.pay.payload.request.LoginRequest;
import com.btt.pay.payload.request.RegisterRequest;
import com.btt.pay.payload.response.JwtResponse;

public interface AuthService {

    JwtResponse authenticateUser(LoginRequest request);

    UserDTO registerUser(RegisterRequest request);

    boolean isUsernameUsed(String username);

    boolean isEmailUsed(String email);
}
