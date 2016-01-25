USE DATABASE dbClinSaludTotal
;
DROP TABLE estadoDocumentoInterno CASCADE
;
CREATE TABLE estadoDocumentoInterno ( 
	codigo_estado_documento integer NOT NULL,
	nombre varchar(250)
)
;

ALTER TABLE estadoDocumentoInterno ADD CONSTRAINT PK_EstadoDocumentoInterno 
	PRIMARY KEY (codigo_estado_documento)
;


