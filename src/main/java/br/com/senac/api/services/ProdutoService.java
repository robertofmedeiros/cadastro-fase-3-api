package br.com.senac.api.services;

import br.com.senac.api.controllers.dtos.ProdutoRequestDTO;
import br.com.senac.api.modelos.Produto;
import br.com.senac.api.repositorios.ProdutoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    public List<Produto> listarTodos() {
        return produtoRepositorio.findAll();
    }

    public Produto criar(ProdutoRequestDTO produto) {
        Produto produtoPersist = this.produtoRequestDtoParaProduto(produto);

        return produtoRepositorio.save(produtoPersist);

    }

    public Produto atualizar (Long id, ProdutoRequestDTO produto) {
        if(!produtoRepositorio.existsById(id)) {
            throw new RuntimeException("Registro não encontrado!");
        }

        Produto produtoPersist = this.produtoRequestDtoParaProduto(produto);

        produtoPersist.setId(id);

        return produtoRepositorio.save(produtoPersist);
    }

    public void deletar (Long id) {
        if(!produtoRepositorio.existsById(id)) {
            throw new RuntimeException("Registro não encontrado!");
        }

        produtoRepositorio.deleteById(id);
    }

    public Produto listarById(Long id) {
        Optional<Produto> produtoResult = produtoRepositorio.findById(id);
        if (produtoResult.isEmpty()) {
            throw new RuntimeException("Produto não encontrado!");
        }

        return produtoResult.get();
    }

    private Produto produtoRequestDtoParaProduto(ProdutoRequestDTO in) {
        Produto out = new Produto();
        out.setNome(in.getNome());
        out.setDescricao(in.getDescricao());

        return out;
    }
}
