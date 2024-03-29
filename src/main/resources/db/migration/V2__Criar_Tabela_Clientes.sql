CREATE TABLE clientes (
    id  BIGSERIAL   NOT NULL,
    CONSTRAINT pk_cliente
        PRIMARY KEY (id),
    CONSTRAINT fk_cliente_usuario 
        FOREIGN KEY (id)
        REFERENCES usuarios (id)
);
