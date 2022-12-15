package com.btt.pay.service;

import com.btt.pay.domain.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDTO create();
    UserDTO retrieve();
    UserDTO update();
    String delete();

}
