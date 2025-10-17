package br.com.senac.api.services;

import br.com.senac.api.controllers.dtos.PedidosItensRequestDTO;
import br.com.senac.api.modelos.Pedidos;
import br.com.senac.api.modelos.PedidosItens;
import br.com.senac.api.modelos.Produto;
import br.com.senac.api.repositorios.PedidosItensRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidosItensService {

    @Autowired
    private PedidosItensRepositorio pedidosItensRepositorio;

    private PedidosService pedidosService;

    private ProdutoService produtoService;

    public PedidosItensService(
            PedidosService pedidosService,
            ProdutoService produtoService) {
        this.pedidosService = pedidosService;
        this.produtoService = produtoService;
    }

    public List<PedidosItens> listar() {
        return pedidosItensRepositorio.findAll();
    }

    public PedidosItens criar(PedidosItensRequestDTO item) {
        try {
            Pedidos pedidoResult = pedidosService.listarById(item.getPedidoId());
            Produto produtoResult = produtoService.listarById(item.getProdutoId());
            PedidosItens itemPersist =
                    this.pedidosItensRequestDtoParaPedidosItens(item);
            itemPersist.setPedido(pedidoResult);
            itemPersist.setProduto(produtoResult);

            return pedidosItensRepositorio.save(itemPersist);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public PedidosItens atualizar(Long id, PedidosItensRequestDTO item) {
        Optional<PedidosItens> itemResult =
                pedidosItensRepositorio.findById(id);
        if(itemResult.isEmpty()) {
            throw new RuntimeException("Item não encontrado!");
        }

        Produto produtoResult = produtoService.listarById(item.getProdutoId());

        PedidosItens itemPersist =
                this.pedidosItensRequestDtoParaPedidosItens(item);
        itemPersist.setPedido(itemResult.get().getPedido());
        itemPersist.setId(id);
        itemPersist.setProduto(produtoResult);

        return pedidosItensRepositorio.save(itemPersist);

    }

    public void deletar(Long id) {
        if(!pedidosItensRepositorio.existsById(id)) {
            throw new RuntimeException("Item não encontrado!");
        }

        pedidosItensRepositorio.deleteById(id);
    }

    private PedidosItens
        pedidosItensRequestDtoParaPedidosItens(PedidosItensRequestDTO entrada) {

        PedidosItens saida = new PedidosItens();
        saida.setQuantidade(entrada.getQuantidade());
        saida.setValorUnitario(entrada.getValorUnitario());

        return saida;
    }
}
