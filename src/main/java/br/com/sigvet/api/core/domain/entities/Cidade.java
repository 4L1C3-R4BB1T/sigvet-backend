package br.com.sigvet.api.core.domain.entities;

import java.time.LocalDateTime;

import br.com.sigvet.api.core.exception.DomainInvalidException;

public class Cidade {
    private Long id;
    private String nome;
    private UF uf;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Cidade() {
    }

    public Cidade(Long id, String nome, UF uf) {
        this.id = id;
        this.nome = nome;
        this.uf = uf;
    }

    public void validate() throws DomainInvalidException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new DomainInvalidException("O nome da cidade não pode estar vazio.");
        }
        if (nome.length() > 255) {
            throw new DomainInvalidException("O nome da cidade deve ter no máximo 255 caracteres.");
        }

        if (uf == null) {
            throw new DomainInvalidException("O UF da cidade não pode ser nulo.");
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
    }
}
