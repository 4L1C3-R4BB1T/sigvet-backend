CREATE TABLE veterinarios (
    id              BIGSERIAL       NOT NULL,
    especialidade   VARCHAR(255)    NOT NULL,
    crmv            VARCHAR(45)     NOT NULL,
    crmv_uf         CHAR(2)         NOT NULL,
    CONSTRAINT pk_veterinario
        PRIMARY KEY (id),
    CONSTRAINT fk_veterinario_usuario 
        FOREIGN KEY (id)
        REFERENCES usuarios (id)
);

CREATE INDEX veterinarios_index_crmv ON veterinarios (crmv);