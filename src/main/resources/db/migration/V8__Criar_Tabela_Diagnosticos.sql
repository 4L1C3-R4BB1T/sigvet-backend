CREATE TABLE diagnosticos (
    id          BIGSERIAL       NOT NULL, 
    diagnostico VARCHAR(255)    NOT NULL,
    observacoes TEXT            NULL,
    consulta_id BIGINT          NOT NULL,
    created_at   TIMESTAMP       NOT NULL DEFAULT NOW(),
    updated_at   TIMESTAMP       NOT NULL DEFAULT NOW(),
    CONSTRAINT pk_diagnostico 
        PRIMARY KEY (id),
    CONSTRAINT fk_diagnostico_consulta 
        FOREIGN KEY (consulta_id)
        REFERENCES consultas (id)
);