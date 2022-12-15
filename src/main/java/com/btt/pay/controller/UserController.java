package com.btt.pay.controller;

import com.btt.pay.domain.dto.UserDTO;
import com.btt.pay.domain.enumeration.ErrorMessage;
import com.btt.pay.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> createUser() {
        log.debug("Creating User");

        userService.create();

        return ResponseEntity.ok()
                .body(ErrorMessage.SUCCESSFUL.getMessage());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> retrieveUser() {
        log.debug("Retrieving User");

        userService.retrieve();

        return ResponseEntity.ok()
                .body(new UserDTO());
    }

    @PutMapping
    public ResponseEntity<String> updateUser() {
        log.debug("Updating User");

        userService.update();

        return ResponseEntity.ok()
                .body(ErrorMessage.SUCCESSFUL.getMessage());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser() {
        log.debug("Deleting User");

        userService.delete();

        return ResponseEntity.ok()
                .body(ErrorMessage.SUCCESSFUL.getMessage());
    }
}
