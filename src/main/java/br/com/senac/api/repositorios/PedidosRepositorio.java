package br.com.senac.api.repositorios;

import br.com.senac.api.modelos.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidosRepositorio extends JpaRepository<Pedidos, Long> {
}
