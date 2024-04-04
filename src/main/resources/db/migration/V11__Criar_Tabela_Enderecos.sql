CREATE TABLE enderecos (
    id          BIGSERIAL       NOT NULL,
    rua         VARCHAR(255)    NOT NULL,
    bairro      VARCHAR(255)    NOT NULL,
    cep         CHAR(8)         NOT NULL,
    numero      INTEGER         NULL,
    cidade_id   BIGINT          NOT NULL,
    usuario_id  BIGINT          NOT NULL,
    created_at   TIMESTAMP       NOT NULL DEFAULT NOW(),
    updated_at   TIMESTAMP       NOT NULL DEFAULT NOW(),
    deleted     BOOLEAN         DEFAULT  false,
    CONSTRAINT pk_endereco
        PRIMARY KEY (id),
    CONSTRAINT fk_endereco_cidade 
        FOREIGN KEY (cidade_id) 
        REFERENCES cidades (id),
    CONSTRAINT fk_endereco_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES usuarios (id)
);