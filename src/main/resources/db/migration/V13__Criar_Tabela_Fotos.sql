CREATE TABLE fotos (
    id          BIGSERIAL       NOT NULL,
    tipo_mime   VARCHAR(100)    NOT NULL,
    dados       BYTEA           NOT NULL,
    entidade_id BIGINT          NOT NULL,
    entidade_tipo VARCHAR(30)   NOT NULL,
    created_at  TIMESTAMP       NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP       NOT NULL DEFAULT NOW(),
    CONSTRAINT pk_foto
        PRIMARY KEY (id)
)