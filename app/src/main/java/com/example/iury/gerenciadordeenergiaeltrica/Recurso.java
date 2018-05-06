package com.example.iury.gerenciadordeenergiaeltrica;

import java.io.Serializable;

public class Recurso implements Serializable{

    private String descricao;
    private Double voltagem;
    private Double potenciaUso;
    private Double potenciaStand;
    private String foto;

    private Long id;

    @Override
    public String toString() {
        return "(" + id +")" + voltagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getVoltagem() {
        return voltagem;
    }

    public Double getPotenciaUso() {
        return potenciaUso;
    }

    public Double getPotenciaStand() {
        return potenciaStand;
    }

    public void setVoltagem(Double voltagem) {
        this.voltagem = voltagem;
    }

    public void setPotenciaUso(Double potenciaUso) {
        this.potenciaUso = potenciaUso;
    }

    public void setPotenciaStand(Double potenciaStand) {
        this.potenciaStand = potenciaStand;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
