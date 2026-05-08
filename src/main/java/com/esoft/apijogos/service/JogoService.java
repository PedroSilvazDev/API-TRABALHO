package com.esoft.apijogos.service;

import com.esoft.apijogos.dto.JogoRequest;
import com.esoft.apijogos.exception.NotFoundException;
import com.esoft.apijogos.model.Jogo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JogoService {
    
    private final Map<Integer, Jogo> jogosDb = new HashMap<>();
    private int nextId = 1;
    
    public JogoService() {
        jogosDb.put(1, new Jogo(1, "The Legend of Zelda", "Aventura", 10, "Um clássico absoluto."));
        jogosDb.put(2, new Jogo(2, "FIFA 23", "Esporte", 7, "Bom para jogar com amigos."));
        nextId = 3;
    }
    
    public List<Jogo> listarTodos() {
        return new ArrayList<>(jogosDb.values());
    }
    
    public Jogo buscarPorId(Integer id) {
        Jogo jogo = jogosDb.get(id);
        if (jogo == null) {
            throw new NotFoundException("Jogo não encontrado");
        }
        return jogo;
    }
    
    public Jogo criar(JogoRequest request) {
        Jogo novoJogo = new Jogo(
            nextId,
            request.getNome(),
            request.getTipo(),
            request.getNota(),
            request.getReview()
        );
        jogosDb.put(nextId, novoJogo);
        nextId++;
        return novoJogo;
    }
    
    public Jogo atualizar(Integer id, JogoRequest request) {
        if (!jogosDb.containsKey(id)) {
            throw new NotFoundException("Jogo não encontrado");
        }
        
        Jogo jogoAtualizado = new Jogo(
            id,
            request.getNome(),
            request.getTipo(),
            request.getNota(),
            request.getReview()
        );
        jogosDb.put(id, jogoAtualizado);
        return jogoAtualizado;
    }
    
    public void deletar(Integer id) {
        if (!jogosDb.containsKey(id)) {
            throw new NotFoundException("Jogo não encontrado");
        }
        jogosDb.remove(id);
    }
}
