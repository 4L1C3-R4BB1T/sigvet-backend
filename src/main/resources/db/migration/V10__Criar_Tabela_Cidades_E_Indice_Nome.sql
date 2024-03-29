CREATE TABLE cidades (
    id          BIGSERIAL       NOT NULL,
    nome        VARCHAR(255)    NOT NULL,
    uf_sigla    CHAR(2)         NOT NULL,
    created_at   TIMESTAMP       NOT NULL DEFAULT NOW(),
    updated_at   TIMESTAMP       NOT NULL DEFAULT NOW(),
    CONSTRAINT pk_cidade
        PRIMARY KEY (id),
    CONSTRAINT fk_cidade_uf 
        FOREIGN KEY (uf_sigla) 
        REFERENCES ufs (sigla)
);

CREATE INDEX cidades_index_nome ON cidades (nome);