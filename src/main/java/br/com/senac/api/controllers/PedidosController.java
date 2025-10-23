package br.com.senac.api.controllers;

import br.com.senac.api.controllers.dtos.PedidosRequestDTO;
import br.com.senac.api.modelos.Pedidos;
import br.com.senac.api.services.PedidosService;
import br.com.senac.api.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pedidos")
public class PedidosController {

    @Autowired
    private PedidosService pedidosService;



    @GetMapping("/listar")
    public ResponseEntity<List<Pedidos>> listar() {
        return ResponseEntity.ok(pedidosService.listar());
    }

    @PostMapping("/criar")
    public ResponseEntity<?>
            criar(@RequestBody PedidosRequestDTO pedido) {
        try {
            return ResponseEntity
                    .created(null)
                    .body(pedidosService.criar(pedido));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(ResponseUtil.response(e.getMessage()));
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @RequestBody PedidosRequestDTO pedido) {
        try {
          return ResponseEntity.ok(pedidosService.atualizar(id, pedido));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(ResponseUtil.response(e.getMessage()));
        }
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        try {
            pedidosService.excluir(id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(ResponseUtil.response(e.getMessage()));
        }
    }
}
