select movimientos_doc.codigo_documento,oficinas_a.descripcion_oficina,oficinas_b.descripcion_oficina,tipo_documento.descripcion_tipo
,documento.numero_documento,documento.naturaleza_documento,documento.fecha_documento,doc_salida_origen.codigo_oficina,
doc_salida_destino.codigo_institucion,institucion_a.descripcion_institucion,oficinas_c.descripcion_oficina,
institucion_b.descripcion_institucion,oficinas_d.descripcion_oficina,institucion_b.siglas_institucion,oficinas_c.siglas_oficina,
oficinas_b.siglas_oficina,movimientos_doc.fecha_movimiento,movimientos_doc.hora_movimiento,movimientos_doc.estado_movimiento,
motivo.descripcion_motivo,documento.asunto_documento,documento.folios_documento,documento.observa_documento,
serie_documental.descripcion_serie,expediente.descripcion_expediente,documento.fecha_registro,documento.hora_registro,
documento.ultima_modificacion,movimientos_doc.codigo_inicia,movimientos_doc.secuencia_movimiento,doc_interno_origen.codigo_oficina,
movimientos_doc.ultimo_usuario,personal_a.nombre_personal,personal_b.nombre_personal,personal_c.nombre_personal,
personal_d.nombre_personal,contactos_a.nombre_contacto,contactos_b.nombre_contacto,tipo_documento.es_multiple,
movimientos_doc.doc_deriva,movimientos_doc.observa_movimiento,movimientos_doc.codigo_motivo,'N',doc_interno_destino.codigo_oficina,
doc_entrada.destino_documento
from tramite.doc_salida_destino doc_salida_destino left outer join tramite.per_juridica institucion_a
on doc_salida_destino.codigo_institucion = institucion_a.codigo_institucion,
tramite.documento documento left outer join tramite.doc_interno_destino doc_interno_destino on documento.codigo_documento = doc_interno_destino.codigo_documento,
tramite.documento documento_a left outer join tramite.doc_interno_origen doc_interno_origen on documento_a.codigo_documento = doc_interno_origen.codigo_documento,
tramite.doc_interno_destino doc_interno_destino_a left outer join tramite.oficinas oficinas_a on doc_interno_destino_a.codigo_oficina= oficinas_a.codigo_oficina and doc_interno_destino_a.codigo_fondo = oficinas_a.codigo_fondo,
tramite.doc_interno_origen doc_interno_origen_a left outer join tramite.oficinas oficinas_b on doc_interno_origen_a.codigo_fondo= oficinas_b.codigo_fondo and 
doc_interno_origen_a.codigo_oficina = oficinas_b.codigo_oficina,
tramite.doc_entrada doc_entrada left outer join tramite.per_juridica institucion_b on doc_entrada.origen_documento= institucion_b.codigo_institucion,tramite.doc_entrada doc_entrada_a left outer join tramite.oficinas
oficinas_d on doc_entrada_a.destino_documento = oficinas_d.codigo_oficina
and doc_entrada_a.fondo_destino = oficinas_d.codigo_fondo,
tramite.doc_interno_origen doc_interno_origen_b left outer join tramite.personal personal_a on doc_interno_origen_b.codigo_personal= personal_a.codigo_personal,
tramite.doc_interno_destino doc_interno_destino_b left outer join tramite.personal  personal_b on doc_interno_destino_b.codigo_personal = personal_b.codigo_personal,
tramite.doc_salida_origen doc_salida_origen left outer join tramite.personal personal_c on doc_salida_origen.codigo_personal= personal_c.codigo_personal,
tramite.doc_salida_destino doc_salida_destino_a left outer join tramite.contactos contactos_a on doc_salida_destino_a.codigo_contacto = contactos_a.codigo_contacto,tramite.doc_entrada doc_entrada_b
left outer join tramite.contactos contactos_b on doc_entrada_b.remitente = contactos_b.codigo_contacto,tramite.doc_entrada doc_entrada_c
left outer join tramite.personal personal_d on doc_entrada_c.destinatario =personal_d.codigo_personal,
tramite.doc_salida_origen doc_salida_origen_a left outer join tramite.oficinas oficinas_c on doc_salida_origen_a.codigo_oficina = oficinas_c.codigo_oficina
and doc_salida_origen_a.codigo_fondo = oficinas_c.codigo_fondo,tramite.documento documento_b
left outer join tramite.doc_salida_origen doc_salida_origen_b on documento_b.codigo_documento = doc_salida_origen_b.codigo_documento,tramite.documento documento_c
left outer join tramite.doc_salida_destino doc_salida_destino_b on documento_c.codigo_documento = doc_salida_destino_b.codigo_documento,tramite.documento documento_d 
left outer join tramite.doc_entrada doc_entrada_d on documento_d.codigo_documento = doc_entrada_d.codigo_documento,tramite.movimientos_doc movimientos_doc
left outer join tramite.motivo motivo on movimientos_doc.codigo_motivo = motivo.codigo_motivo,tramite.movimientos_doc movimientos_doc_a
left outer join tramite.expediente expediente on movimientos_doc_a.codigo_expediente = expediente.codigo_expediente
and movimientos_doc_a.codigo_fondo = expediente.codigo_fondo and movimientos_doc_a.codigo_oficina
= expediente.codigo_oficina and movimientos_doc_a.codigo_serie = expediente.codigo_serie,tramite.movimientos_doc movimientos_doc_b
left outer join tramite.serie_documental serie_documental on movimientos_doc_b.codigo_fondo = serie_documental.codigo_fondo
and movimientos_doc_b.codigo_oficina = serie_documental.codigo_oficina
and movimientos_doc_b.codigo_serie = serie_documental.codigo_serie,tramite.tipo_documento tipo_documento
where documento.codigo_documento = movimientos_doc.codigo_documento
and tipo_documento.codigo_tipo = documento.codigo_tipo and movimientos_doc.codigo_oficina
='0002' and movimientos_doc.codigo_fondo ='01' and movimientos_doc.fecha_movimiento
>= '18/08/2008' and movimientos_doc.fecha_movimiento <= '18/08/2008'
order by movimientos_doc.fecha_movimiento desc,movimientos_doc.hora_movimiento
desc,movimientos_doc.codigo_documento desc,movimientos_doc.secuencia_movimiento
desc 

