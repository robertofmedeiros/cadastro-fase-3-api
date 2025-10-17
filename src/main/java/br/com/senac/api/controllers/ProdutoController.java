package br.com.senac.api.controllers;

import br.com.senac.api.controllers.dtos.PessoaRequestDTO;
import br.com.senac.api.controllers.dtos.ProdutoRequestDTO;
import br.com.senac.api.modelos.Pessoa;
import br.com.senac.api.modelos.Produto;
import br.com.senac.api.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/listar")
    public ResponseEntity<List<Produto>> listar() {
        return ResponseEntity.ok(produtoService.listarTodos());
    }

    @PostMapping("/criar")
    public ResponseEntity<Produto> criar(@RequestBody ProdutoRequestDTO produto) {
        try {
            return ResponseEntity.ok(produtoService.criar(produto));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Produto> atualizar(
            @PathVariable Long id,
            @RequestBody ProdutoRequestDTO produto ) {
        try {
            return ResponseEntity.ok(produtoService.atualizar(id, produto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            produtoService.deletar(id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
