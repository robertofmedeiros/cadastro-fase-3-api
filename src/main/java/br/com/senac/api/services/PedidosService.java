package br.com.senac.api.services;

import br.com.senac.api.controllers.dtos.PedidosRequestDTO;
import br.com.senac.api.modelos.Clientes;
import br.com.senac.api.modelos.Enderecos;
import br.com.senac.api.modelos.Pedidos;
import br.com.senac.api.repositorios.PedidosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PedidosService {

    @Autowired
    private PedidosRepositorio pedidosRepositorio;

    private ClientesService clientesService;

    public PedidosService(ClientesService clientesService) {
        this.clientesService = clientesService;
    }

    public List<Pedidos> listar() {
        return pedidosRepositorio.findAll();
    }

    public Pedidos criar(PedidosRequestDTO pedido) {
        /*
        Pedidos pedidoPersist = this.pedidoRequestDtoParaPedido(pedido);

        return pedidosRepositorio.save(pedidoPersist);
         */

        return pedidosRepositorio
                .save(this.pedidoRequestDtoParaPedido(pedido));
    }

    public Pedidos atualizar(Long id, PedidosRequestDTO pedido) {
        if(pedidosRepositorio.existsById(id) == false) {
            throw new RuntimeException("Pedido não encontrado!");
        }

        Pedidos pedidoPersist = this.pedidoRequestDtoParaPedido(pedido);
        pedidoPersist.setId(id);

        return pedidosRepositorio.save(pedidoPersist);
    }

    public void excluir(Long id) {
        if(pedidosRepositorio.existsById(id) == false) {
            throw new RuntimeException("Pedido não encontrado");
        }

        pedidosRepositorio.deleteById(id);
    }

    public Pedidos listarById(Long id) {
        Optional<Pedidos> pedidoResult = pedidosRepositorio.findById(id);
        if(pedidoResult.isEmpty()) {
            throw new RuntimeException("Pedido não encontrado");
        }

        return pedidoResult.get();
    }

    private Pedidos pedidoRequestDtoParaPedido(PedidosRequestDTO entrada) {
        Pedidos saida = new Pedidos();
        saida.setDataCriacao(LocalDateTime.now());
        saida.setDataEntrega(entrada.getDataEntrega());

        Clientes clienteResult = clientesService.listarById(entrada.getClienteId());

        Optional<Enderecos> enderecoResult = clienteResult
                .getEnderecos()
                .stream()
                .filter(item -> item.getId() == entrada.getEnderecoEntregaId())
                .findFirst();
        if (enderecoResult.isEmpty()) {
            throw new RuntimeException("Endereco não encontrado!");
        }

        saida.setEnderecoEntrega(enderecoResult.get());
        saida.setCliente(clienteResult);

        return saida;

    }
}
