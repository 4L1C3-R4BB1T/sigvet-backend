package br.com.sigvet.api.core.domain;

public class Cidade {
    private Long id;
    private String nome;
    private UF uf;

    public Cidade() {
    }

    public Cidade(Long id, String nome, UF uf) {
        this.id = id;
        this.nome = nome;
        this.uf = uf;
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

    public UF getUF() {
        return uf;
    }

    public void setUF(UF uf) {
        this.uf = uf;
    }
}
