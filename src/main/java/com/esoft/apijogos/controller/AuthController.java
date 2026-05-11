package com.esoft.apijogos.controller;

import com.esoft.apijogos.dto.LoginRequest;
import com.esoft.apijogos.dto.LoginResponse;
import com.esoft.apijogos.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class AuthController {
    
    private final AuthService authService;
    
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /** Raiz — evita 404 ao abrir a URL da API no browser ou no health check genérico */
    @GetMapping("/")
    public Map<String, String> raiz() {
        return Map.of(
                "status", "ok",
                "mensagem", "API Biblioteca de Jogos",
                "login", "POST /login",
                "jogos", "GET /jogos (header Authorization: Bearer <token>)",
                "teste", "GET /login-test"
        );
    }

    /**
     * No browser o endereço /login é aberto em GET; o login real é só POST.
     * Resposta em JSON para não mostrar a Whitelabel page.
     */
    @GetMapping("/login")
    public ResponseEntity<Map<String, String>> loginSomentePost() {
        Map<String, String> body = new HashMap<>();
        body.put("detail",
                "Este URL só aceita POST (não abrir direto no browser). "
                        + "Use POST com Content-Type: application/json e corpo "
                        + "{\"email\":\"usuario@esoft.com\",\"password\":\"Abc123\"}.");
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(body);
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
