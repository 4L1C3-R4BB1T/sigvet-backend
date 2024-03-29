CREATE TABLE vacinas (
    id              BIGSERIAL       NOT NULL, 
    nome            VARCHAR(255)    NOT NULL,
    fabricante      VARCHAR(255)    NOT NULL,
    lote            VARCHAR(255)    NOT NULL,
    preco_unitario  DECIMAL(10, 2)  NOT NULL DEFAULT 0,
    estoque         INTEGER         NOT NULL DEFAULT 0,
    data_validade   TIMESTAMP       NOT NULL,
    created_at       TIMESTAMP       NOT NULL DEFAULT NOW(),
    updated_at       TIMESTAMP       NOT NULL DEFAULT NOW(),
    CONSTRAINT pk_vacina 
        PRIMARY KEY (id)
);