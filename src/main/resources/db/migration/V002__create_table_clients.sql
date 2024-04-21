CREATE TABLE clients (
    id  BIGSERIAL   NOT NULL,
    CONSTRAINT pk_client
        PRIMARY KEY (id),
    CONSTRAINT fk_client_user
        FOREIGN KEY (id)
        REFERENCES users (id)
);
