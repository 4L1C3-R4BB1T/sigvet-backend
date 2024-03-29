package br.com.sigvet.api.core.domain.entities;

import java.time.LocalDateTime;

import br.com.sigvet.api.core.exception.DomainInvalidException;

public class UF {
    private Long id;
    private String sigla;
    private String nome;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UF() {
    }

    public UF(Long id, String sigla, String nome) throws DomainInvalidException {
        this.id = id;
        this.sigla = sigla;
        this.nome = nome;
        this.validate();
    }

    public UF(String sigla, String nome) throws DomainInvalidException {
        this.sigla = sigla;
        this.nome = nome;
        this.validate();
    }

    public void validate() throws DomainInvalidException {
        if (sigla == null || sigla.isEmpty()) {
            throw new DomainInvalidException("A sigla da UF não pode ser nula ou vazia.");
        }

        if (sigla.length() != 2) {
            throw new DomainInvalidException("A sigla da UF deve conter exatamente 2 caracteres.");
        }
        
        if (nome == null || nome.isEmpty()) {
            throw new DomainInvalidException("O nome da UF não pode ser nulo ou vazio.");
        }

        if (nome.length() > 255) {
            throw new DomainInvalidException("O nome da UF não pode exceder 255 caracteres.");
        }
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

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
