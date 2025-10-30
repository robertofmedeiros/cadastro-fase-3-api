package br.com.senac.api.services;

import br.com.senac.api.controllers.dtos.MarcaRequestDTO;
import br.com.senac.api.modelos.Marca;
import br.com.senac.api.repositorios.MarcaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {
    @Autowired
    private MarcaRepositorio marcaRepositorio;

    public List<Marca> listarTodos() {
        return marcaRepositorio.findAll();
    }

    public Marca criar(MarcaRequestDTO marca) {

        return marcaRepositorio.save(this.marcaRequestDTOParaMarca(marca));
    }

    public Marca atualizar(Long id, MarcaRequestDTO marca) throws Exception {
        if(marcaRepositorio.existsById(id) == false) {
            throw new Exception("Registro não encontrado");
        }

        Marca marcaPersist = this.marcaRequestDTOParaMarca(marca);
        marcaPersist.setId(id);

        return marcaRepositorio.save(marcaPersist);
    }

    public void deletar(Long id) {
        if(!marcaRepositorio.existsById(id)) {
            throw new RuntimeException("Registro não encontrado");
        }

        marcaRepositorio.deleteById(id);
    }

    public Marca listarById(Long id) {
        Optional<Marca> marcaResult = marcaRepositorio.findById(id);
        if (marcaResult.isEmpty()) {
            throw new RuntimeException("Carro não encontrado!");
        }

        return marcaResult.get();
    }

    private Marca marcaRequestDTOParaMarca(MarcaRequestDTO entrada) {
        Marca saida = new Marca();
        saida.setNome(entrada.getNome());
        saida.setDescricao(entrada.getDescricao());

        return saida;
    }
}
