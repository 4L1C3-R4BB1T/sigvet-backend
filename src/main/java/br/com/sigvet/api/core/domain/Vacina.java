package br.com.sigvet.api.core.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Vacina {
    private Long id;
    private String nome;
    private String fabricante;
    private String lote;
    private Double precoUnitario;
    private Integer estoque;
    private LocalDate dataValidade;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Vacina() {
    }

    public Vacina(Long id, String nome, String fabricante, String lote,
        Double precoUnitario, Integer estoque, LocalDate dataValidade,
        LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.nome = nome;
        this.fabricante = fabricante;
        this.lote = lote;
        this.precoUnitario = precoUnitario;
        this.estoque = estoque;
        this.dataValidade = dataValidade;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
