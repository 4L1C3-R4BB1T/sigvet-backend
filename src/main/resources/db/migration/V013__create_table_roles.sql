CREATE TABLE roles (
    user_id BIGINT      NOT NULL,
    "role"   VARCHAR(10) NOT NULL,
    CONSTRAINT pk_roles
        PRIMARY KEY (user_id, "role"),
    CONSTRAINT fk_roles_user
        FOREIGN KEY (user_id)
        REFERENCES users (id)
);