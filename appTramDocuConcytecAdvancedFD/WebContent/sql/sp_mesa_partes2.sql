

select * from tramite.recorrido_doc where codigo_oficina='0002' and codigo_documento='20081' and secuencia_movimiento='2'

insert into tramite.recorrido_doc(id_correlativo, id_item, id_producto, cantidad, total_parcial)  values(id_correlativo, id_item, id_producto, cantidad, total_parcial) 


select * from tramite.movimientos_doc
select * from tramite.recorrido_doc
/***************************Frame central******************//

select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento, 
md.hora_movimiento,md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento, 
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.fecha_rpta,md.codigo_expediente,md.oficina_origen
,md.oficina_deriva,md.correlativo_deriva,md.destinatario,  
tp.descripcion_tipo,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,dc.numero_documento_ant, 
de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente, 
de.destinatario,of.codigo_oficina,of.descripcion_oficina,of_a.descripcion_oficina as descripcion_destino, 
pr.codigo_personal,pr.nombre_personal,pr_a.nombre_personal from tramite.movimientos_doc md, 
tramite.tipo_documento tp,tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of, 
tramite.oficinas of_a,tramite.personal pr,tramite.personal pr_a where tp.codigo_tipo=dc.codigo_tipo and 
 dc.codigo_documento=md.codigo_documento and dc.codigo_documento=de.codigo_documento and 
 md.codigo_documento=de.codigo_documento and de.destinatario=pr.codigo_personal and 
md.destinatario=pr_a.codigo_personal and 
 md.codigo_oficina=of.codigo_oficina and de.destino_documento=of_a.codigo_oficina and 
 md.codigo_oficina like '14'   and md.codigo_fondo like '01' order by 
  md.fecha_movimiento desc, md.hora_movimiento desc, md.codigo_documento desc

/***************************************************************/
select * from tramite.recorrido_doc where codigo_oficina='0002' and codigo_documento='20081' and secuencia_movimiento='2' and estado_movimiento='2'
/******recorrido nuevo*******/

select re.codigo_documento,re.codigo_oficina,re.fecha_movimiento,re.hora_movimiento, 
re.estado_movimiento,re.codigo_inicia,re.secuencia_movimiento,re.ultimo_usuario, 
re.doc_deriva,re.observa_movimiento,re.codigo_motivo,re.desc_origen,re.tipo,re.fecha_rpta, 
re.estado,tp.descripcion_tipo,dc.numero_documento,dc.naturaleza_documento, 
dc.asunto_documento,de.origen_documento,de.destino_documento,de.remitente,de.destinatario, 
of.codigo_oficina,of.descripcion_oficina as desc_oficina,of_a.descripcion_oficina, 
pr.codigo_personal,pr.nombre_personal from tramite.recorrido_doc re,tramite.tipo_documento tp, 
tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of,tramite.oficinas of_a, 
tramite.personal pr where tp.codigo_tipo=dc.codigo_tipo and 
 dc.codigo_documento=re.codigo_documento and dc.codigo_documento=de.codigo_documento and 
 re.codigo_documento=de.codigo_documento and de.destinatario=pr.codigo_personal and 
 re.codigo_oficina=of.codigo_oficina and de.destino_documento=of_a.codigo_oficina and 
 re.codigo_inicia ='20081' order by re.fecha_movimiento asc,re.hora_movimiento asc,re.oid asc

/****************recorrido***********/
select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento,
md.hora_movimiento,md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento, 
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.desc_origen, 
md.tipo,md.fecha_rpta from tramite.movimientos_doc  md left  outer join tramite.recorrido_doc re

  on md.codigo_oficina=re.codigo_oficina, tramite.recorrido_doc re_a

where   
  md.codigo_inicia =  '20081'  order by 
 md.codigo_documento asc,md.secuencia_movimiento

delete from tramite.recorrido_doc;
delete from tramite.movimientos_doc;
delete from tramite.documento;
delete from tramite.doc_entrada;
delete from tramite.doc_entrada_destino;
delete from tramite.doc_entrada_origen;

