CREATE TABLE diagnostics (
    id              BIGSERIAL       NOT NULL,
    diagnosis       VARCHAR(255)    NOT NULL,
    comments        TEXT            NULL,
    consult_id      BIGINT          NOT NULL,
    created_at      TIMESTAMP       NOT NULL DEFAULT NOW(),
    updated_at      TIMESTAMP       NOT NULL DEFAULT NOW(),
    deleted         BOOLEAN         DEFAULT  false,
    CONSTRAINT pk_diagnostic
        PRIMARY KEY (id),
    CONSTRAINT fk_diagnostic_consult
        FOREIGN KEY (consult_id)
        REFERENCES consults (id)
);