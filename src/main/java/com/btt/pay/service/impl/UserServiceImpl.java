package com.btt.pay.service.impl;

import com.btt.pay.domain.User;
import com.btt.pay.domain.dto.MetadataDTO;
import com.btt.pay.domain.dto.UserDTO;
import com.btt.pay.domain.enumeration.ErrorMessage;
import com.btt.pay.payload.request.RegisterRequest;
import com.btt.pay.payload.response.MessageResponse;
import com.btt.pay.repository.UserRepository;
import com.btt.pay.service.MetadataService;
import com.btt.pay.service.UserService;
import com.btt.pay.service.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final MetadataService metadataService;

    @Override
    public MessageResponse create(RegisterRequest request) {
        log.debug("USER SERVICE create: {}", request);
        MessageResponse response = new MessageResponse(ErrorMessage.INTERNAL_ERROR.getMessage());

        try {
            UserDTO newUserDTO = new UserDTO(request);

            MetadataDTO newMetadataDTO = metadataService.create();
            newUserDTO.setMetadata(newMetadataDTO);

            userRepository.save(userMapper.toEntity(newUserDTO));

            response.setMessage(ErrorMessage.REGISTER_SUCCESSFUL.getMessage());
        } catch (Exception e) {
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    public UserDTO retrieveById(Long id) {
        log.debug("USER SERVICE retrieve");

        Optional<User> userOptional = userRepository.findById(id);

        return userOptional.map(userMapper::toDTO).orElse(null);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        log.debug("USER SERVICE update");

        User user = userMapper.toEntity(userDTO);

        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public void incrementLoginAttempts(String username) {
        log.debug("USER SERVICE incrementLoginAttempt: {}", username);

        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (!user.isLocked()) {
                UserDTO userDTO = userMapper.toDTO(user);
                userDTO.setLoginAttempts(userDTO.getLoginAttempts() + 1);
                userDTO.setLocked(userDTO.getLoginAttempts() > 3);

                userRepository.save(userMapper.toEntity(userDTO));
            }
        }
    }

    @Override
    public String delete() {
        log.debug("USER SERVICE delete");

        return null;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("USER SERVICE loadUserByUsername");

        Optional<User> userOptional = userRepository.findByUsername(username);

        return userOptional.map(userMapper::toDTO).orElse(null);
    }

    @Override
    public boolean isUsernameUsed(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean isEmailUsed(String email) {
        return userRepository.existsByEmail(email);
    }

}