select(codigo_documento,
codigo_fondo,secuencia_movimiento,codigo_oficina,codigo_serie,codigo_motivo,
codigo_institucion,estado_movimiento,fecha_movimiento,fecha_consulta,hora_movimiento,
observa_movimiento,codigo_inicia,ultimo_usuario,fondo_deriva,
oficina_deriva,destinatario,correlativo_deriva,fecha_rpta,codigo_expediente,oficina_origen,estado as N) from tramite.recorrido_doc where
 codigo_oficina='0002' and codigo_documento='20081' and secuencia_movimiento='2'


/******Busqueda por naturaleza del documento*******/

select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento,md.hora_movimiento, 
md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento,md.ultimo_usuario, 
md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.fecha_rpta,md.oficina_origen,md.oficina_deriva,    
tp.descripcion_tipo,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento, 
de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente, 
de.destinatario,of.codigo_oficina,of.descripcion_oficina,of_a.descripcion_oficina,pr.codigo_personal, 
pr.nombre_personal from tramite.movimientos_doc md,tramite.tipo_documento tp, 
tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of,tramite.oficinas of_a,tramite.personal pr  
where tp.codigo_tipo=dc.codigo_tipo and dc.codigo_documento=md.codigo_documento and  
dc.codigo_documento=de.codigo_documento and md.codigo_documento=de.codigo_documento and  
 de.destinatario=pr.codigo_personal and md.codigo_oficina=of.codigo_oficina and de.destino_documento=of_a.codigo_oficina and  
dc.naturaleza_documento = 'I' and md.fecha_movimiento>= '22/09/2008'  and md.fecha_movimiento <= '23/09/2008'
order by  md.fecha_movimiento desc, md.hora_movimiento desc, md.codigo_documento desc


select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento, 
md.hora_movimiento,md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento, 
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.fecha_rpta,md.codigo_expediente,md.oficina_origen,md.oficina_deriva, 
tp.descripcion_tipo,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,dc.numero_documento_ant, 
de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente, 
de.destinatario,of.codigo_oficina,of.descripcion_oficina,of_a.descripcion_oficina as descripcion_destino, 
pr.codigo_personal,pr.nombre_personal from tramite.movimientos_doc md, 
tramite.tipo_documento tp,tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of, 
tramite.oficinas of_a,tramite.personal pr where tp.codigo_tipo=dc.codigo_tipo and 
 dc.codigo_documento=md.codigo_documento and dc.codigo_documento=de.codigo_documento and 
 md.codigo_documento=de.codigo_documento and de.destinatario=pr.codigo_personal and 
 md.codigo_oficina=of.codigo_oficina and de.destino_documento=of_a.codigo_oficina and 
dc.naturaleza_documento = 'W' and md.fecha_movimiento>= '03/09/2008'  and md.fecha_movimiento <= '10/09/2008' 
and md.codigo_oficina like '0002' 
order by  md.fecha_movimiento desc, md.hora_movimiento desc, md.codigo_documento desc

/******Busqueda por tipo de documento*******/

select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento, 
md.hora_movimiento,md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento, 
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.fecha_rpta,md.codigo_expediente,md.oficina_origen,md.oficina_deriva, 
tp.descripcion_tipo,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,dc.numero_documento_ant, 
de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente, 
de.destinatario,of.codigo_oficina,of.descripcion_oficina,of_a.descripcion_oficina as descripcion_destino, 
pr.codigo_personal,pr.nombre_personal from tramite.movimientos_doc md, 
tramite.tipo_documento tp,tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of, 
tramite.oficinas of_a,tramite.personal pr where tp.codigo_tipo=dc.codigo_tipo and 
 dc.codigo_documento=md.codigo_documento and dc.codigo_documento=de.codigo_documento and 
 md.codigo_documento=de.codigo_documento and de.destinatario=pr.codigo_personal and 
 md.codigo_oficina=of.codigo_oficina and de.destino_documento=of_a.codigo_oficina and  
dc.codigo_tipo = '04' 
order by  md.fecha_movimiento desc, md.hora_movimiento desc, md.codigo_documento desc
/******Busqueda por todos*******/