select md.codigo_documento,md.fecha_movimiento,md.hora_movimiento,md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento,
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo
 from tramite.movimientos_doc md where md.codigo_oficina
like '0001' and md.codigo_fondo like '01' and  md.fecha_movimiento
>= '2008-12-08' and  md.fecha_movimiento <= '2008-12-08' order by  md.fecha_movimiento desc, md.hora_movimiento
desc, md.codigo_documento desc


select md.codigo_documento,md.fecha_movimiento,md.hora_movimiento,md.estado_movimiento,md.secuencia_movimiento,
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.codigo_inicia,tp.descripcion_tipo
,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente,de.destinatario
,of.codigo_oficina,of.descripcion_oficina,pr.codigo_personal,pr.nombre_personal
 from tramite.movimientos_doc md,tramite.tipo_documento tp,tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of,
tramite.personal pr,tramite.doc_entrada_origen eo,tramite.doc_entrada_destino ed
 where tp.codigo_tipo=dc.codigo_tipo and
dc.codigo_documento=md.codigo_documento and dc.codigo_documento=de.codigo_documento and md.codigo_documento=de.codigo_documento and
de.destino_documento=of.codigo_oficina and de.destinatario=pr.codigo_personal and md.codigo_documento=eo.codigo_documento
and md.codigo_documento=ed.codigo_documento and
md.codigo_oficina like '0003' and md.codigo_fondo like '01' 
  order by  md.fecha_movimiento desc, md.hora_movimiento
desc, md.codigo_documento desc



