CREATE TABLE usuarios (
    id          BIGSERIAL       NOT NULL,
    usuario     VARCHAR(100)    NOT NULL UNIQUE,
    senha       VARCHAR(100)    NOT NULL,
    email       VARCHAR(100)    NOT NULL UNIQUE,
    nome        VARCHAR(100)    NOT NULL,
    cpf         CHAR(14)        NOT NULL UNIQUE,
    telefone    VARCHAR(18)     NULL,
    created_at  TIMESTAMP       NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP       NOT NULL DEFAULT NOW(),
    CONSTRAINT pk_usuario 
        PRIMARY KEY (id)
);

CREATE INDEX usuarios_index_usuario ON usuarios (usuario);
CREATE INDEX usuarios_index_cpf ON usuarios (cpf);
CREATE INDEX usuarios_index_email ON usuarios (email);