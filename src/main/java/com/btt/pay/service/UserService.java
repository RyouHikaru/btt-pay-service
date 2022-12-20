package com.btt.pay.service;

import com.btt.pay.domain.dto.UserDTO;
import com.btt.pay.payload.request.RegisterRequest;
import com.btt.pay.payload.response.MessageResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    MessageResponse create(RegisterRequest request);
    UserDTO retrieveById(Long id);
    UserDTO update();
    String delete();
    boolean isUsernameUsed(String username);
    boolean isEmailUsed(String email);
}
