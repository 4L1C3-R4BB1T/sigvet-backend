package br.com.sigvet.api.core.domain;

public class UF {
    private String sigla;
    private String nome;

    public UF() {
    }

    public UF(String sigla, String nome) {
        this.sigla = sigla;
        this.nome = nome;
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
