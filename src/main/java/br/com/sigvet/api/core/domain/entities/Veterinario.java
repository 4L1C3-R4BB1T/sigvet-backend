package br.com.sigvet.api.core.domain.entities;

import br.com.sigvet.api.core.exception.DomainInvalidException;

public class Veterinario extends Usuario {

    public String especialidade;
    public String crmv;
    public String crmvUf;

    public Veterinario() {
    }

    public Veterinario(Long id, String usuario, String senha, String email, String nome,
            Documento cpf, String telefone, Endereco endereco, String especialidade, String crmv,
            String crmvUf) throws DomainInvalidException {
        super(id, usuario, senha, email, nome, cpf, telefone, endereco);
        this.especialidade = especialidade;
        this.crmv = crmv;
        this.crmvUf = crmvUf;
        this.validate();
    }

    public Veterinario(String usuario, String senha, String email, String nome, Documento cpf,
            String telefone, Endereco endereco, String especialidade, String crmv, String crmvUf)
            throws DomainInvalidException {
        super(usuario, senha, email, nome, cpf, telefone, endereco);
        this.especialidade = especialidade;
        this.crmv = crmv;
        this.crmvUf = crmvUf;
        this.validate();
    }

    @Override
    public void validate() throws DomainInvalidException {
        super.validate();
        if (crmv == null || !crmv.matches("^[A-Z]{2}/\\d{4}$")) {
            throw new DomainInvalidException("O crmv não está no formato UF/XXXX");
        }
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
