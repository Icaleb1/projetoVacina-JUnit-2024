DROP DATABASE concesionaria;

CREATE DATABASE concesionaria;
USE concesionaria;

CREATE TABLE montadora (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    paisFundacao VARCHAR(100) NOT NULL,
    nomePresidente VARCHAR(100) NOT NULL,
    dataFundacao DATE NOT NULL
);

CREATE TABLE carro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    modelo VARCHAR(100) NOT NULL,
    placa VARCHAR(10) NOT NULL UNIQUE,
    montadora_id INT NOT NULL,
    ano INT NOT NULL,
    valor DOUBLE NOT NULL,
    FOREIGN KEY (montadora_id) REFERENCES montadora(id)
);


INSERT INTO concesionaria.montadora (nome,paisFundacao,nomePresidente,dataFundacao) VALUES ("ford","Brasil","Caleb","2004-08-08");
INSERT INTO concesionaria.montadora (nome, paisFundacao, nomePresidente, dataFundacao) VALUES ("Ford", "Brasil", "Caleb", "2004-08-08");
INSERT INTO concesionaria.montadora (nome, paisFundacao, nomePresidente, dataFundacao) VALUES ("Chevrolet", "Estados Unidos", "Mary", "1911-11-03");
INSERT INTO concesionaria.montadora (nome, paisFundacao, nomePresidente, dataFundacao) VALUES ("Toyota", "Japão", "Akio", "1937-08-28");
INSERT INTO concesionaria.montadora (nome, paisFundacao, nomePresidente, dataFundacao) VALUES ("Volkswagen", "Alemanha", "Herbert", "1937-05-28");
INSERT INTO concesionaria.montadora (nome, paisFundacao, nomePresidente, dataFundacao) VALUES ("Hyundai", "Coreia do Sul", "Euisun", "1967-12-29");

INSERT INTO concesionaria.carro (modelo,placa,montadora_id, ano, valor) VALUES ("hyundai", "sla12", 1, "2002", 22022);
INSERT INTO concesionaria.carro (modelo, placa, montadora_id, ano, valor) VALUES ("HB20", "ABC1234", 5, 2020, 55000);
INSERT INTO concesionaria.carro (modelo, placa, montadora_id, ano, valor) VALUES ("Corolla", "XYZ5678", 3, 2018, 85000);
INSERT INTO concesionaria.carro (modelo, placa, montadora_id, ano, valor) VALUES ("Golf", "LMN9012", 4, 2019, 75000);
INSERT INTO concesionaria.carro (modelo, placa, montadora_id, ano, valor) VALUES ("Cruze", "OPQ3456", 2, 2021, 90000);
INSERT INTO concesionaria.carro (modelo, placa, montadora_id, ano, valor) VALUES ("Fiesta", "RST7890", 1, 2017, 65000);



select * from montadora;
select * from carro;