select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento, 
md.hora_movimiento,md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento, 
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.fecha_rpta,md.codigo_expediente,md.oficina_origen,md.oficina_deriva, 
tp.descripcion_tipo,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,dc.numero_documento_ant, 
de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente, 
de.destinatario,of.codigo_oficina,of.descripcion_oficina,of_a.descripcion_oficina as descripcion_destino, 
pr.codigo_personal,pr.nombre_personal from tramite.movimientos_doc md, 
tramite.tipo_documento tp,tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of, 
tramite.oficinas of_a,tramite.personal pr where tp.codigo_tipo=dc.codigo_tipo and 
 dc.codigo_documento=md.codigo_documento and dc.codigo_documento=de.codigo_documento and 
 md.codigo_documento=de.codigo_documento and de.destinatario=pr.codigo_personal and 
 md.codigo_oficina=of.codigo_oficina and de.destino_documento=of_a.codigo_oficina 
order by  md.fecha_movimiento desc, md.hora_movimiento desc, md.codigo_documento desc
/******Busqueda por Numero de registro o numero de expediente Filtro*******/

select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento, 
md.hora_movimiento,md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento, 
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.fecha_rpta,md.codigo_expediente,md.oficina_origen,md.oficina_deriva, 
tp.descripcion_tipo,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,dc.numero_documento_ant, 
de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente, 
de.destinatario,of.codigo_oficina,of.descripcion_oficina,of_a.descripcion_oficina as descripcion_destino, 
pr.codigo_personal,pr.nombre_personal from tramite.movimientos_doc md, 
tramite.tipo_documento tp,tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of, 
tramite.oficinas of_a,tramite.personal pr where tp.codigo_tipo=dc.codigo_tipo and 
 dc.codigo_documento=md.codigo_documento and dc.codigo_documento=de.codigo_documento and 
 md.codigo_documento=de.codigo_documento and de.destinatario=pr.codigo_personal and 
 md.codigo_oficina=of.codigo_oficina and de.destino_documento=of_a.codigo_oficina and   
dc.codigo_documento = '20084' and md.fecha_movimiento>= '03/09/2008'  and md.fecha_movimiento <= '05/09/2008'
order by  md.fecha_movimiento desc, md.hora_movimiento desc, md.codigo_documento desc

select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento, 
md.hora_movimiento,md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento, 
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.fecha_rpta,md.codigo_expediente,md.oficina_origen,md.oficina_deriva, 
tp.descripcion_tipo,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,dc.numero_documento_ant, 
de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente, 
de.destinatario,of.codigo_oficina,of.descripcion_oficina,of_a.descripcion_oficina as descripcion_destino, 
pr.codigo_personal,pr.nombre_personal from tramite.movimientos_doc md, 
tramite.tipo_documento tp,tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of, 
tramite.oficinas of_a,tramite.personal pr where tp.codigo_tipo=dc.codigo_tipo and 
 dc.codigo_documento=md.codigo_documento and dc.codigo_documento=de.codigo_documento and 
 md.codigo_documento=de.codigo_documento and de.destinatario=pr.codigo_personal and 
 md.codigo_oficina=of.codigo_oficina and de.destino_documento=of_a.codigo_oficina and  
md.codigo_expediente like '6' 
order by  md.fecha_movimiento desc, md.hora_movimiento desc, md.codigo_documento desc
/******Busqueda por estado de documento*******/

select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento,md.hora_movimiento, 
md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento,md.ultimo_usuario, 
md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.fecha_rpta,md.oficina_origen,md.oficina_deriva,    
tp.descripcion_tipo,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,dc.codigo_tipo,  
de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente, 
de.destinatario,of.codigo_oficina,of.descripcion_oficina,of_a.descripcion_oficina,pr.codigo_personal, 
pr.nombre_personal from tramite.movimientos_doc md,tramite.tipo_documento tp, 
tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of,tramite.oficinas of_a,tramite.personal pr  
where tp.codigo_tipo=dc.codigo_tipo and dc.codigo_documento=md.codigo_documento and  
dc.codigo_documento=de.codigo_documento and md.codigo_documento=de.codigo_documento and  
 de.destinatario=pr.codigo_personal and md.codigo_oficina=of.codigo_oficina and de.destino_documento=of_a.codigo_oficina and  
md.estado_movimiento = '7' 



/******Busqueda por Naturaleza y tipo  de documento*******/

