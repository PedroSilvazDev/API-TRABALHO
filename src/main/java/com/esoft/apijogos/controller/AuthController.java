package com.esoft.apijogos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping
public class AuthController {

    @GetMapping("/")
    public Map<String, String> raiz() {
        return Map.of(
                "status", "ok",
                "mensagem", "API Biblioteca de Jogos",
                "jogos", "GET/POST/PUT/DELETE /jogos",
                "teste", "GET /login-test"
        );
    }

    @GetMapping("/login-test")
    public String teste() {
        return "API funcionando";
    }
}
