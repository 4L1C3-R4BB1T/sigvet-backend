CREATE TABLE ufs (
    sigla   CHAR(2)         NOT NULL,
    nome    VARCHAR(255)    NOT NULL,
    created_at   TIMESTAMP       NOT NULL DEFAULT NOW(),
    updated_at   TIMESTAMP       NOT NULL DEFAULT NOW(),
    CONSTRAINT pk_uf 
        PRIMARY KEY (sigla)
);

CREATE INDEX ufs_index_nome ON ufs (nome);