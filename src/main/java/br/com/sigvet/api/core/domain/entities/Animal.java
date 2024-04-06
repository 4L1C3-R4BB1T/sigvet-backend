package br.com.sigvet.api.core.domain.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.sigvet.api.core.exception.DomainInvalidException;

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

    public Animal(Long id, String nome, String raca, LocalDate dataNascimento, LocalDateTime createdAt,
            LocalDateTime updatedAt, Cliente cliente) throws DomainInvalidException {
        this.id = id;
        this.nome = nome;
        this.raca = raca;
        this.dataNascimento = dataNascimento;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.cliente = cliente;
        this.validate();
    }

    public Animal(String nome, String raca, LocalDate dataNascimento, Cliente cliente) throws DomainInvalidException {
        this.nome = nome;
        this.raca = raca;
        this.dataNascimento = dataNascimento;
        this.cliente = cliente;
        this.validate();
    }

    public Animal(String nome, String raca, LocalDate dataNascimento) throws DomainInvalidException {
        this.nome = nome;
        this.raca = raca;
        this.dataNascimento = dataNascimento;
        this.validate();
    }

    public void validate() throws DomainInvalidException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new DomainInvalidException("O nome do animal não pode estar vazio.");
        }

        if (nome.length() > 255) {
            throw new DomainInvalidException("O nome do animal deve ter no máximo 255 caracteres.");
        }

        if (raca != null && raca.length() > 255) {
            throw new DomainInvalidException("A raça do animal deve ter no máximo 255 caracteres.");
        }

        if (dataNascimento != null && dataNascimento.isAfter(LocalDate.now())) {
            throw new DomainInvalidException("A data de nascimento do animal não pode ser no futuro.");
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
