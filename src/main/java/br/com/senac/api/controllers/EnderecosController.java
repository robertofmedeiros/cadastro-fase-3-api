package br.com.senac.api.controllers;

import br.com.senac.api.controllers.dtos.EnderecosRequestDTO;
import br.com.senac.api.services.EnderecosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/enderecos")
public class EnderecosController {

    @Autowired
    private EnderecosService enderecosService;

    @GetMapping("/listar")
    public ResponseEntity<List<?>> listar() {
        return ResponseEntity.ok(enderecosService.listarTodos());
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criar(@RequestBody EnderecosRequestDTO endereco) {
        try {
            return ResponseEntity.ok(enderecosService.criar(endereco));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @RequestBody EnderecosRequestDTO endereco ) {
        try {
            return ResponseEntity.ok(enderecosService.atualizar(id, endereco));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            enderecosService.deletar(id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
