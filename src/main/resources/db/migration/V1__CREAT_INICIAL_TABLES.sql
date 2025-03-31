CREATE TABLE endereco
(
    id          UUID NOT NULL,
    logradouro  VARCHAR(255),
    numero      VARCHAR(255),
    complemento VARCHAR(255),
    bairro      VARCHAR(255),
    cidade      VARCHAR(255),
    uf          VARCHAR(255),
    cep         VARCHAR(255),
    cliente_id  UUID NOT NULL,
    CONSTRAINT pk_endereco PRIMARY KEY (id)
);

CREATE TABLE tb_cliente
(
    id       UUID NOT NULL,
    nome     VARCHAR(255),
    cpf      VARCHAR(255),
    email    VARCHAR(255),
    telefone VARCHAR(255),
    CONSTRAINT pk_tb_cliente PRIMARY KEY (id)
);

ALTER TABLE endereco
    ADD CONSTRAINT uc_endereco_cliente UNIQUE (cliente_id);

ALTER TABLE endereco
    ADD CONSTRAINT FK_ENDERECO_ON_CLIENTE FOREIGN KEY (cliente_id) REFERENCES tb_cliente (id);