select md.oficina_deriva,of.descripcion_oficina,md.codigo_motivo,pe.nombre_personal,md.destinatario,
md.observa_movimiento,mt.descripcion_motivo from tramite.movimientos_doc md,tramite.oficinas of
,tramite.personal pe,tramite.motivo mt where md.oficina_deriva=of.codigo_oficina
 and md.destinatario=pe.codigo_personal and md.codigo_motivo=mt.codigo_motivo and md.correlativo_deriva='10'


 

select md.codigo_documento,md.fecha_movimiento,md.hora_movimiento,md.estado_movimiento,md.secuencia_movimiento,
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.codigo_inicia,tp.descripcion_tipo
,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente,de.destinatario
,of.codigo_oficina,of.descripcion_oficina,pr.codigo_personal,pr.nombre_personal,mo.descripcion_motivo
,dc.fecha_registro,dc.hora_registro,dc.folios_documento,dc.observa_documento
 from tramite.movimientos_doc md,tramite.tipo_documento tp,tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of,
tramite.personal pr,tramite.motivo mo 
 where tp.codigo_tipo=dc.codigo_tipo and
dc.codigo_documento=md.codigo_documento and dc.codigo_documento=de.codigo_documento and md.codigo_documento=de.codigo_documento and
de.destino_documento=of.codigo_oficina and de.destinatario=pr.codigo_personal and mo.codigo_motivo=md.codigo_motivo and
md.codigo_oficina = '0002' and md.codigo_fondo = '01' and
md.codigo_documento = '20081' and
md.secuencia_movimiento = '1' order by  md.fecha_movimiento desc,md.hora_movimiento
desc, md.codigo_documento desc



select md.codigo_documento,md.fecha_movimiento,md.hora_movimiento,md.estado_movimiento,md.secuencia_movimiento,
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.codigo_inicia,tp.descripcion_tipo
,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente,de.destinatario
,of.codigo_oficina,of.descripcion_oficina,pr.codigo_personal,pr.nombre_personal,mo.descripcion_motivo
,dc.fecha_registro,dc.hora_registro,dc.folios_documento,dc.observa_documento
 from tramite.movimientos_doc md left outer join tramite.documento dc ON dc.codigo_documento=md.codigo_documento,
      tramite.movimientos_doc md_a left outer join tramite.doc_entrada de ON md_a.codigo_documento=de.codigo_documento,
	tramite.documento dc_a left outer join  tramite.tipo_documento tp ON tp.codigo_tipo=dc_a.codigo_tipo,
	tramite.doc_entrada de_a left outer join tramite.oficinas of ON de_a.destino_documento=of.codigo_oficina,
	tramite.doc_entrada de_b  left outer join tramite.personal pr ON de_b.destinatario=pr.codigo_personal,
	tramite.movimientos_doc md_b left outer join tramite.motivo mo  ON mo.codigo_motivo=md_b.codigo_motivo,
	tramite.movimientos_doc md_c left outer join tramite.doc_entrada_origen eo  ON  md_c.codigo_documento=eo.codigo_documento,
	tramite.movimientos_doc md_d left outer join tramite.doc_entrada_destino ed  ON   md_d.codigo_documento=ed.codigo_documento
	where  dc.codigo_documento = md.codigo_documento
	and tp.codigo_tipo = dc.codigo_tipo and md.codigo_oficina='0002' and
	md.codigo_fondo ='01' and md.fecha_movimiento>= '18/08/2008' and md.fecha_movimiento <= '18/08/2008' 
	order by md.fecha_movimiento desc,md.hora_movimiento desc,
	md.codigo_documento desc,md.secuencia_movimiento desc 


select md.codigo_documento,md.fecha_movimiento,md.hora_movimiento,md.estado_movimiento,md.secuencia_movimiento,
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.codigo_inicia,tp.descripcion_tipo
,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente,de.destinatario
,of.codigo_oficina,of.descripcion_oficina,pr.codigo_personal,pr.nombre_personal
 from tramite.movimientos_doc md,tramite.tipo_documento tp,tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of,
