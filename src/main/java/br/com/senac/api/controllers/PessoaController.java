package br.com.senac.api.controllers;

import br.com.senac.api.controllers.dtos.PessoaRequestDTO;
import br.com.senac.api.modelos.Pessoa;
import br.com.senac.api.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/listar")
    public ResponseEntity<List<Pessoa>> listar() {
        return ResponseEntity.ok(pessoaService.listarTodos());
    }

    @PostMapping("/criar")
    public ResponseEntity<Pessoa> criar(@RequestBody PessoaRequestDTO pessoa) {
        try {
            return ResponseEntity.ok(pessoaService.criar(pessoa));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Pessoa> atualizar(
            @PathVariable Long id,
            @RequestBody PessoaRequestDTO pessoa ) {
       try {
           return ResponseEntity.ok(pessoaService.atualizar(id, pessoa));
       } catch (Exception e) {
           return ResponseEntity.badRequest().body(null);
       }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            pessoaService.deletar(id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
