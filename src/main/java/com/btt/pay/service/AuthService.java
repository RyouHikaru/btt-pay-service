package com.btt.pay.service;

import com.btt.pay.payload.request.LoginRequest;
import com.btt.pay.payload.request.RegisterRequest;
import com.btt.pay.payload.response.JwtResponse;
import com.btt.pay.payload.response.MessageResponse;

public interface AuthService {

    JwtResponse authenticateUser(LoginRequest request);

    MessageResponse registerUser(RegisterRequest request);

    boolean isUsernameUsed(String username);

    boolean isEmailUsed(String email);
}
