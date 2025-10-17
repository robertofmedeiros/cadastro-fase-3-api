package br.com.senac.api.modelos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Pedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    @Column
    private LocalDateTime dataEntrega;

    @OneToMany(mappedBy = "pedido")
    @JsonManagedReference
    private List<PedidosItens> itens;

    @ManyToOne(optional = false)
    private Clientes cliente;

    @ManyToOne(optional = false)
    private Enderecos enderecoEntrega;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDateTime dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public List<PedidosItens> getItens() {
        return itens;
    }

    public void setItens(List<PedidosItens> itens) {
        this.itens = itens;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Enderecos getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(Enderecos enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }
}
