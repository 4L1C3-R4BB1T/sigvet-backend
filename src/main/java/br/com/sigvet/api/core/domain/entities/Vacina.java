package br.com.sigvet.api.core.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.sigvet.api.core.exception.DomainInvalidException;

public class Vacina {

    private Long id;
    private String nome;
    private String fabricante;
    private String lote;
    private BigDecimal precoUnitario;
    private Integer estoque;
    private LocalDate dataValidade;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Vacina() {
    }

    public Vacina(Long id, String nome, String fabricante, String lote, BigDecimal precoUnitario,
            Integer estoque, LocalDate dataValidade) throws DomainInvalidException {
        this.id = id;
        this.nome = nome;
        this.fabricante = fabricante;
        this.lote = lote;
        this.precoUnitario = precoUnitario;
        this.estoque = estoque;
        this.dataValidade = dataValidade;
        this.validate();
    }

    public Vacina(String nome, String fabricante, BigDecimal precoUnitario,
            Integer estoque, LocalDate dataValidade) throws DomainInvalidException {
        this.nome = nome;
        this.fabricante = fabricante;
        this.precoUnitario = precoUnitario;
        this.estoque = estoque;
        this.dataValidade = dataValidade;
        this.validate();
    }

    public void validate() throws DomainInvalidException {
        if (nome == null || nome.isEmpty()) {
            throw new DomainInvalidException("O nome da vacina não pode ser vazio.");
        }
        if (fabricante == null || fabricante.isEmpty()) {
            throw new DomainInvalidException("O fabricante da vacina não pode ser vazio.");
        }
        if (lote == null || lote.isEmpty()) {
            throw new DomainInvalidException("O lote da vacina não pode ser vazio.");
        }
        if (precoUnitario.compareTo(BigDecimal.ZERO) < 0) {
            throw new DomainInvalidException("O preço unitário da vacina deve ser maior que zero.");
        }
        if (estoque <= 0) {
            throw new DomainInvalidException("O estoque da vacina deve ser um número inteiro positivo.");
        }
        if (dataValidade.isBefore(LocalDate.now())) {
            throw new DomainInvalidException("A data de validade da vacina deve ser no futuro.");
        }
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

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
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
