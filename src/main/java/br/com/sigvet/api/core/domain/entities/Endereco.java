package br.com.sigvet.api.core.domain.entities;

import java.time.LocalDateTime;

import br.com.sigvet.api.core.exception.DomainInvalidException;

public class Endereco {
    private Long id;
    private String rua;
    private String bairro;
    private String cep;
    private Integer numero;
    private Cidade cidade;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    

    public Endereco() {
    }

    public Endereco(Long id, String rua, String bairro, String cep,
        Integer numero, Cidade cidade) throws DomainInvalidException {
        this.id = id;
        this.rua = rua;
        this.bairro = bairro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.validate();
    }

    public Endereco(String rua, String bairro, String cep,
        Integer numero, Cidade cidade) throws DomainInvalidException {
        this.rua = rua;
        this.bairro = bairro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.validate();
    }

    public void validate() throws DomainInvalidException {
        if (rua == null || rua.isEmpty()) {
            throw new DomainInvalidException("A rua não pode ser nula ou vazia.");
        }

        if (bairro == null || bairro.isEmpty()) {
            throw new DomainInvalidException("O bairro não pode ser nulo ou vazio.");
        }

        if (cep == null || cep.isEmpty()) {
            throw new DomainInvalidException("O CEP não pode ser nulo ou vazio.");
        }
        
        if (!cep.matches("^\\d{5}-?\\d{3}$")) {
            throw new DomainInvalidException("Formato de CEP inválido.");
        }

        if (numero != null && numero < 0) {
            throw new DomainInvalidException("O número do endereço não pode ser negativo.");
        }

        if (cidade == null) {
            throw new DomainInvalidException("A cidade não pode ser nula.");
        }

        cidade.validate(); 
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

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}