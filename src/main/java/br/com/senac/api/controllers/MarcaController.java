package br.com.senac.api.controllers;

import br.com.senac.api.controllers.dtos.CarroRequestDTO;
import br.com.senac.api.controllers.dtos.MarcaRequestDTO;
import br.com.senac.api.modelos.Carro;
import br.com.senac.api.modelos.Marca;
import br.com.senac.api.services.CarroService;
import br.com.senac.api.services.MarcaService;
import br.com.senac.api.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/marcas")
@CrossOrigin
public class MarcaController {
    @Autowired
    private MarcaService marcaService;

    @GetMapping("/listar")
    public ResponseEntity<List<Marca>> listarTodos() {
        return ResponseEntity.ok(marcaService.listarTodos());
    }

    @PostMapping("/criar")
    public ResponseEntity<Marca> criar(@RequestBody MarcaRequestDTO marca) {
        try {
            return ResponseEntity.ok(marcaService.criar(marca));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Marca> atualizar(@PathVariable Long id,
                                           @RequestBody MarcaRequestDTO marca) {
        try {
            return ResponseEntity.ok(marcaService.atualizar(id, marca));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            marcaService.deletar(id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.response(e.getMessage()));
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(marcaService.listarById(id));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(ResponseUtil.response(e.getMessage()));
        }
    }
}
