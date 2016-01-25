select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento,md.hora_movimiento, 
md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento,md.ultimo_usuario, 
md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.fecha_rpta, 
md.codigo_expediente,md.oficina_origen,md.oficina_deriva,md.correlativo_deriva,tp.descripcion_tipo, 
dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,dc.numero_documento_ant,dc.codigo_tipo, 
de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente, 
de.destinatario,de.codigo_solicitud,of.codigo_oficina,of.descripcion_oficina,of_a.descripcion_oficina as 
 descripcion_destino,pr.codigo_personal,pr.nombre_personal from tramite.movimientos_doc 
 md,tramite.tipo_documento tp,tramite.documento dc,tramite.doc_entrada de, 
tramite.oficinas of,tramite.oficinas of_a,tramite.personal pr where 
 tp.codigo_tipo=dc.codigo_tipo and dc.codigo_documento=md.codigo_documento and 
 dc.codigo_documento=de.codigo_documento and md.codigo_documento=de.codigo_documento 
 and de.destinatario=pr.codigo_personal and md.codigo_oficina=of.codigo_oficina and 
 de.destino_documento=of_a.codigo_oficina    
 and dc.codigo_tipo = '3' and de.firmadopor = 'RICARDO RODRIGUEZ' and md.codigo_oficina='1' order by md.fecha_movimiento desc,
 md.hora_movimiento desc, md.codigo_documento desc

select count(usuario) as c_valor from tramite.usuarios WHERE
     			usuario='rrodriguez' and clave='rrodriguez'