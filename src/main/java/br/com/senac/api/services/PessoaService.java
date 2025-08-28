package br.com.senac.api.services;

import br.com.senac.api.controllers.dtos.PessoaRequestDTO;
import br.com.senac.api.modelos.Pessoa;
import br.com.senac.api.repositorios.PessoaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepositorio pessoaRepositorio;

    public List<Pessoa> listarTodos() {
        return pessoaRepositorio.findAll();
    }

    public Pessoa criar(PessoaRequestDTO pessoa) {
        Pessoa pessoaPersist = this.pessoaRequestDtoParaPessoa(pessoa);

        return pessoaRepositorio.save(pessoaPersist);
    }

    public Pessoa atualizar(Long id, PessoaRequestDTO pessoa) {
        if(!pessoaRepositorio.existsById(id)) {
            throw new RuntimeException("Registro não encontrado!");
        }

        Pessoa pessoaPersist = this.pessoaRequestDtoParaPessoa(pessoa);

        pessoaPersist.setId(id);

        return pessoaRepositorio.save(pessoaPersist);
    }

    public void deletar(Long id) throws RuntimeException{
        if(!pessoaRepositorio.existsById(id)) {
            throw new RuntimeException("Registro não encontrado");
        }

        pessoaRepositorio.deleteById(id);
    }

    private Pessoa pessoaRequestDtoParaPessoa(PessoaRequestDTO in){
        Pessoa out = new Pessoa();
        out.setNome(in.getNome());
        out.setSobrenome(in.getSobrenome());

        return out;
    }
}
