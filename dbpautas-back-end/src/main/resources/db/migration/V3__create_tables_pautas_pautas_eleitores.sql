CREATE TABLE pautas (
    id BIGSERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    resumo VARCHAR(500) NOT NULL,
    descricao TEXT NOT NULL,
    categoria VARCHAR(20) NOT NULL CHECK (categoria IN ('FINANCAS', 'EDUCACAO', 'SAUDE', 'TECNOLOGIA', 'MEIO_AMBIENTE', 'TRANSPORTE', 'SEGURANCA_PUBLICA', 'INFRAESTRUTURA')),
    aberta BOOLEAN NOT NULL DEFAULT FALSE,
    aberto_as TIMESTAMP,
    votos_sim INTEGER NOT NULL DEFAULT 0,
    votos_nao INTEGER NOT NULL DEFAULT 0,
    tempo_de_sessao_em_minutos INTEGER NOT NULL DEFAULT 1
);

CREATE TABLE pautas_eleitores (
    pauta_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    PRIMARY KEY (pauta_id, usuario_id),
    FOREIGN KEY (pauta_id) REFERENCES pautas(id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);