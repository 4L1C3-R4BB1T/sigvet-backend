CREATE TABLE users (
    id          BIGSERIAL           NOT NULL,
    username    VARCHAR(100)        NOT NULL,
    password    VARCHAR(100)        NOT NULL,
    email       VARCHAR(100)        NOT NULL,
    name        VARCHAR(100)        NOT NULL,
    document    CHAR(14)            NOT NULL,
    phone       VARCHAR(18)         NULL,
    created_at  TIMESTAMP           NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP           NOT NULL DEFAULT NOW(),
    deleted     BOOLEAN             DEFAULT  false,
    CONSTRAINT pk_user
        PRIMARY KEY (id)
);

CREATE INDEX users_index_username ON users (username);
CREATE INDEX users_index_document ON users (document);
CREATE INDEX users_index_email ON users (email);