select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento, 
md.hora_movimiento,md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento, 
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.fecha_rpta,md.codigo_expediente,md.oficina_origen,md.oficina_deriva, 
tp.descripcion_tipo,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,dc.numero_documento_ant, 
de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente, 
de.destinatario,of.codigo_oficina,of.descripcion_oficina,of_a.descripcion_oficina as descripcion_destino, 
pr.codigo_personal,pr.nombre_personal from tramite.movimientos_doc md, 
tramite.tipo_documento tp,tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of, 
tramite.oficinas of_a,tramite.personal pr where tp.codigo_tipo=dc.codigo_tipo and 
 dc.codigo_documento=md.codigo_documento and dc.codigo_documento=de.codigo_documento and 
 md.codigo_documento=de.codigo_documento and de.destinatario=pr.codigo_personal and 
 md.codigo_oficina=of.codigo_oficina and de.destino_documento=of_a.codigo_oficina and 
de.desc_origen like 'TELEFONICA'  
 and md.codigo_oficina = '3'
order by  md.fecha_movimiento desc, md.hora_movimiento desc, md.codigo_documento desc

/******Busqueda por Naturaleza y numero de registro*******/

select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento, 
md.hora_movimiento,md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento, 
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.fecha_rpta,md.codigo_expediente,md.oficina_origen,md.oficina_deriva, 
tp.descripcion_tipo,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,dc.numero_documento_ant, 
de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente, 
de.destinatario,of.codigo_oficina,of.descripcion_oficina,of_a.descripcion_oficina as descripcion_destino, 
pr.codigo_personal,pr.nombre_personal from tramite.movimientos_doc md, 
tramite.tipo_documento tp,tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of, 
tramite.oficinas of_a,tramite.personal pr where tp.codigo_tipo=dc.codigo_tipo and 
 dc.codigo_documento=md.codigo_documento and dc.codigo_documento=de.codigo_documento and 
 md.codigo_documento=de.codigo_documento and de.destinatario=pr.codigo_personal and 
 md.codigo_oficina=of.codigo_oficina and de.destino_documento=of_a.codigo_oficina and 
dc.naturaleza_documento = 'E' and md.fecha_movimiento>= '03/09/2008'  and md.fecha_movimiento <= '09/09/2008' 
and md.codigo_documento = '20081' and md.codigo_oficina = '0001'
order by  md.fecha_movimiento desc, md.hora_movimiento desc, md.codigo_documento desc

/******Busqueda por Naturaleza y numero de expediente*******/

select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento, 
md.hora_movimiento,md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento, 
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.fecha_rpta,md.codigo_expediente,md.oficina_origen,md.oficina_deriva, 
tp.descripcion_tipo,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,dc.numero_documento_ant, 
de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente, 
de.destinatario,of.codigo_oficina,of.descripcion_oficina,of_a.descripcion_oficina as descripcion_destino, 
pr.codigo_personal,pr.nombre_personal from tramite.movimientos_doc md, 
tramite.tipo_documento tp,tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of, 
tramite.oficinas of_a,tramite.personal pr where tp.codigo_tipo=dc.codigo_tipo and 
 dc.codigo_documento=md.codigo_documento and dc.codigo_documento=de.codigo_documento and 
 md.codigo_documento=de.codigo_documento and de.destinatario=pr.codigo_personal and 
 md.codigo_oficina=of.codigo_oficina and de.destino_documento=of_a.codigo_oficina and 
dc.naturaleza_documento = 'E' and md.fecha_movimiento>= '03/09/2008'  and md.fecha_movimiento <= '09/09/2008' 
and md.codigo_expediente = '2' and md.codigo_oficina = '0002'
order by  md.fecha_movimiento desc, md.hora_movimiento desc, md.codigo_documento desc

/******Busqueda por Naturaleza y estado*******/

