package com.esoft.apijogos.service;

import com.esoft.apijogos.exception.UnauthorizedException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class AuthService {
    
    private final Set<String> tokensValidos = new HashSet<>();
    
    public String login(String email, String password) {
        if ("usuario@esoft.com".equals(email) && "Abc123".equals(password)) {
            String token = UUID.randomUUID().toString();
            tokensValidos.add(token);
            return token;
        }
        throw new UnauthorizedException("Credenciais inválidas");
    }
    
    public void validarToken(String authorization) {
        if (authorization == null || authorization.isEmpty()) {
            throw new UnauthorizedException("Token não fornecido");
        }
        
        if (!authorization.startsWith("Bearer ")) {
            throw new UnauthorizedException("Formato de token inválido");
        }
        
        String token = authorization.substring(7);
        
        if (!tokensValidos.contains(token)) {
            throw new UnauthorizedException("Token inválido");
        }
    }
}
