package br.com.senac.api.services;

import br.com.senac.api.controllers.dtos.EnderecosRequestDTO;
import br.com.senac.api.modelos.Enderecos;
import br.com.senac.api.repositorios.EnderecosRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecosService {

    private EnderecosRepositorio enderecosRepositorio;

    public List<Enderecos> listarTodos() {
        return enderecosRepositorio.findAll();
    }

    public Enderecos criar(EnderecosRequestDTO endereco) {
        Enderecos enderecoPersist = new Enderecos();
        enderecoPersist.setCep(endereco.getCep());
        enderecoPersist.setBairro(endereco.getBairro());
        enderecoPersist.setCidade(endereco.getCidade());
        enderecoPersist.setNumero(enderecoPersist.getNumero());
        enderecoPersist.setRua(enderecoPersist.getRua());
        enderecoPersist.setComplemento(enderecoPersist.getComplemento());
        enderecoPersist.setUf(endereco.getUf());

        return enderecosRepositorio.save(enderecoPersist);
    }

    public Enderecos atualizar(Long id, EnderecosRequestDTO endereco) throws Exception {
        if(enderecosRepositorio.existsById(id) == false) {
            throw new Exception("Registro não encontrado");
        }

        Enderecos enderecoPersist = new Enderecos();
        enderecoPersist.setCep(endereco.getCep());
        enderecoPersist.setBairro(endereco.getBairro());
        enderecoPersist.setCidade(endereco.getCidade());
        enderecoPersist.setNumero(enderecoPersist.getNumero());
        enderecoPersist.setRua(enderecoPersist.getRua());
        enderecoPersist.setComplemento(enderecoPersist.getComplemento());
        enderecoPersist.setUf(endereco.getUf());
        enderecoPersist.setId(id);

        return enderecosRepositorio.save(enderecoPersist);
    }

    public void deletar(Long id) {
        if(!enderecosRepositorio.existsById(id)) {
            throw new RuntimeException("Registro não encontrado");
        }

        enderecosRepositorio.deleteById(id);
    }
}