select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento, 
md.hora_movimiento,md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento, 
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.fecha_rpta,md.codigo_expediente,md.oficina_origen,md.oficina_deriva, 
tp.descripcion_tipo,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,dc.numero_documento_ant, 
de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente, 
de.destinatario,of.codigo_oficina,of.descripcion_oficina,of_a.descripcion_oficina as descripcion_destino, 
pr.codigo_personal,pr.nombre_personal from tramite.movimientos_doc md, 
tramite.tipo_documento tp,tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of, 
tramite.oficinas of_a,tramite.personal pr where tp.codigo_tipo=dc.codigo_tipo and 
 dc.codigo_documento=md.codigo_documento and dc.codigo_documento=de.codigo_documento and 
 md.codigo_documento=de.codigo_documento and de.destinatario=pr.codigo_personal and 
 md.codigo_oficina=of.codigo_oficina and de.destino_documento=of_a.codigo_oficina and 
dc.naturaleza_documento = 'E' and md.fecha_movimiento>= '03/09/2008'  and md.fecha_movimiento <= '09/09/2008' 
and md.estado_movimiento = '2' and md.codigo_oficina = '0001'
order by  md.fecha_movimiento desc, md.hora_movimiento desc, md.codigo_documento desc

/******Busqueda por tipo de documento y numero de registro*******/

select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento, 
md.hora_movimiento,md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento, 
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.fecha_rpta,md.codigo_expediente,md.oficina_origen,md.oficina_deriva, 
tp.descripcion_tipo,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,dc.numero_documento_ant, 
de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente, 
de.destinatario,of.codigo_oficina,of.descripcion_oficina,of_a.descripcion_oficina as descripcion_destino, 
pr.codigo_personal,pr.nombre_personal from tramite.movimientos_doc md, 
tramite.tipo_documento tp,tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of, 
tramite.oficinas of_a,tramite.personal pr where tp.codigo_tipo=dc.codigo_tipo and 
 dc.codigo_documento=md.codigo_documento and dc.codigo_documento=de.codigo_documento and 
 md.codigo_documento=de.codigo_documento and de.destinatario=pr.codigo_personal and 
 md.codigo_oficina=of.codigo_oficina and de.destino_documento=of_a.codigo_oficina and  
dc.codigo_tipo = '05' and md.fecha_movimiento>= '03/09/2008'  and md.fecha_movimiento <= '09/09/2008' 
and md.codigo_documento = '20082' and md.codigo_oficina = '0001'
order by  md.fecha_movimiento desc, md.hora_movimiento desc, md.codigo_documento desc

/******Busqueda por tipo de documento y numero de expediente*******/

select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento, 
md.hora_movimiento,md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento, 
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.fecha_rpta,md.codigo_expediente,md.oficina_origen,md.oficina_deriva, 
tp.descripcion_tipo,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,dc.numero_documento_ant, 
de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente, 
de.destinatario,of.codigo_oficina,of.descripcion_oficina,of_a.descripcion_oficina as descripcion_destino, 
pr.codigo_personal,pr.nombre_personal from tramite.movimientos_doc md, 
tramite.tipo_documento tp,tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of, 
tramite.oficinas of_a,tramite.personal pr where tp.codigo_tipo=dc.codigo_tipo and 
 dc.codigo_documento=md.codigo_documento and dc.codigo_documento=de.codigo_documento and 
 md.codigo_documento=de.codigo_documento and de.destinatario=pr.codigo_personal and 
 md.codigo_oficina=of.codigo_oficina and de.destino_documento=of_a.codigo_oficina and  
dc.codigo_tipo = '05' and md.fecha_movimiento>= '03/09/2008'  and md.fecha_movimiento <= '09/09/2008' 
and md.codigo_expediente = '2' and md.codigo_oficina = '0001'
order by  md.fecha_movimiento desc, md.hora_movimiento desc, md.codigo_documento desc

/******Busqueda por tipo de documento y estado*******/

select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento, 
md.hora_movimiento,md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento, 
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.fecha_rpta,md.codigo_expediente,md.oficina_origen,md.oficina_deriva, 
tp.descripcion_tipo,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,dc.numero_documento_ant, 
de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente, 
de.destinatario,of.codigo_oficina,of.descripcion_oficina,of_a.descripcion_oficina as descripcion_destino, 
pr.codigo_personal,pr.nombre_personal from tramite.movimientos_doc md, 
tramite.tipo_documento tp,tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of, 
tramite.oficinas of_a,tramite.personal pr where tp.codigo_tipo=dc.codigo_tipo and 
 dc.codigo_documento=md.codigo_documento and dc.codigo_documento=de.codigo_documento and 
 md.codigo_documento=de.codigo_documento and de.destinatario=pr.codigo_personal and 
 md.codigo_oficina=of.codigo_oficina and de.destino_documento=of_a.codigo_oficina and  
