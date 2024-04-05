package br.com.sigvet.api.core.domain.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.sigvet.api.core.exception.DomainInvalidException;

public class Cliente extends Usuario {

    private List<Animal> animais = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(Long id, String usuario, String senha, String email, String nome, Documento cpf,
            String telefone, Endereco endereco) throws DomainInvalidException {
        super(id, usuario, senha, email, nome, cpf, telefone, endereco);
    }

    public Cliente(String usuario, String senha, String email, String nome, Documento cpf,
            String telefone, Endereco endereco) throws DomainInvalidException {
        super(usuario, senha, email, nome, cpf, telefone, endereco);
    }

    public Cliente(Long id, String usuario, String senha, String email, String nome, Documento cpf,
            String telefone, LocalDateTime createdAt, LocalDateTime updatedAt) throws DomainInvalidException {
        super(id, usuario, senha, email, nome, cpf, telefone, createdAt, updatedAt);
    }

    public List<Animal> getAnimais() {
        return animais;
    }

    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }

}
