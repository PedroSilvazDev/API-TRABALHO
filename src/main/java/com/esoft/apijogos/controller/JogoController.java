package com.esoft.apijogos.controller;

import com.esoft.apijogos.dto.JogoRequest;
import com.esoft.apijogos.model.Jogo;
import com.esoft.apijogos.service.AuthService;
import com.esoft.apijogos.service.JogoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogos")
public class JogoController {
    
    private final JogoService jogoService;
    private final AuthService authService;
    
    public JogoController(JogoService jogoService, AuthService authService) {
        this.jogoService = jogoService;
        this.authService = authService;
    }
    
    @GetMapping
    public ResponseEntity<List<Jogo>> listarJogos(
            @RequestHeader(value = "Authorization", required = false) String authorization) {
        authService.validarToken(authorization);
        return ResponseEntity.ok(jogoService.listarTodos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Jogo> buscarJogo(@PathVariable Integer id,
            @RequestHeader(value = "Authorization", required = false) String authorization) {
        authService.validarToken(authorization);
        return ResponseEntity.ok(jogoService.buscarPorId(id));
    }
    
    @PostMapping
    public ResponseEntity<Jogo> criarJogo(@RequestBody JogoRequest request,
            @RequestHeader(value = "Authorization", required = false) String authorization) {
        authService.validarToken(authorization);
        Jogo novoJogo = jogoService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoJogo);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Jogo> atualizarJogo(@PathVariable Integer id, @RequestBody JogoRequest request,
            @RequestHeader(value = "Authorization", required = false) String authorization) {
        authService.validarToken(authorization);
        return ResponseEntity.ok(jogoService.atualizar(id, request));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarJogo(@PathVariable Integer id,
            @RequestHeader(value = "Authorization", required = false) String authorization) {
        authService.validarToken(authorization);
        jogoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
