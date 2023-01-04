package com.btt.pay.controller;

import com.btt.pay.domain.dto.UserDTO;
import com.btt.pay.domain.enumeration.ErrorMessage;
import com.btt.pay.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveUserById(@PathVariable Long id) {
        UserDTO userDTO = userService.retrieveById(id);

        return ResponseEntity.ok()
                .body(userDTO);
    }

//    @PutMapping
//    public ResponseEntity<?> updateUser() {
//        userService.update();
//
//        return ResponseEntity.ok()
//                .body(ErrorMessage.SUCCESSFUL.getMessage());
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser() {
        userService.delete();

        return ResponseEntity.ok()
                .body(ErrorMessage.SUCCESSFUL.getMessage());
    }
}
