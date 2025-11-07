CREATE DATABASE db_estoque_veiculos;

USE db_estoque_veiculos;

CREATE TABLE tbl_marcas (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(45) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE tbl_modelos (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(45) NOT NULL,
    id_marca INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_marca) REFERENCES tbl_marcas(id)
);

CREATE TABLE tbl_veiculos (
    id INT NOT NULL AUTO_INCREMENT,
    cor VARCHAR(20),
    ano INT,
    preco DECIMAL(10,2),
    quilometragem INT,
    status VARCHAR(20),
    id_modelo INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_modelo) REFERENCES tbl_modelos(id)
);

INSERT INTO tbl_marcas (nome) VALUES
('Fiat'),
('Volkswagen'),
('Chevrolet'),
('Toyota'),
('Honda');

INSERT INTO tbl_modelos (nome, id_marca) VALUES
('Uno', 1),
('Argo', 1),
('Gol', 2),
('Onix', 3),
('Corolla', 4),
('Civic', 5);

INSERT INTO tbl_veiculos (cor, ano, preco, quilometragem, status, id_modelo) VALUES
('Preto', 2015, 35000.00, 60000, 'disponivel', 1),
('Branco', 2018, 45000.00, 40000, 'vendido', 2),
('Prata', 2020, 55000.00, 30000, 'disponivel', 3),
('Vermelho', 2019, 70000.00, 20000, 'disponivel', 4),
('Cinza', 2022, 130000.00, 5000, 'disponivel', 5),
('Azul', 2021, 125000.00, 8000, 'vendido', 6);

SELECT v.id, m.nome AS modelo, ma.nome AS marca, v.ano, v.cor, v.preco, v.status
FROM tbl_veiculos v
JOIN tbl_modelos m ON v.id_modelo = m.id
JOIN tbl_marcas ma ON m.id_marca = ma.id;

