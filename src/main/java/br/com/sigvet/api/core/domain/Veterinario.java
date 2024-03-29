package br.com.sigvet.api.core.domain;

import java.time.LocalDateTime;

public class Veterinario extends Usuario {
    public String especialidade;
    public String crmv;
    public String crmvUf;

    public Veterinario() {
    }

    public Veterinario(Long id, String usuario, String senha,
        String email, String nome, Documento cpf, String telefone,
        LocalDateTime createdAt, LocalDateTime updatedAt,
        String especialidade, String crmv, String crmvUF) {
        super(id, usuario, senha, email, nome, cpf, telefone,
            createdAt, updatedAt);
        this.especialidade = especialidade;
        this.crmv = crmv;
        this.crmvUf = crmvUF;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getCrmv() {
        return crmv;
    }

    public void setCrmv(String crmv) {
        this.crmv = crmv;
    }

    public String getCrmvUf() {
        return crmvUf;
    }

    public void setCrmvUf(String crmvUf) {
        this.crmvUf = crmvUf;
    }
}
