package br.com.senac.api.services;

import br.com.senac.api.controllers.dtos.PedidosItensRequestDTO;
import br.com.senac.api.modelos.Pedidos;
import br.com.senac.api.modelos.PedidosItens;
import br.com.senac.api.repositorios.PedidosItensRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidosItensService {

    @Autowired
    private PedidosItensRepositorio pedidosItensRepositorio;

    private PedidosService pedidosService;

    public PedidosItensService(PedidosService pedidosService) {
        this.pedidosService = pedidosService;
    }

    public List<PedidosItens> listar() {
        return pedidosItensRepositorio.findAll();
    }

    public PedidosItens criar(PedidosItensRequestDTO item) {
        try {
            Pedidos pedidoResult = pedidosService.listarById(item.getPedidoId());
            PedidosItens itemPersist =
                    this.pedidosItensRequestDtoParaPedidosItens(item);
            itemPersist.setPedido(pedidoResult);

            return pedidosItensRepositorio.save(itemPersist);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private PedidosItens
        pedidosItensRequestDtoParaPedidosItens(PedidosItensRequestDTO entrada) {

        PedidosItens saida = new PedidosItens();
        saida.setQuantidade(entrada.getQuantidade());
        saida.setValorUnitario(entrada.getValorUnitario());

        return saida;
    }
}
