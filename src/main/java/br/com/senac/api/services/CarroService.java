package br.com.senac.api.services;

import br.com.senac.api.controllers.dtos.CarroRequestDTO;
import br.com.senac.api.modelos.Carro;
import br.com.senac.api.modelos.Marca;
import br.com.senac.api.repositorios.CarroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarroRepositorio carroRepositorio;

    private MarcaService marcaService;

    public CarroService(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    public List<Carro> listarTodos() {
        return carroRepositorio.findAll();
    }

    public Carro criar(CarroRequestDTO carro) {
        Marca marcaResult = marcaService.listarById(carro.getMarcaId());

        Carro carroPersist = new Carro();
        carroPersist.setMarca(marcaResult);
        carroPersist.setModelo(carro.getModelo());

        return carroRepositorio.save(carroPersist);
    }

    public Carro atualizar(Long id, CarroRequestDTO carro) throws Exception {
        if(carroRepositorio.existsById(id) == false) {
            throw new Exception("Registro não encontrado");
        }

        Marca marcaResult = marcaService.listarById(carro.getMarcaId());

        Carro carroPersist = new Carro();
        carroPersist.setModelo(carro.getModelo());
        carroPersist.setMarca(marcaResult);
        carroPersist.setId(id);

        return carroRepositorio.save(carroPersist);
    }

    public void deletar(Long id) {
        if(!carroRepositorio.existsById(id)) {
            throw new RuntimeException("Registro não encontrado");
        }

        carroRepositorio.deleteById(id);
    }

    public Carro listarById(Long id) {
        Optional<Carro> carroResult = carroRepositorio.findById(id);
        if (carroResult.isEmpty()) {
           throw new RuntimeException("Carro não encontrado!");
        }

        return carroResult.get();
    }
}
