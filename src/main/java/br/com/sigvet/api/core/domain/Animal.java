package br.com.sigvet.api.core.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Animal {
    private Long id;
    private String nome;
    private String raca;
    private LocalDate dataNascimento;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Cliente cliente;

    public Animal() {
    }

    public Animal(Long id, String nome, String raca,
        LocalDate dataNascimento, LocalDateTime createdAt,
        LocalDateTime updatedAt, Cliente cliente) {
        this.id = id;
        this.nome = nome;
        this.raca = raca;
        this.dataNascimento = dataNascimento;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.cliente = cliente;
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

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
