select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento,md.hora_movimiento,md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento,
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.fecha_rpta,md.codigo_expediente,md.oficina_origen,md.oficina_deriva,
md.correlativo_deriva,md.observa_registro,md.codigo_recepcion,md.flag,tp.descripcion_tipo,md.desc_origen,dc.numero_documento,dc.naturaleza_documento,
dc.asunto_documento,dc.numero_documento_ant,dc.observa_documento,dc.fecha_registro,dc.hora_registro,dc.dirigido,de.origen_documento,de.destino_documento
,de.desc_origen as nombre_procedencia,de.tipo,de.remitente,md.destinatario,de.firmadopor,de.medio,de.codigo_solicitud,of.codigo_oficina,
of.descripcion_oficina,of_a.descripcion_oficina as descripcion_destino,pr.codigo_personal,pr.nombre_personal from tramite.movimientos_doc md
,tramite.tipo_documento tp,tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of,tramite.oficinas of_a,tramite.personal pr where
 tp.codigo_tipo=dc.codigo_tipo and dc.codigo_documento=md.codigo_documento and dc.codigo_documento=de.codigo_documento and
 md.codigo_documento=de.codigo_documento and de.destinatario=pr.codigo_personal and md.codigo_oficina=of.codigo_oficina and
 de.destino_documento=of_a.codigo_oficina and md.estado_movimiento ='2' and md.codigo_fondo like '01' and md.codigo_oficina like '2'
 and md.codigo_documento not in (select codigo_documento from tramite.movimientos_doc where estado_movimiento = '2' and codigo_fondo like
 '01' and codigo_oficina like '2' and flag ='I') order by md.codigo_documento desc


select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento,md.hora_movimiento,md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento,
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.fecha_rpta,md.codigo_expediente,md.oficina_origen,
md.oficina_deriva,md.correlativo_deriva,md.observa_registro,md.codigo_recepcion,md.flag,tp.descripcion_tipo,dc.numero_documento,
dc.naturaleza_documento,dc.asunto_documento,dc.numero_documento_ant,de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,
de.remitente,de.destinatario,of.codigo_oficina,of.descripcion_oficina,of_a.descripcion_oficina as descripcion_destino,pr.codigo_personal,
pr.nombre_personal from tramite.movimientos_doc md,tramite.tipo_documento tp,tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of,
tramite.oficinas of_a,tramite.personal pr where tp.codigo_tipo=dc.codigo_tipo and dc.codigo_documento=md.codigo_documento and 
dc.codigo_documento=de.codigo_documento and md.codigo_documento=de.codigo_documento and de.destinatario=pr.codigo_personal and
 md.codigo_oficina=of.codigo_oficina and de.destino_documento=of_a.codigo_oficina and md.estado_movimiento = '3' and md.codigo_fondo like '01'
 and md.codigo_oficina like '2' and md.codigo_documento not in (select codigo_documento from tramite.movimientos_doc where estado_movimiento = '3'
 and codigo_fondo like '01' and codigo_oficina like '2' and flag ='I') order by md.codigo_documento desc


select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento,md.hora_movimiento,md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento,
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.fecha_rpta,md.codigo_expediente,md.oficina_origen,md.oficina_deriva,
md.correlativo_deriva,md.codigo_recepcion,md.destinatario as ofidestinatario,tp.descripcion_tipo,md.numero_documento,dc.naturaleza_documento,
md.asunto_documento,dc.numero_documento_ant,de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente,de.destinatario,
of.codigo_oficina,of.descripcion_oficina,of_a.descripcion_oficina as descripcion_destino,pr.codigo_personal,pr.nombre_personal from
 tramite.movimientos_doc md,tramite.tipo_documento tp,tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of,tramite.oficinas of_a,
tramite.personal pr where tp.codigo_tipo=dc.codigo_tipo and dc.codigo_documento=md.codigo_documento and dc.codigo_documento=de.codigo_documento
 and md.codigo_documento=de.codigo_documento and de.destinatario=pr.codigo_personal and md.codigo_oficina=of.codigo_oficina and
 de.destino_documento=of_a.codigo_oficina and dc.codigo_documento like '306' and md.secuencia_movimiento<>'1' order by md.secuencia_movimiento desc,
 md.hora_movimiento desc, md.fecha_movimiento desc

select max(secuencia_movimiento) from tramite.movimientos_doc where codigo_documento=306
select dc1.codigo_documento,dc1.numero_documento from tramite.documento dc1,tramite.movimientos_doc md1 where
  dc1.codigo_inicia='306' and dc1.codigo_inicia=md1.codigo_inicia and md1.secuencia_movimiento<>'1'

select dc1.codigo_documento,dc1.numero_documento from tramite.documento dc1 where
  dc1.codigo_inicia='306' 

select re.codigo_documento,re.codigo_oficina,re.fecha_movimiento,re.hora_movimiento,re.estado_movimiento,re.codigo_inicia,re.secuencia_movimiento,
re.ultimo_usuario,re.doc_deriva,re.observa_movimiento,re.codigo_motivo,re.desc_origen,re.tipo,re.fecha_rpta,re.estado,re.oficina_origen,
re.oficina_deriva,re.destinatario as nombdestinatario,tp.descripcion_tipo,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,
de.origen_documento,de.destino_documento,de.remitente,de.destinatario,of.codigo_oficina,of.descripcion_oficina as desc_oficina,
of_a.descripcion_oficina,pr.codigo_personal,pr.nombre_personal from tramite.recorrido_doc re,tramite.tipo_documento tp,tramite.documento dc,
tramite.doc_entrada de,tramite.oficinas of,tramite.oficinas of_a,tramite.personal pr where tp.codigo_tipo=dc.codigo_tipo and
 dc.codigo_documento=re.codigo_documento and dc.codigo_documento=de.codigo_documento and re.codigo_documento=de.codigo_documento and 
de.destinatario=pr.codigo_personal and re.codigo_oficina=of.codigo_oficina and de.destino_documento=of_a.codigo_oficina and re.codigo_inicia = '306'
order by re.fecha_consulta asc,re.hora_movimiento asc,re.codigo_documento asc,re.secuencia_movimiento asc


select count(*) as c_valor from tramite.movimientos_doc where codigo_documento=306

select max(secuencia_movimiento) as as_valor from tramite.movimientos_doc where  codigo_documento='306'