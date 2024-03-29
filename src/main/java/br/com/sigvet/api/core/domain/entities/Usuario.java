package br.com.sigvet.api.core.domain.entities;

import java.time.LocalDateTime;

import br.com.sigvet.api.core.exception.DomainInvalidException;

public class Usuario {
    private Long id;
    private String usuario;
    private String senha;
    private String email;
    private String nome;
    private Documento cpf;
    private String telefone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Usuario() {
    }

    public Usuario(Long id, String usuario, String senha,
        String email, String nome, Documento cpf, String telefone) throws DomainInvalidException {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
        this.email = email;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.validate();
    }

    public Usuario(String usuario, String senha,
        String email, String nome, Documento cpf, String telefone) throws DomainInvalidException {
        this.usuario = usuario;
        this.senha = senha;
        this.email = email;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.validate();
    }

    public void validate() throws DomainInvalidException {
        if (usuario == null || usuario.trim().isEmpty()) {
            throw new DomainInvalidException("O nome de usuário não pode estar vazio.");
        }
        if (usuario.length() > 100) {
            throw new DomainInvalidException("O nome de usuário deve ter no máximo 100 caracteres.");
        }

        if (senha == null || senha.trim().isEmpty()) {
            throw new DomainInvalidException("A senha não pode estar vazia.");
        }

        if (senha.length() > 100) {
            throw new DomainInvalidException("A senha deve ter no máximo 100 caracteres.");
        }

        if (email == null || !email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
            throw new DomainInvalidException("O email deve ser um e-mail válido.");
        }

        if (nome == null || nome.trim().isEmpty()) {
            throw new DomainInvalidException("O nome não pode estar vazio.");
        }
        if (nome.length() > 100) {
            throw new DomainInvalidException("O nome deve ter no máximo 100 caracteres.");
        }

        if (telefone != null && telefone.length() > 18) {
            throw new DomainInvalidException("O telefone deve ter no máximo 18 caracteres.");
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Documento getCpf() {
        return cpf;
    }

    public void setCpf(Documento cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

    // public List<T> listarTotalFaturado(LocalDate dataInicio, 
    //    LocalDate dataFim) {
    // busca todas as consultas e aplicações de vacina que estejam 
    // dentro da dataInicio e dataFim informada

    // caso não seja informada data, será usado como parâmetro 
    // os últimos 30 dias

    // calcula o somatorio dos valores das consultas e aplicações

    // retorna os valores ordenados por ano e mês em ordem decrescente
    // }

    // public List<T> listarQuantidadeDeConsultas(LocalDate dataInicio, 
    //    LocalDate dataFim) {
    // busca todas as consultas que estejam dentro da dataInicio 
    // e dataFim informada

    // caso não seja informada data, será usado como parâmetro 
    // os últimos 30 dias

    // calcula-se o total de consultas realizadas ordenadas 
    // por cada veterinário

    // retorna os valores ordenados por ano e mês em ordem decrescente
    // }
}
