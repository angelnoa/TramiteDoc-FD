CREATE TABLE tramite.medio
(
  id_medio int8 NOT NULL,
  codigo_medio char(2),
  descripcion varchar(50),
  estado char(1)
) 
WITH OIDS;
ALTER TABLE tramite.medio OWNER TO postgres;


insert into tramite.medio (id_medio,codigo_medio,descripcion,estado)values('1','OR','Original','A');
insert into tramite.medio (id_medio,codigo_medio,descripcion,estado)values('2','FA','Fax','A');
insert into tramite.medio (id_medio,codigo_medio,descripcion,estado)values('3','CO','Copia Inf.','A');
insert into tramite.medio (id_medio,codigo_medio,descripcion,estado)values('4','EM','Email','A');

select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento,
	md.hora_movimiento,md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento,
	md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.fecha_rpta,
	md.codigo_expediente,md.oficina_origen,md.oficina_deriva,md.correlativo_deriva,md.observa_registro,md.codigo_recepcion,
	tp.descripcion_tipo,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,
	dc.numero_documento_ant,de.origen_documento,de.destino_documento,de.desc_origen,
	de.tipo,de.remitente,de.destinatario,of.codigo_oficina,of.descripcion_oficina,
	of_a.descripcion_oficina as descripcion_destino,pr.codigo_personal,pr.nombre_personal
	 from tramite.movimientos_doc md,tramite.tipo_documento tp,tramite.documento dc,
	tramite.doc_entrada de,tramite.oficinas of,tramite.oficinas of_a,tramite.personal pr
	 where tp.codigo_tipo=dc.codigo_tipo and dc.codigo_documento=md.codigo_documento and
	 dc.codigo_documento=de.codigo_documento and md.codigo_documento=de.codigo_documento
	 and de.destinatario=pr.codigo_personal and md.codigo_oficina=of.codigo_oficina and
	 de.destino_documento=of_a.codigo_oficina and
	 md.codigo_fondo like '01'