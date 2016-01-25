USE DATABASE dbClinSaludTotal
;
DROP TABLE documento_tipo_documento_interno CASCADE
;
CREATE TABLE documento_tipo_documento_interno ( 
	codigo_tipo_documento_interno integer,
	codigo_documento integer,
	asunto varchar(250),
	descripcion text,
	dirigido_a varchar(250),
	firmado_por varchar(250),
	fecha_creacion timestamp,
	fecha_modificacion timestamp,
	codigo_estado_documento integer
)
;

