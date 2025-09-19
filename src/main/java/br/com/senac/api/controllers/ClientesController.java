package br.com.senac.api.controllers;

import br.com.senac.api.controllers.dtos.ClientesRequestDTO;
import br.com.senac.api.modelos.Clientes;
import br.com.senac.api.services.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
@CrossOrigin
public class ClientesController {

    @Autowired
    private ClientesService clientesService;

    @PostMapping("/criar")
    public ResponseEntity<Void> cadastrarCliente(@RequestBody ClientesRequestDTO cliente) {
        System.out.println(cliente.toString());
        clientesService.criar(cliente);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Clientes>> listarTodos() {
        return ResponseEntity.ok(clientesService.listarTodos());
    }
}
