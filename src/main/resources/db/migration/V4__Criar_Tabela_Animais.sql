CREATE TABLE animais (
    id              BIGSERIAL       NOT NULL, 
    nome            VARCHAR(255)    NOT NULL,
    raca            VARCHAR(255)    NULL,
    data_nascimento DATE       NULL,
    cliente_id      BIGINT          NOT NULL,
    created_at      TIMESTAMP       NOT NULL DEFAULT NOW(),
    updated_at      TIMESTAMP       NOT NULL DEFAULT NOW(),
    deleted     BOOLEAN         DEFAULT  false,
    CONSTRAINT pk_animal 
        PRIMARY KEY (id),
    CONSTRAINT fk_animal_cliente 
        FOREIGN KEY (cliente_id) 
        REFERENCES clientes (id)
);
