CREATE TABLE tb_categoria
(
    id   UUID NOT NULL,
    nome VARCHAR(255),
    tipo VARCHAR(255),
    CONSTRAINT pk_tb_categoria PRIMARY KEY (id)
);