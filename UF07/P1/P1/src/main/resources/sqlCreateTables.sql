DROP DATABASE IF EXISTS mercadaw;
CREATE DATABASE IF NOT EXISTS mercadaw;
USE mercadaw;

DROP TABLE IF EXISTS productos,
                     tipos;
                     
                     
CREATE TABLE tipos (
	id 				INT 			AUTO_INCREMENT,
    nombre 			VARCHAR(20),
    
    PRIMARY KEY (id),
    UNIQUE  KEY (nombre)
);
CREATE TABLE productos (
	id 				INT 			AUTO_INCREMENT,
    referencia 		CHAR(10) 		NOT NULL,
    nombre 			VARCHAR(20) 	NOT NULL,
    descripcion 	VARCHAR(100),
    idTipo 			INT,
    cantidad 		INT 			NOT NULL,
    precio 			DECIMAL (6,2) 	NOT NULL,	
    descuento 		TINYINT 		NOT NULL,
    IVA 			TINYINT 		NOT NULL,
    aplicarDto 		BOOL 			NOT NULL,
    
    FOREIGN KEY (idTipo) REFERENCES tipos (id) ON DELETE CASCADE,
    PRIMARY KEY (id),
    UNIQUE 	KEY (referencia)
);