DROP DATABASE IF EXISTS alquilervehiculos;
CREATE DATABASE alquilervehiculos;
DROP USER IF EXISTS 'admin_alquiler'@'localhost';
CREATE USER 'admin_alquiler'@'localhost' IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON alquilervehiculos.* TO 'admin_alquiler'@'localhost';
FLUSH PRIVILEGES;

USE alquilervehiculos;

CREATE TABLE IF NOT EXISTS cliente(
  nif VARCHAR(9) NOT NULL,
  nombre VARCHAR(40) NOT NULL,
  apellido1 VARCHAR(40) NOT NULL,
  apellido2 VARCHAR(40) NOT NULL,
  PRIMARY KEY (nif));

CREATE TABLE IF NOT EXISTS vehiculo (
  matricula VARCHAR(7) NOT NULL,
  modelo VARCHAR(40) NOT NULL,
  PRIMARY KEY(matricula));

CREATE TABLE IF NOT EXISTS alquiler(
  id INTEGER NOT NULL AUTO_INCREMENT,
  nif_cliente VARCHAR(9),
  matricula VARCHAR(7),
  fechainicio DATE NOT NULL,
  fechafin DATE  NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (nif_cliente) REFERENCES cliente(nif),
  FOREIGN KEY (matricula) REFERENCES vehiculo(matricula));


INSERT INTO cliente VALUES ('12334455S', 'Raimon', 'Sellarès', 'Feiner');
INSERT INTO cliente VALUES ('55443321G', 'Alberto', 'Martínez', 'Perez');

INSERT INTO vehiculo VALUES ('3415KJN','Seat Ibiza');
INSERT INTO vehiculo VALUES ('9633BDT','Renault Twingo');
INSERT INTO vehiculo VALUES ('2547CDG','Opel Corsa');
INSERT INTO vehiculo VALUES ('8741DFG','Toyota Aygo');

Insert INTO alquiler values (null, '12334455S', '3415KJN', "2021-06-15", "2021-06-19");
Insert INTO alquiler values (null, '55443321G', '2547CDG', "2021-06-15", "2021-06-19");
Insert INTO alquiler values (null, '12334455S', '8741DFG', "2021-06-18", "2021-06-19");

DROP PROCEDURE IF EXISTS insertar_cliente;
DROP PROCEDURE IF EXISTS obtener_cliente;
DROP PROCEDURE IF EXISTS modifica_cliente;
DROP PROCEDURE IF EXISTS elimina_cliente;


DROP PROCEDURE IF EXISTS insertar_vehiculo;
DROP PROCEDURE IF EXISTS obtener_vehiculo;
DROP PROCEDURE IF EXISTS modifica_vehiculo;
DROP PROCEDURE IF EXISTS elimina_vehiculo;

DROP PROCEDURE IF EXISTS insertar_alquiler;
DROP PROCEDURE IF EXISTS obtener_alquiler;
DROP PROCEDURE IF EXISTS modifica_alquiler;
DROP PROCEDURE IF EXISTS elimina_alquiler;


DELIMITER //

CREATE PROCEDURE insertar_cliente(IN nif VARCHAR(9), IN nombre VARCHAR(40), IN apellido1 VARCHAR(40), IN apellido2 VARCHAR(40))
BEGIN
	INSERT INTO cliente VALUES (nif, nombre, apellido1, apellido2);
END //

DELIMITER //
CREATE PROCEDURE insertar_vehiculo(IN matricula VARCHAR(7), IN modelo VARCHAR(40))
BEGIN
	INSERT INTO vehiculo VALUES (matricula, modelo);
END //

DELIMITER //
CREATE PROCEDURE insertar_alquiler (IN nif_cliente VARCHAR(9), IN matricula VARCHAR(7), IN fechainicio DATE, IN fechafin DATE)
BEGIN
	INSERT INTO alquiler VALUES (null, nif_cliente, matricula, fechainicio, fechafin);
END //

CREATE PROCEDURE obtener_cliente(IN nif INT)
BEGIN
	SELECT * FROM cliente c WHERE c.nif = nif;
END //

CREATE PROCEDURE obtener_vehiculo(IN matriucla INT)
BEGIN
	SELECT * FROM vehiculo v WHERE v.matricula = matricula;
END //

CREATE PROCEDURE obtener_vehiculo_modelo(IN matriucla INT)
BEGIN
	SELECT * FROM vehiculo v WHERE v.modelo = modelo;
END //

CREATE PROCEDURE obtener_aquiler_nif(IN nif VARCHAR(9))
BEGIN
SELECT * FROM alquiler a WHERE a.nif_cliente = nif;
END //

CREATE PROCEDURE obtener_aquiler_matricula(IN matricula VARCHAR(7))
BEGIN
SELECT * FROM alquiler a WHERE a.matricula = matricula;
END //

CREATE PROCEDURE obtener_alquiler(IN nif VARCHAR(9), IN matricula VARCHAR(7))
BEGIN
SELECT * FROM alquiler a WHERE a.matricula = matricula AND a.nif_cliente = nif;
END //

CREATE PROCEDURE modifica_cliente(IN nif VARCHAR(9), nombre VARCHAR(40), IN apellido1 VARCHAR(40),  IN apellido2 VARCHAR(40))
BEGIN
	UPDATE cliente c SET c.nombre=nombre, c.apellido1=apellido1, c.apellido2=apellido2 WHERE c.nif=nif; 
END //

CREATE PROCEDURE modifica_vehiculo (IN matricula_original VARCHAR(7), IN matricula VARCHAR(7), IN modelo VARCHAR(40))
BEGIN
	UPDATE vehiculo v SET v.matricula=matricula, v.modelo=modelo WHERE v.matricula=matricula_original;
END //

CREATE PROCEDURE modifica_alquiler (IN id INT, nif_cliente VARCHAR(9), matricula VARCHAR(7),IN fechainicio DATE, IN fechafin DATE)
BEGIN
	UPDATE alquiler a SET a.nif_cliente=nif_cliente, a.matricula=matricula, a.fechainicio=fechainicio, a.fechafin=fechafin WHERE a.id=id;
END //

CREATE PROCEDURE elimina_cliente(IN nif_ VARCHAR(9))
BEGIN
	DELETE FROM cliente WHERE nif = nif_;
END //

CREATE PROCEDURE elimina_vehiculo(IN matricula_ VARCHAR(7))
BEGIN
	DELETE FROM vehiculo WHERE matricula = matricula_;
END //

CREATE PROCEDURE elimina_alquiler(IN id_ INTEGER)
BEGIN
	DELETE FROM alquiler WHERE id = id_;
END //

DELIMITER ;
