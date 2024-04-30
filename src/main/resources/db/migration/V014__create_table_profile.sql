CREATE TABLE profiles (
    user_id         BIGINT          NOT NULL,
    file_name       VARCHAR(150)    NOT NULL,
    description     VARCHAR(255)    NULL,
    content_type    VARCHAR(80)     NULL,
    "size"          INT             NOT NULL,
    CONSTRAINT pk_profile
        PRIMARY KEY (user_id)
);