dc.codigo_tipo = '05' and md.fecha_movimiento>= '03/09/2008'  and md.fecha_movimiento <= '09/09/2008' 
and md.estado_movimiento = '2' and md.codigo_oficina = '0002'
order by  md.fecha_movimiento desc, md.hora_movimiento desc, md.codigo_documento desc

/******Busqueda por Numero de registro y numero de expediente *******/

select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento, 
md.hora_movimiento,md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento, 
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.fecha_rpta,md.codigo_expediente,md.oficina_origen,md.oficina_deriva, 
tp.descripcion_tipo,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,dc.numero_documento_ant, 
de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente, 
de.destinatario,of.codigo_oficina,of.descripcion_oficina,of_a.descripcion_oficina as descripcion_destino, 
pr.codigo_personal,pr.nombre_personal from tramite.movimientos_doc md, 
tramite.tipo_documento tp,tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of, 
tramite.oficinas of_a,tramite.personal pr where tp.codigo_tipo=dc.codigo_tipo and 
 dc.codigo_documento=md.codigo_documento and dc.codigo_documento=de.codigo_documento and 
 md.codigo_documento=de.codigo_documento and de.destinatario=pr.codigo_personal and 
 md.codigo_oficina=of.codigo_oficina and de.destino_documento=of_a.codigo_oficina and   
dc.codigo_documento like '20082' and md.codigo_oficina = '0002' and md.codigo_expediente = '2' 
and md.fecha_movimiento>= '03/09/2008'  and md.fecha_movimiento <= '09/09/2008'
order by  md.fecha_movimiento desc, md.hora_movimiento desc, md.codigo_documento desc

/******Busqueda por Numero de registro y estado *******/

select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento, 
md.hora_movimiento,md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento, 
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.fecha_rpta,md.codigo_expediente,md.oficina_origen,md.oficina_deriva, 
tp.descripcion_tipo,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,dc.numero_documento_ant, 
de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente, 
de.destinatario,of.codigo_oficina,of.descripcion_oficina,of_a.descripcion_oficina as descripcion_destino, 
pr.codigo_personal,pr.nombre_personal from tramite.movimientos_doc md, 
tramite.tipo_documento tp,tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of, 
tramite.oficinas of_a,tramite.personal pr where tp.codigo_tipo=dc.codigo_tipo and 
 dc.codigo_documento=md.codigo_documento and dc.codigo_documento=de.codigo_documento and 
 md.codigo_documento=de.codigo_documento and de.destinatario=pr.codigo_personal and 
 md.codigo_oficina=of.codigo_oficina and de.destino_documento=of_a.codigo_oficina and   
dc.codigo_documento like '20082' and md.codigo_oficina = '0002' and md.estado_movimiento = '3' 
and md.fecha_movimiento>= '03/09/2008'  and md.fecha_movimiento <= '09/09/2008'
order by  md.fecha_movimiento desc, md.hora_movimiento desc, md.codigo_documento desc

/******Busqueda por Numero de expediente y estado *******/
select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento, 
md.hora_movimiento,md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento, 
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.fecha_rpta,md.codigo_expediente,md.oficina_origen,md.oficina_deriva, 
tp.descripcion_tipo,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,dc.numero_documento_ant, 
de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente, 
de.destinatario,of.codigo_oficina,of.descripcion_oficina,of_a.descripcion_oficina as descripcion_destino, 
pr.codigo_personal,pr.nombre_personal from tramite.movimientos_doc md, 
tramite.tipo_documento tp,tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of, 
tramite.oficinas of_a,tramite.personal pr where tp.codigo_tipo=dc.codigo_tipo and 
 dc.codigo_documento=md.codigo_documento and dc.codigo_documento=de.codigo_documento and 
 md.codigo_documento=de.codigo_documento and de.destinatario=pr.codigo_personal and 
 md.codigo_oficina=of.codigo_oficina and de.destino_documento=of_a.codigo_oficina and  
