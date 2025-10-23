package br.com.senac.api.controllers.dtos;

public class CarroRequestDTO {

    private String modelo;

    private Long marcaId;

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Long getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Long marcaId) {
        this.marcaId = marcaId;
    }
}