tramite.personal pr,tramite.doc_entrada_origen eo,tramite.doc_entrada_destino ed
 where tp.codigo_tipo=dc.codigo_tipo and
dc.codigo_documento=md.codigo_documento and dc.codigo_documento=de.codigo_documento and md.codigo_documento=de.codigo_documento and
de.destino_documento=of.codigo_oficina and de.destinatario=pr.codigo_personal and md.codigo_documento=eo.codigo_documento
and md.codigo_documento=ed.codigo_documento and
md.codigo_oficina like '0002' and md.codigo_fondo like '01' 
  order by  md.fecha_movimiento desc, md.hora_movimiento
desc, md.codigo_documento desc


select md.codigo_documento,md.fecha_movimiento,md.hora_movimiento,md.estado_movimiento,md.secuencia_movimiento,
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.codigo_inicia,tp.descripcion_tipo
,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente,de.destinatario
,of.codigo_oficina,of.descripcion_oficina,pr.codigo_personal,pr.nombre_personal
 from tramite.movimientos_doc md,tramite.tipo_documento tp,tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of,
tramite.personal pr,tramite.doc_entrada_origen eo,tramite.doc_entrada_destino ed
 where tp.codigo_tipo=dc.codigo_tipo and
dc.codigo_documento=md.codigo_documento and dc.codigo_documento=de.codigo_documento and md.codigo_documento=de.codigo_documento and
de.destino_documento=of.codigo_oficina and de.destinatario=pr.codigo_personal and md.codigo_documento=eo.codigo_documento
and md.codigo_documento=ed.codigo_documento and
eo.codigo_oficina like '0001' and md.codigo_fondo like '01' 
  order by  md.fecha_movimiento desc, md.hora_movimiento
desc, md.codigo_documento desc


select md.codigo_documento,md.fecha_movimiento,md.hora_movimiento,md.estado_movimiento,md.secuencia_movimiento,
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.codigo_inicia,tp.descripcion_tipo
,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente,de.destinatario
,of.codigo_oficina,of.descripcion_oficina,pr.codigo_personal,pr.nombre_personal
 from tramite.movimientos_doc md,tramite.tipo_documento tp,tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of,
tramite.personal pr,tramite.doc_entrada_origen eo,tramite.doc_entrada_destino ed
 where tp.codigo_tipo=dc.codigo_tipo and
dc.codigo_documento=md.codigo_documento and dc.codigo_documento=de.codigo_documento and md.codigo_documento=de.codigo_documento and
de.destino_documento=of.codigo_oficina and de.destinatario=pr.codigo_personal and md.codigo_documento=eo.codigo_documento
and md.codigo_documento=ed.codigo_documento and
md.codigo_oficina like '0001' and md.codigo_fondo like '01' 
  order by  md.fecha_movimiento desc, md.hora_movimiento
desc, md.codigo_documento desc

/*si  existe motivo para la derivacion*/
select md.codigo_documento,md.fecha_movimiento,md.hora_movimiento,md.estado_movimiento,md.secuencia_movimiento,
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.codigo_inicia,tp.descripcion_tipo
,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente,de.destinatario
,of.codigo_oficina,of.descripcion_oficina,pr.codigo_personal,pr.nombre_personal,mo.descripcion_motivo
,dc.fecha_registro,dc.hora_registro,dc.folios_documento,dc.observa_documento
 from tramite.movimientos_doc md,tramite.tipo_documento tp,tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of,
tramite.personal pr,tramite.motivo mo 
 where tp.codigo_tipo=dc.codigo_tipo and
dc.codigo_documento=md.codigo_documento and dc.codigo_documento=de.codigo_documento and md.codigo_documento=de.codigo_documento and
de.destino_documento=of.codigo_oficina and de.destinatario=pr.codigo_personal and mo.codigo_motivo=md.codigo_motivo and
md.codigo_oficina = '0001' and md.codigo_fondo = '01' and
md.codigo_documento = '20081' and
md.secuencia_movimiento = '1' order by  md.fecha_movimiento desc,md.hora_movimiento
desc, md.codigo_documento desc