md.codigo_expediente like '2' and md.codigo_oficina = '0002' and md.estado_movimiento = '3' 
and md.fecha_movimiento>= '03/09/2008'  and md.fecha_movimiento <= '09/09/2008'
order by  md.fecha_movimiento desc, md.hora_movimiento desc, md.codigo_documento desc

/*******************Rango de codigos***************/

SELECT md.codigo_documento  
    FROM tramite.movimientos_doc md,   
         tramite.documento dc  
   WHERE  dc.codigo_documento = md.codigo_documento  and  
          md.codigo_oficina like '0001'  AND  
          md.codigo_fondo like '01'  AND  
          md.fecha_movimiento = '10/09/2008'  AND  
          dc.naturaleza_documento = 'E'   

/**************oficinas usuarios**********/

SELECT pe.nombre_personal,pe.codigo_personal,of.codigo_oficina,of.descripcion_oficina FROM 
tramite.oficinas of,tramite.personal pe WHERE pe.codigo_oficina =of.codigo_oficina AND  
          pe.c_usuario = 'alex'   


select count(codigo_documento) as c_valor from tramite.movimientos_doc where codigo_documento  = '20081' and correlativo_deriva='212'


select pj.codigo_institucion,pj.descripcion_institucion,pj.tipo,pn.codigo_persona,pn.nombre_persona,pn.tipo
from tramite.per_juridica pj CROSS JOIN   tramite.per_natural pn WHERE  pj.descripcion_institucion LIKE 'Cadena' and 
pn.nombre_persona LIKE 'Cadena'


SELECT *
FROM   tramite.per_juridica pj, tramite.per_natural pn


CREATE TABLE tramite.persona
(
  codigo_persona int4 NOT NULL,
  nombre_persona char(80),
  direccion_persona char(80),
  dni char(30),
  ruc char(30),
  tipo char(1)
) 
WITHOUT OIDS;
ALTER TABLE tramite.per_natural OWNER TO postgres;

insert into tramite.persona(codigo_persona, nombre_persona,tipo) values('1','SIGNUS','J');

insert into tramite.persona(codigo_persona, nombre_persona,tipo) values('2','MINISTERIO DE JUSTICIA','J');
insert into tramite.persona(codigo_persona, nombre_persona,tipo) values('3','MINISTERIO DE EDUCACION','J');
insert into tramite.persona(codigo_persona, nombre_persona,tipo) values('4','GRAÑA Y MONTERO PETROLERA','J');
insert into tramite.persona(codigo_persona, nombre_persona,tipo) values('5','AMERICO SUASNABAR','N');
insert into tramite.persona(codigo_persona, nombre_persona,tipo) values('6','MARIO RODRIGUEZ','N');

insert into tramite.cargo(codigo_cargo, nombrecargo) values('12','ENCARGADO DE MESA DE PARTES');
insert into tramite.cargo(codigo_cargo, nombrecargo) values('13','SECRETARIO');



select us.usuario,us.nombres,us.apellidos,us.email,pe.codigo_personal,
  pe.c_usuario,pe.codigo_oficina,pe.cargo_personal,ca.codigo_cargo,ca.nombrecargo,of.codigo_oficina,of.nombre_corto from tramite.usuarios us,
      tramite.personal pe,tramite.cargo ca,tramite.oficinas of where us.usuario=pe.c_usuario and 
       pe.cargo_personal=ca.codigo_cargo and of.codigo_oficina=pe.codigo_oficina order by codigo_personal asc


CREATE TABLE tramite.solicitud
(
  codigo_solicitud int4 NOT NULL,
  nombre_solicitud varchar(50),
  estado char(1)
) 
WITH OIDS;
ALTER TABLE tramite.solicitud OWNER TO postgres;


CREATE TABLE tramite.doc_entrada
(
  codigo_documento int8 NOT NULL,
  destinatario int4,
  fondo_destino char(2),
  remitente char(6),
  destino_documento int4,
  origen_documento int4,
  usumod char(10),
  fecmod char(50),
  medio char(2),
  hora char(8),
  firmadopor varchar(30),
  codigo_solicitud int4,
  desc_origen varchar(80),
  tipo char(20)
) 
WITH OIDS;
ALTER TABLE tramite.doc_entrada OWNER TO postgres;

