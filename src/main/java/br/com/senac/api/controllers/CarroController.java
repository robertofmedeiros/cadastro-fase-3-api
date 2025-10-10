package br.com.senac.api.controllers;

import br.com.senac.api.controllers.dtos.CarroRequestDTO;
import br.com.senac.api.modelos.Carro;
import br.com.senac.api.services.CarroService;
import br.com.senac.api.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/carro")
@CrossOrigin
public class CarroController {

    @Autowired
    private CarroService carroService;

    @GetMapping("/listar")
    public ResponseEntity<List<Carro>> listarTodos() {
        return ResponseEntity.ok(carroService.listarTodos());
    }

    @PostMapping("/criar")
    public ResponseEntity<Carro> criar(@RequestBody CarroRequestDTO carro) {
        try {
            return ResponseEntity.ok(carroService.criar(carro));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Carro> atualizar(@PathVariable Long id,
                                           @RequestBody CarroRequestDTO carro) {
        try {
            return ResponseEntity.ok(carroService.atualizar(id, carro));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            carroService.deletar(id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.response(e.getMessage()));
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(carroService.listarById(id));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(ResponseUtil.response(e.getMessage()));
        }
    }
}
