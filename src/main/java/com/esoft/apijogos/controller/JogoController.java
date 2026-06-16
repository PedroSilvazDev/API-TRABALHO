package com.esoft.apijogos.controller;

import com.esoft.apijogos.dto.JogoRequest;
import com.esoft.apijogos.model.Jogo;
import com.esoft.apijogos.service.JogoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogos")
public class JogoController {

    private final JogoService jogoService;

    public JogoController(JogoService jogoService) {
        this.jogoService = jogoService;
    }

    @GetMapping
    public ResponseEntity<List<Jogo>> listarJogos() {
        return ResponseEntity.ok(jogoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogo> buscarJogo(@PathVariable Integer id) {
        return ResponseEntity.ok(jogoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Jogo> criarJogo(@Valid @RequestBody JogoRequest request) {
        Jogo novoJogo = jogoService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoJogo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jogo> atualizarJogo(@PathVariable Integer id, @Valid @RequestBody JogoRequest request) {
        return ResponseEntity.ok(jogoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarJogo(@PathVariable Integer id) {
        jogoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
