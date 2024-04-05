package br.com.sigvet.api.core.domain.entities;

import br.com.sigvet.api.core.exception.DomainInvalidException;

public class Veterinario extends Usuario {

    public String especialidade;
    public String crmv;
    public String crmvUf;

    public Veterinario() {
    }

    public Veterinario(String usuario, String senha, String email, String nome, Documento cpf, String telefone,
    String especialidade, String crmv, String crmvUf) throws DomainInvalidException {
        super(usuario, senha, email, nome, cpf, telefone);
        this.especialidade = especialidade;
        this.crmv = crmv;
        this.crmvUf = crmvUf;
        this.validate();
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

    public Veterinario(Long id, String usuario, String senha, String email, String nome,
            Documento cpf, String telefone, String especialidade, String crmv,
            String crmvUf) throws DomainInvalidException {
        super(id, usuario, senha, email, nome, cpf, telefone, null);
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

        if (crmv == null) {
            throw new DomainInvalidException("O CRMV não pode ser nulo");
        }
        
        if (!crmv.matches("^[a-zA-Z0-9]*$")) {
            throw new DomainInvalidException("O CRMV '" + crmv + "' contém caracteres especiais, apenas letras e números são permitidos");
        }
        
        if (crmv.length() > 45) {
            throw new DomainInvalidException("O CRMV '" + crmv + "' não pode ter mais que " + 45 + " caracteres");
        }
        
        if (crmvUf == null) {
            throw new DomainInvalidException("O CRMV UF não pode ser nulo");
        }
        
        if (!crmvUf.matches("[a-zA-Z]{2}")) {
            throw new DomainInvalidException("O CRMV UF '" + crmvUf + "' deve ter exatamente 2 letras");
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
