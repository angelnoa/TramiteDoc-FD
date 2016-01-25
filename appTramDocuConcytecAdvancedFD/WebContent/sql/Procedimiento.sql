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
doc_entrada.destino_documento,movimientos_doc.abogadesignado,movimientos_doc.flag_atenabogado
from tramite.doc_salida_destino doc_salida_destino left outer join tramite.institucion institucion_a
on doc_salida_destino.codigo_institucion = institucion_a.codigo_institucion,
tramite.documento documento left outer join tramite.doc_interno_destino doc_interno_destino on documento.codigo_documento = doc_interno_destino.codigo_documento,
tramite.documento documento_a left outer join tramite.doc_interno_origen doc_interno_origen on documento_a.codigo_documento = doc_interno_origen.codigo_documento,
tramite.doc_interno_destino doc_interno_destino_a left outer join tramite.oficinas oficinas_a on doc_interno_destino_a.codigo_oficina= oficinas_a.codigo_oficina and doc_interno_destino_a.codigo_fondo = oficinas_a.codigo_fondo,
tramite.doc_interno_origen doc_interno_origen_a left outer join tramite.oficinas oficinas_b on doc_interno_origen_a.codigo_fondo= oficinas_b.codigo_fondo and 
doc_interno_origen_a.codigo_oficina = oficinas_b.codigo_oficina,
tramite.doc_entrada doc_entrada left outer join tramite.institucion institucion_b on doc_entrada.origen_documento= institucion_b.codigo_institucion,tramite.doc_entrada doc_entrada_a left outer join tramite.oficinas
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
like '0002' and movimientos_doc.codigo_fondo like '01' and movimientos_doc.fecha_movimiento
>= '2008-11-08' and movimientos_doc.fecha_movimiento <= '2008-11-08'
order by movimientos_doc.fecha_movimiento desc,movimientos_doc.hora_movimiento
desc,movimientos_doc.codigo_documento desc,movimientos_doc.secuencia_movimiento
desc 

select md.codigo_documento,md.fecha_movimiento,md.hora_movimiento,md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento,
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo
 from tramite.movimientos_doc md where md.codigo_oficina
like '0002' and md.codigo_fondo like '01' and  md.fecha_movimiento
>= '2008-11-07' and  md.fecha_movimiento <= '2008-11-08' order by  md.fecha_movimiento desc, md.hora_movimiento
desc, md.codigo_documento desc

