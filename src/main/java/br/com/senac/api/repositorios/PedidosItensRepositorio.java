package br.com.senac.api.repositorios;

import br.com.senac.api.modelos.PedidosItens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidosItensRepositorio extends JpaRepository<PedidosItens, Long> {
}
