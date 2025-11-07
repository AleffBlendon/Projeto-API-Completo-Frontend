CREATE DATABASE IF NOT EXISTS db_estoque_veiculos;
USE db_estoque_veiculos;


DROP TABLE IF EXISTS tbl_veiculos;

CREATE TABLE tbl_veiculos (
    id INT NOT NULL AUTO_INCREMENT,
    cor VARCHAR(50),
    ano INT,
    preco DECIMAL(10,2),
    quilometragem INT,
    status VARCHAR(20),
    foto VARCHAR(300),
    marca VARCHAR(100),
    modelo VARCHAR(100),
    PRIMARY KEY (id)
);


INSERT INTO tbl_veiculos (cor, ano, preco, quilometragem, status, foto, marca, modelo) VALUES
('Preto', 2015, 35000, 60000, 'disponivel', 'https://cdn.salaodocarro.com.br/_upload/carros/2024/07/05/fiat-punto-2015-preto-352027-0.jpg', 'Fiat', 'Punto'),
('Branco', 2018, 45000, 40000, 'vendido', 'https://s2-autoesporte.glbimg.com/kARbdCujMth-aj6Zm_Irq_UGPjo=/0x0:620x413/600x0/smart/filters:gifv():strip_icc()/i.s3.glbimg.com/v1/AUTH_cf9d035bf26b4646b105bd958f32089d/internal_photos/bs/2020/W/i/MNI843QdqnRBCfBVmUPg/2014-07-25-novo-ford-ka-2.jpg', 'Ford', 'Ka'),
('Prata', 2020, 55000, 30000, 'disponivel', 'https://http2.mlstatic.com/D_NQ_NP_628517-MLB88531150710_072025-O-chevrolet-onix-10-turbo-aut-5p.webp', 'Chevrolet', 'Onix'),
('Vermelho', 2019, 70000, 20000, 'disponivel', 'https://img.olx.com.br/thumbs700x500/69/691517585863435.webp', 'Volkswagen', 'Gol'),
('Cinza', 2022, 130000, 5000, 'disponivel', 'https://s3.ecompletocarros.dev/images/lojas/125/veiculos/133737/veiculoInfoVeiculoImagesMobile/vehicle_image_1676920654_06850b859953a4d9282adc177eb502a5.jpeg', 'Honda', 'Civic'),
('Azul', 2021, 125000, 8000, 'vendido', 'https://image.webmotors.com.br/_fotos/anunciousados/gigante/2025/202511/20251105/hyundai-hb20-1.0-tgdi-flex-platinum-automatico-wmimagem17275459166.jpg', 'Hyundai', 'HB20');


SELECT * FROM tbl_veiculos;