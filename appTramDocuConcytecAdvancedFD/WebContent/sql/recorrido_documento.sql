select md.fecha_movimiento,mo.descripcion_motivo,md.hora_movimiento,
md.codigo_documento,md.secuencia_movimiento,md.estado_movimiento,
of_a.descripcion_oficina,in_a.descripcion_institucion,dc.naturaleza_documento,
dc.numero_documento,dc.asunto_documento,dc.fecha_documento,tipo_documento.descripcion_tipo,
dc.estado_documento,md.doc_deriva,in_b.siglas_institucion,of_b.siglas_oficina,
of_c.siglas_oficina,md.ultimo_usuario,md.observa_movimiento,md.oficina_deriva
from tramite.movimientos_doc md left outer join tramite.motivo mo on md.codigo_motivo
= mo.codigo_motivo,tramite.movimientos_doc left outer join tramite.per_juridica as
institucion_a on movimientos_doc.codigo_institucion = institucion_a.codigo_institucion,documento
left outer join tramite.doc_entrada on documento.codigo_documento = doc_entrada.codigo_documento,documento
left outer join tramite.doc_interno_origen on documento.codigo_documento = doc_interno_origen.codigo_documento,tramite.documento
left outer join tramite.doc_salida_origen on documento.codigo_documento = doc_salida_origen.codigo_documento,doc_entrada
left outer join tramite.institucion as institucion_b on doc_entrada.origen_documento
= institucion_b.codigo_institucion,tramite.movimientos_doc left outer join tramite.oficinas
as oficinas_a on movimientos_doc.codigo_fondo = oficinas_a.codigo_fondo
and movimientos_doc.codigo_oficina = oficinas_a.codigo_oficina,tramite.doc_interno_origen
left outer join tramite.oficinas as oficinas_b on doc_interno_origen.codigo_fondo
= oficinas_b.codigo_fondo and doc_interno_origen.codigo_oficina = oficinas_b.codigo_oficina,tramite.doc_salida_origen
left outer join tramite.oficinas as oficinas_c on doc_salida_origen.codigo_fondo
= oficinas_c.codigo_fondo and doc_salida_origen.codigo_oficina = oficinas_c.codigo_oficina,tramite.tipo_documento
where(documento.codigo_documento = movimientos_doc.codigo_documento)
and(tipo_documento.codigo_tipo = documento.codigo_tipo) and((movimientos_doc.codigo_inicia
= '20083')) order by movimientos_doc.codigo_documento asc,movimientos_doc.secuencia_movimiento
asc 


select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento,md.hora_movimiento, 
md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento,md.ultimo_usuario, 
md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.fecha_rpta,  
tp.descripcion_tipo,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento, 
de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente, 
de.destinatario,of.codigo_oficina,of.descripcion_oficina,of_a.descripcion_oficina,pr.codigo_personal, 
pr.nombre_personal
 from tramite.movimientos_doc md,tramite.tipo_documento tp, 
tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of,tramite.oficinas of_a,tramite.personal pr
where tp.codigo_tipo=dc.codigo_tipo and dc.codigo_documento=md.codigo_documento and  
dc.codigo_documento=de.codigo_documento and md.codigo_documento=de.codigo_documento and  
 de.destinatario=pr.codigo_personal and md.codigo_oficina=of.codigo_oficina and de.destino_documento=of_a.codigo_oficina and
md.codigo_inicia = '20082' order by md.codigo_documento asc,md.secuencia_movimiento
asc 

select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento,md.hora_movimiento, 
md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento,md.ultimo_usuario, 
md.doc_deriva,md.codigo_motivo,md.codigo_institucion
,pe.descripcion_institucion
 from tramite.movimientos_doc md left outer join tramite.per_juridica pe on md.codigo_institucion=pe.codigo_institucion 
where   
 
md.codigo_inicia = '20083' order by md.codigo_documento asc,md.secuencia_movimiento
asc 

select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento,md.hora_movimiento, 
md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento,md.ultimo_usuario, 
md.doc_deriva,md.observa_movimiento,md.codigo_motivo, 
tp.descripcion_tipo,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento, 
de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente, 
de.destinatario,of.codigo_oficina,of.descripcion_oficina,of_a.descripcion_oficina,pr.codigo_personal, 
pr.nombre_personal from tramite.movimientos_doc md,tramite.tipo_documento tp, 
tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of,tramite.oficinas of_a,tramite.personal pr  
where tp.codigo_tipo=dc.codigo_tipo and dc.codigo_documento=md.codigo_documento and  
dc.codigo_documento=de.codigo_documento and md.codigo_documento=de.codigo_documento and  
 de.destinatario=pr.codigo_personal and md.codigo_oficina=of.codigo_oficina and de.destino_documento=of_a.codigo_oficina and  
md.codigo_inicia = '20085'  
 order by  md.fecha_movimiento desc, md.hora_movimiento desc, md.codigo_documento desc


select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento,md.hora_movimiento, 
md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento,md.ultimo_usuario, 
md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.desc_origen,md.tipo,md.tipo, 
tp.descripcion_tipo,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento, 
de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente, 
de.destinatario,of.codigo_oficina,of.descripcion_oficina,of_a.descripcion_oficina,pr.codigo_personal, 
pr.nombre_personal from tramite.movimientos_doc md,tramite.tipo_documento tp, 
tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of,tramite.oficinas of_a,tramite.personal pr  
where tp.codigo_tipo=dc.codigo_tipo and dc.codigo_documento=md.codigo_documento and  
dc.codigo_documento=de.codigo_documento and md.codigo_documento=de.codigo_documento and  
 de.destinatario=pr.codigo_personal and md.codigo_oficina=of.codigo_oficina and de.destino_documento=of_a.codigo_oficina and  
md.codigo_oficina like '0002' and md.codigo_fondo like '01' 
 order by  md.fecha_movimiento desc, md.hora_movimiento desc, md.codigo_documento desc



/************************seleccion frame central*********/

select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento,md.hora_movimiento, 
md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento,md.ultimo_usuario, 
md.doc_deriva,md.observa_movimiento,md.codigo_motivo, 
tp.descripcion_tipo,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento, 
de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente, 
de.destinatario,of.codigo_oficina,of.descripcion_oficina,of_a.descripcion_oficina,pr.codigo_personal, 
pr.nombre_personal from tramite.movimientos_doc md,tramite.tipo_documento tp, 
tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of,tramite.oficinas of_a,tramite.personal pr  
where tp.codigo_tipo=dc.codigo_tipo and dc.codigo_documento=md.codigo_documento and  
dc.codigo_documento=de.codigo_documento and md.codigo_documento=de.codigo_documento and  
 de.destinatario=pr.codigo_personal and md.codigo_oficina=of.codigo_oficina and de.destino_documento=of_a.codigo_oficina and  
md.codigo_oficina like '0002' and md.codigo_fondo like '01' 
 order by  md.fecha_movimiento desc, md.hora_movimiento desc, md.codigo_documento desc




