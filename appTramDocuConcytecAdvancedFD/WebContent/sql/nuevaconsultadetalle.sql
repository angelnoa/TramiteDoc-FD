 SELECT movimientos_doc.codigo_documento,   
         oficinas_a.descripcion_oficina,   
         oficinas_b.descripcion_oficina,   
         tipo_documento.descripcion_tipo,   
         documento.numero_documento,   
         documento.naturaleza_documento,   
         documento.fecha_documento,   
         doc_salida_origen.codigo_oficina,   
         doc_salida_destino.codigo_institucion,   
         institucion_a.descripcion_institucion,   
         oficinas_c.descripcion_oficina,   
         institucion_b.descripcion_institucion,   
         oficinas_d.descripcion_oficina,   
         institucion_b.siglas_institucion,   
         oficinas_c.siglas_oficina,   
         oficinas_b.siglas_oficina,   
         movimientos_doc.fecha_movimiento,   
         movimientos_doc.hora_movimiento,   
         movimientos_doc.estado_movimiento,   
         motivo.descripcion_motivo,   
         documento.asunto_documento,   
         documento.folios_documento,   
         documento.observa_documento,   
         serie_documental.descripcion_serie,   
         expediente.descripcion_expediente,   
         documento.fecha_registro,   
         documento.hora_registro,   
         documento.ultima_modificacion,   
         movimientos_doc.codigo_inicia,   
         movimientos_doc.secuencia_movimiento,   
         doc_interno_origen.codigo_oficina,   
         movimientos_doc.ultimo_usuario,   
         personal_a.nombre_personal,   
         personal_b.nombre_personal,   
         personal_c.nombre_personal,   
         contactos_a.nombre_contacto,   
         contactos_b.nombre_contacto,   
         personal_d.nombre_personal,   
         tipo_documento.es_multiple,   
         movimientos_doc.observa_movimiento,   
         movimientos_doc.doc_deriva,   
         movimientos_doc.codigo_motivo  
    FROM  tramite.doc_salida_origen doc_salida_origen RIGHT OUTER JOIN tramite.documento documento ON doc_salida_origen.codigo_documento = documento.codigo_documento,
  tramite.doc_salida_destino RIGHT OUTER JOIN tramite.documento documento1 ON doc_salida_destino.codigo_documento = documento1.codigo_documento, 
 tramite.doc_interno_destino doc_interno_destino RIGHT OUTER JOIN tramite.documento documento2 ON doc_interno_destino.codigo_documento = documento2.codigo_documento,
  tramite.doc_interno_origen RIGHT OUTER JOIN tramite.documento documento3 ON doc_interno_origen.codigo_documento = documento3.codigo_documento, 
 tramite.oficinas oficinas_a RIGHT OUTER JOIN tramite.doc_interno_destino doc_interno_destino1 ON oficinas_a.codigo_oficina = doc_interno_destino1.codigo_oficina AND
 oficinas_a.codigo_fondo = doc_interno_destino1.codigo_fondo,  tramite.doc_interno_origen LEFT OUTER JOIN tramite.oficinas oficinas_b ON
 doc_interno_origen.codigo_fondo = oficinas_b.codigo_fondo AND doc_interno_origen.codigo_oficina = oficinas_b.codigo_oficina,
  tramite.doc_entrada RIGHT OUTER JOIN tramite.documento ON doc_entrada.codigo_documento = documento.codigo_documento, 
 tramite.motivo RIGHT OUTER JOIN tramite.movimientos_doc ON motivo.codigo_motivo = movimientos_doc.codigo_motivo, 
 tramite.expediente RIGHT OUTER JOIN tramite.movimientos_doc ON expediente.codigo_expediente = movimientos_doc.codigo_expediente AND
 expediente.codigo_fondo = movimientos_doc.codigo_fondo AND expediente.codigo_oficina = movimientos_doc.codigo_oficina AND
 expediente.codigo_serie = movimientos_doc.codigo_serie,  tramite.serie_documental RIGHT OUTER JOIN tramite.movimientos_doc ON
 serie_documental.codigo_fondo = movimientos_doc.codigo_fondo AND serie_documental.codigo_oficina = movimientos_doc.codigo_oficina AND
 serie_documental.codigo_serie = movimientos_doc.codigo_serie,  tramite.personal personal_a RIGHT OUTER JOIN tramite.doc_interno_origen ON
 personal_a.codigo_personal = doc_interno_origen.codigo_personal,  tramite.personal personal_b RIGHT OUTER JOIN tramite.doc_interno_destino ON
 personal_b.codigo_personal = doc_interno_destino.codigo_personal,  tramite.personal personal_c RIGHT OUTER JOIN tramite.doc_salida_origen ON
 personal_c.codigo_personal = doc_salida_origen.codigo_personal,  tramite.contactos contactos_a RIGHT OUTER JOIN tramite.doc_salida_destino ON
 contactos_a.codigo_contacto = doc_salida_destino.codigo_contacto,  tramite.contactos contactos_b RIGHT OUTER JOIN tramite.doc_entrada ON
 contactos_b.codigo_contacto = doc_entrada.remitente,  tramite.personal personal_d RIGHT OUTER JOIN tramite.doc_entrada ON
 personal_d.codigo_personal = doc_entrada.destinatario,  tramite.institucion institucion_a RIGHT OUTER JOIN tramite.doc_salida_destino ON
 institucion_a.codigo_institucion = doc_salida_destino.codigo_institucion,  tramite.oficinas oficinas_c RIGHT OUTER JOIN tramite.doc_salida_origen ON
 oficinas_c.codigo_oficina = doc_salida_origen.codigo_oficina AND oficinas_c.codigo_fondo = doc_salida_origen.codigo_fondo,
  tramite.institucion institucion_b RIGHT OUTER JOIN tramite.doc_entrada ON institucion_b.codigo_institucion = doc_entrada.origen_documento,
  tramite.oficinas oficinas_d RIGHT OUTER JOIN tramite.doc_entrada ON oficinas_d.codigo_oficina = doc_entrada.destino_documento AND
 oficinas_d.codigo_fondo = doc_entrada.fondo_destino,   
         tipo_documento  
   WHERE ( documento.codigo_documento = movimientos_doc.codigo_documento ) and  
         ( tipo_documento.codigo_tipo = documento.codigo_tipo ) and  
         ( ( movimientos_doc.codigo_oficina like '0001' ) AND  
         ( movimientos_doc.codigo_fondo like '01' ) AND  
         ( movimientos_doc.codigo_documento = '20082' ) AND  
         ( movimientos_doc.secuencia_movimiento = '1' ) )   
ORDER BY movimientos_doc.fecha_movimiento DESC,   
         movimientos_doc.hora_movimiento DESC,   
         movimientos_doc.codigo_documento DESC,   
         movimientos_doc.secuencia_movimiento DESC   
