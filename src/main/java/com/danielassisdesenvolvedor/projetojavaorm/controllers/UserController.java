package com.danielassisdesenvolvedor.projetojavaorm.controllers;

import com.danielassisdesenvolvedor.projetojavaorm.dto.UserDTO;
import com.danielassisdesenvolvedor.projetojavaorm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findByEmail(@RequestParam(name = "email", defaultValue = "") String email) {
        return ResponseEntity.ok(userService.findByEmail(email));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    @GetMapping(value = "/me")
    public ResponseEntity<UserDTO> getMe(){
        return ResponseEntity.ok(userService.getMe());
    }
}
