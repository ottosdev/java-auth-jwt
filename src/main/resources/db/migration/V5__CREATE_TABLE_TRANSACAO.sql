CREATE TABLE tb_transacao
(
    id             VARCHAR(255) NOT NULL,
    cliente_id     UUID,
    categoria_id   UUID,
    nome           VARCHAR(255),
    valor          DECIMAL,
    data_transacao TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_tb_transacao PRIMARY KEY (id)
);

ALTER TABLE tb_transacao
    ADD CONSTRAINT FK_TB_TRANSACAO_ON_CATEGORIA FOREIGN KEY (categoria_id) REFERENCES tb_categoria (id);

ALTER TABLE tb_transacao
    ADD CONSTRAINT FK_TB_TRANSACAO_ON_CLIENTE FOREIGN KEY (cliente_id) REFERENCES tb_cliente (id);