/*si no existe motivo para la derivacion*/
select md.codigo_documento,md.fecha_movimiento,md.hora_movimiento,md.estado_movimiento,md.secuencia_movimiento,
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.codigo_inicia,tp.descripcion_tipo
,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente,de.destinatario
,of.codigo_oficina,of.descripcion_oficina,pr.codigo_personal,pr.nombre_personal
,dc.fecha_registro,dc.hora_registro,dc.folios_documento,dc.observa_documento
 from tramite.movimientos_doc md,tramite.tipo_documento tp,tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of,
tramite.personal pr
 where tp.codigo_tipo=dc.codigo_tipo and
dc.codigo_documento=md.codigo_documento and dc.codigo_documento=de.codigo_documento and md.codigo_documento=de.codigo_documento and
de.destino_documento=of.codigo_oficina and de.destinatario=pr.codigo_personal  and
md.codigo_oficina = '0003' and md.codigo_fondo = '01' and
md.secuencia_movimiento = '1' order by  md.fecha_movimiento desc,md.hora_movimiento
desc, md.codigo_documento desc

/*si se registra un documento de entrada y se manda a secretaria */
select md.codigo_documento,md.fecha_movimiento,md.hora_movimiento,md.estado_movimiento,md.secuencia_movimiento,
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.codigo_inicia,tp.descripcion_tipo
,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente,de.destinatario
,of.codigo_oficina,of.descripcion_oficina,pr.codigo_personal,pr.nombre_personal
,dc.fecha_registro,dc.hora_registro,dc.folios_documento,dc.observa_documento
 from tramite.movimientos_doc md,tramite.tipo_documento tp,tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of,
tramite.personal pr
 where tp.codigo_tipo=dc.codigo_tipo and
dc.codigo_documento=md.codigo_documento and dc.codigo_documento=de.codigo_documento and md.codigo_documento=de.codigo_documento and
de.destino_documento=of.codigo_oficina and de.destinatario=pr.codigo_personal  and
md.oficina_deriva = '0002' and md.codigo_fondo = '01' and
md.codigo_documento = '20081' and
md.secuencia_movimiento = '1' order by  md.fecha_movimiento desc,md.hora_movimiento
desc, md.codigo_documento desc

select md.codigo_documento,md.fecha_movimiento,md.hora_movimiento,md.estado_movimiento,md.secuencia_movimiento,
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.codigo_inicia,tp.descripcion_tipo
,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente,de.destinatario
,of.codigo_oficina,of.descripcion_oficina,pr.codigo_personal,pr.nombre_personal,mo.descripcion_motivo
,dc.fecha_registro,dc.hora_registro,dc.folios_documento,dc.observa_documento
 from tramite.movimientos_doc md,tramite.tipo_documento tp,tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of,
tramite.personal pr,tramite.motivo mo left outer join tramite.movimientos_doc md_a on mo.codigo_motivo=md_a.codigo_motivo
 where tp.codigo_tipo=dc.codigo_tipo and
dc.codigo_documento=md.codigo_documento and dc.codigo_documento=de.codigo_documento and md.codigo_documento=de.codigo_documento and
de.destino_documento=of.codigo_oficina and de.destinatario=pr.codigo_personal  and
md.codigo_oficina = '0001' and md.codigo_fondo = '01' and
md.codigo_documento = '20081' and
md.secuencia_movimiento = '1' order by  md.fecha_movimiento desc,md.hora_movimiento
desc, md.codigo_documento desc


/************************seleccion frame central*********/

select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento,md.hora_movimiento, 
md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento,md.ultimo_usuario, 
md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.fecha_rpta,md.oficina_origen,md.oficina_deriva,    
tp.descripcion_tipo,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,dc.folios_documento, 
de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente, 
de.destinatario,of.codigo_oficina,of.descripcion_oficina,of_a.descripcion_oficina,pr.codigo_personal, 
pr.nombre_personal from tramite.movimientos_doc md,tramite.tipo_documento tp, 
tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of,tramite.oficinas of_a,tramite.personal pr  
where tp.codigo_tipo=dc.codigo_tipo and dc.codigo_documento=md.codigo_documento and  
dc.codigo_documento=de.codigo_documento and md.codigo_documento=de.codigo_documento and  
 de.destinatario=pr.codigo_personal and md.codigo_oficina=of.codigo_oficina and de.destino_documento=of_a.codigo_oficina and  
