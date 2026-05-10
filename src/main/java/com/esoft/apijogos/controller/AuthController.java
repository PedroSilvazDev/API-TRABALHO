package com.esoft.apijogos.controller;

import com.esoft.apijogos.dto.LoginRequest;
import com.esoft.apijogos.dto.LoginResponse;
import com.esoft.apijogos.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AuthController {
    
    private final AuthService authService;
    
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        String token = authService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @GetMapping("/login-test")
public String teste() {
    return "API funcionando";
}
}
