CREATE TABLE photos (
    id              BIGSERIAL       NOT NULL,
    entity_id       BIGINT          NOT NULL,
    entity_type     VARCHAR(80)     NOT NULL,
    file_name       VARCHAR(150)    NOT NULL,
    "data"          BYTEA           NOT NULL,
    content_type    VARCHAR(80)     NULL,
    "size"          BIGINT          NOT NULL,
    CONSTRAINT pk_photo
        PRIMARY KEY (id)
);