md.codigo_oficina like '14' and md.codigo_fondo like '01' 
 order by  md.fecha_movimiento desc, md.hora_movimiento desc, md.codigo_documento desc

/************************seleccion frame central*********/

/************************seleccion frame central*********/

select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento,md.hora_movimiento, 
md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento,md.ultimo_usuario, 
md.doc_deriva,md.observa_movimiento,md.codigo_motivo, 
tp.descripcion_tipo,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento, 
de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente, 
de.destinatario,of.codigo_oficina,of.descripcion_oficina,of_a.descripcion_oficina,pr.codigo_personal, 
pr.nombre_personal,di.origen_documento,di.destino_documento,di.desc_origen,di.remitente,di.destinatario
from tramite.movimientos_doc md,tramite.tipo_documento tp, 
tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of,tramite.oficinas of_a,tramite.personal pr
,tramite.doc_interno di  
where tp.codigo_tipo=dc.codigo_tipo and dc.codigo_documento=md.codigo_documento and  
dc.codigo_documento=de.codigo_documento and md.codigo_documento=de.codigo_documento and  
 de.destinatario=pr.codigo_personal and md.codigo_oficina=of.codigo_oficina and de.destino_documento=of_a.codigo_oficina and
dc.codigo_documento=di.codigo_documento and  md.codigo_documento=di.codigo_documento and di.destinatario=pr.codigo_personal and
di.destino_documento=of_a.codigo_oficina and
md.codigo_oficina like '0001' and md.codigo_fondo like '01' 
 order by  md.fecha_movimiento desc, md.hora_movimiento desc, md.codigo_documento desc


select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento,md.hora_movimiento, 
md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento,md.ultimo_usuario, 
md.doc_deriva,md.observa_movimiento,md.codigo_motivo, 
dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento, 
de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente, 
de.destinatario , 
di.origen_documento,di.destino_documento,di.desc_origen,di.tipo,di.remitente, 
di.destinatario 

from tramite.movimientos_doc md,tramite.documento dc,tramite.doc_entrada de,tramite.doc_interno di

where dc.codigo_documento=md.codigo_documento and  md.codigo_documento=de.codigo_documento and
md.codigo_documento=di.codigo_documento and
md.codigo_oficina like '0001' and md.codigo_fondo like '01' 
 order by  md.fecha_movimiento desc, md.hora_movimiento desc, md.codigo_documento desc


select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento,md.hora_movimiento, 
md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento,md.ultimo_usuario, 
md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.fecha_rpta,  
tp.descripcion_tipo,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento, 
de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente, 
de.destinatario,of.codigo_oficina,of.descripcion_oficina,of_a.descripcion_oficina,pr.codigo_personal, 
pr.nombre_personal from tramite.movimientos_doc md,tramite.tipo_documento tp, 
tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of,tramite.oficinas of_a,tramite.personal pr
,tramite.doc_entrada_origen dr,tramite.movimientos_doc md_a 
where tp.codigo_tipo=dc.codigo_tipo and dc.codigo_documento=md.codigo_documento and  
dc.codigo_documento=de.codigo_documento and md.codigo_documento=de.codigo_documento and  
 de.destinatario=pr.codigo_personal and md.codigo_oficina=of.codigo_oficina and de.destino_documento=of_a.codigo_oficina and 
dr.codigo_oficina=md_a.codigo_oficina and 
md.codigo_oficina like '0002' and md.codigo_fondo like '01' 
 order by  md.fecha_movimiento desc, md.hora_movimiento desc, md.codigo_documento desc
