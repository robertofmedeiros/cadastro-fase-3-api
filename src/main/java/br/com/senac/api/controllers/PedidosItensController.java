package br.com.senac.api.controllers;

import br.com.senac.api.controllers.dtos.PedidosItensRequestDTO;
import br.com.senac.api.modelos.PedidosItens;
import br.com.senac.api.services.PedidosItensService;
import br.com.senac.api.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pedidos_itens")
public class PedidosItensController {

    @Autowired
    private PedidosItensService pedidosItensService;

    @GetMapping("/listar")
    public ResponseEntity<List<PedidosItens>> listar() {
        return ResponseEntity.ok(pedidosItensService.listar());
    }

    @PostMapping("/criar")
    public ResponseEntity<?>
        criar(@RequestBody PedidosItensRequestDTO item) {
        try {
            return ResponseEntity
                    .created(null)
                    .body(pedidosItensService.criar(item));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(ResponseUtil.response(e.getMessage()));
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @RequestBody PedidosItensRequestDTO item) {
        try {
            return ResponseEntity
                    .ok(pedidosItensService.atualizar(id, item));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(ResponseUtil.response(e.getMessage()));
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            pedidosItensService.deletar(id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(ResponseUtil.response(e.getMessage()));
        }
    }
}