select count(codigo_documento) as c_valor from tramite.recorrido_doc where codigo_documento  =
'20081' and codigo_oficina  = 
'5' and secuencia_movimiento  =
'4'

/******Busqueda por usuario*******/

select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento, 
md.hora_movimiento,md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento, 
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.fecha_rpta,md.codigo_expediente,md.oficina_origen,md.oficina_deriva, 
tp.descripcion_tipo,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,dc.numero_documento_ant, 
de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente, 
de.destinatario,of.codigo_oficina,of.descripcion_oficina,of_a.descripcion_oficina as descripcion_destino, 
pr.codigo_personal,pr.nombre_personal from tramite.movimientos_doc md, 
tramite.tipo_documento tp,tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of, 
tramite.oficinas of_a,tramite.personal pr where tp.codigo_tipo=dc.codigo_tipo and 
 dc.codigo_documento=md.codigo_documento and dc.codigo_documento=de.codigo_documento and 
 md.codigo_documento=de.codigo_documento and de.destinatario=pr.codigo_personal and 
 md.codigo_oficina=of.codigo_oficina and de.destino_documento=of_a.codigo_oficina and   
md.ultimo_usuario = 'rrodriguez' 
order by  md.fecha_movimiento desc, md.hora_movimiento desc, md.codigo_documento desc

/******Busqueda por usuario,fecha*******/

select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento, 
md.hora_movimiento,md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento, 
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.fecha_rpta,md.codigo_expediente,md.oficina_origen,md.oficina_deriva, 
tp.descripcion_tipo,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,dc.numero_documento_ant, 
de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente, 
de.destinatario,of.codigo_oficina,of.descripcion_oficina,of_a.descripcion_oficina as descripcion_destino, 
pr.codigo_personal,pr.nombre_personal from tramite.movimientos_doc md, 
tramite.tipo_documento tp,tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of, 
tramite.oficinas of_a,tramite.personal pr where tp.codigo_tipo=dc.codigo_tipo and 
 dc.codigo_documento=md.codigo_documento and dc.codigo_documento=de.codigo_documento and 
 md.codigo_documento=de.codigo_documento and de.destinatario=pr.codigo_personal and 
 md.codigo_oficina=of.codigo_oficina and de.destino_documento=of_a.codigo_oficina and   
md.ultimo_usuario = 'rrodriguez' and md.fecha_movimiento like '24/09/2008' 
order by  md.fecha_movimiento desc, md.hora_movimiento desc, md.codigo_documento desc

/******Busqueda por usuario,oficina*******/

select md.codigo_documento,md.codigo_oficina,md.fecha_movimiento, 
md.hora_movimiento,md.estado_movimiento,md.codigo_inicia,md.secuencia_movimiento, 
md.ultimo_usuario,md.doc_deriva,md.observa_movimiento,md.codigo_motivo,md.fecha_rpta,md.codigo_expediente,md.oficina_origen,md.oficina_deriva, 
tp.descripcion_tipo,dc.numero_documento,dc.naturaleza_documento,dc.asunto_documento,dc.numero_documento_ant, 
de.origen_documento,de.destino_documento,de.desc_origen,de.tipo,de.remitente, 
de.destinatario,of.codigo_oficina,of.descripcion_oficina,of_a.descripcion_oficina as descripcion_destino, 
pr.codigo_personal,pr.nombre_personal from tramite.movimientos_doc md, 
tramite.tipo_documento tp,tramite.documento dc,tramite.doc_entrada de,tramite.oficinas of, 
tramite.oficinas of_a,tramite.personal pr where tp.codigo_tipo=dc.codigo_tipo and 
 dc.codigo_documento=md.codigo_documento and dc.codigo_documento=de.codigo_documento and 
 md.codigo_documento=de.codigo_documento and de.destinatario=pr.codigo_personal and 
 md.codigo_oficina=of.codigo_oficina and de.destino_documento=of_a.codigo_oficina and   
md.ultimo_usuario = 'rrodriguez' and md.codigo_oficina = '14' 
order by  md.fecha_movimiento desc, md.hora_movimiento desc, md.codigo_documento desc



