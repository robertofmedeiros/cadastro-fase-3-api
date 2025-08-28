package br.com.senac.api.repositorios;

import br.com.senac.api.modelos.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepositorio extends JpaRepository<Carro, Long> {
}
