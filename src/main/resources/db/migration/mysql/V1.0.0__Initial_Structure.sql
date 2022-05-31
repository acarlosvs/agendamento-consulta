-- Médico
CREATE TABLE medico (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    nome varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    senha varchar(500) NOT NULL,
    confirmacao_senha varchar(500) NOT NULL,
    especialidade varchar(255) NOT NULL,
    cpf varchar(255) NOT NULL,
    data_nascimento date NOT NULL,
    telefone varchar(255) NOT NULL,
    PRIMARY KEY(id),
    UNIQUE KEY cpf_medico_ukey (cpf),
    UNIQUE KEY email_medico_ukey (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Paciente
CREATE TABLE paciente (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    nome varchar(255) NOT NULL,
    cpf varchar(255) NOT NULL,
    PRIMARY KEY(id),
    UNIQUE KEY cpf_paciente_ukey (cpf)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Login
CREATE TABLE login (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    medico_id bigint NOT NULL,
    token varchar(255) NOT NULL,
    status varchar(255) NOT NULL,
    PRIMARY KEY(id),
    CONSTRAINT login_medico_id_fkey FOREIGN KEY (medico_id) REFERENCES medico(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Atendimento
CREATE TABLE atendimento (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    medico_id bigint NOT NULL,
    paciente_id bigint NOT NULL,
    data_hora timestamp NOT NULL,
    PRIMARY KEY(id),
    CONSTRAINT atendimendo_medico_id_fkey FOREIGN KEY (medico_id) REFERENCES medico(id),
    CONSTRAINT atendimendo_paciente_id_fkey FOREIGN KEY (paciente_id) REFERENCES paciente(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;