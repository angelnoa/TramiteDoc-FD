select direccion1 from subvencion.ubicacion ubi where
 ubi.idubicacion=(select persubi.idubicacion from subvencion.persona_ubicacion persubi where persubi.rol='02'
 and persubi.idpersona=subv.idpersona ) 


select 
	idplantillacontratocampos,
	idplantillacontrato,
	campoplantilla,
	tablabd,
	campobd,
	status,
	campomostrar,orden 	
	from subvencion.plantillacontratocampos where idplantillacontrato in ('05','09') and status='01' order by idplantillacontrato,orden asc

select 
	idplantillacontratocampos,
	idplantillacontrato,
	campoplantilla,
	tablabd,
	campobd,
	status,
	campomostrar,orden 	
	from subvencion.plantillacontratocampos where idplantillacontrato in ('09','10')
 and status='01' order by idplantillacontrato,orden asc


SELECT contrato.idcontrato, 	contrato.tipocontrato,
	(select tip.descripcion from subvencion.tipo tip where tip.tipo=contrato.tipocontrato and tip.entidad='tipocontrato' )as ciclo, 
	contrato.numerocontrato,
	contrato.fechainicio,
	calendario.numeroarmada,
	contrato.monto,
	contrato.idsubvencion 
	from subvencion.contrato,subvencion.calendario 
	where contrato.idcontrato=calendario.idcontrato and contrato.idsubvencion='9109' order by idsubvencion


INSERT INTO subvencion.plantillacontratocampos (idplantillacontratocampos, idplantillacontrato, campoplantilla, tablabd, campobd, status, usuariomodificacion, fechamodificacion, usuariocreacion, fechacreacion, campomostrar, orden) VALUES (167, 10,'semestre', NULL, NULL, '01', NULL, NULL, NULL, NULL, 'Semestre', 1);
INSERT INTO subvencion.plantillacontratocampos (idplantillacontratocampos, idplantillacontrato, campoplantilla, tablabd, campobd, status, usuariomodificacion, fechamodificacion, usuariocreacion, fechacreacion, campomostrar, orden) VALUES (168, 10,'ciclo', NULL, NULL, '01', NULL, NULL, NULL, NULL, 'Ciclo', 2);
INSERT INTO subvencion.plantillacontratocampos (idplantillacontratocampos, idplantillacontrato, campoplantilla, tablabd, campobd, status, usuariomodificacion, fechamodificacion, usuariocreacion, fechacreacion, campomostrar, orden) VALUES (169, 10,'universidadestudio', NULL, 'universidad', '01', NULL, NULL, NULL, NULL, 'Universidad', 3);
INSERT INTO subvencion.plantillacontratocampos (idplantillacontratocampos, idplantillacontrato, campoplantilla, tablabd, campobd, status, usuariomodificacion, fechamodificacion, usuariocreacion, fechacreacion, campomostrar, orden) VALUES (170, 10,'especialidad', NULL, 'especialidad', '01', NULL, NULL, NULL, NULL, 'Especialidad', 4);
INSERT INTO subvencion.plantillacontratocampos (idplantillacontratocampos, idplantillacontrato, campoplantilla, tablabd, campobd, status, usuariomodificacion, fechamodificacion, usuariocreacion, fechacreacion, campomostrar, orden) VALUES (171, 10,'titulopersonal', NULL, NULL, '01', NULL, NULL, NULL, NULL, 'Titulo Personal', 5);
INSERT INTO subvencion.plantillacontratocampos (idplantillacontratocampos, idplantillacontrato, campoplantilla, tablabd, campobd, status, usuariomodificacion, fechamodificacion, usuariocreacion, fechacreacion, campomostrar, orden) VALUES (172, 10,'nombres', NULL, 'nombre', '01', NULL, NULL, NULL, NULL, 'Nombres', 6);
INSERT INTO subvencion.plantillacontratocampos (idplantillacontratocampos, idplantillacontrato, campoplantilla, tablabd, campobd, status, usuariomodificacion, fechamodificacion, usuariocreacion, fechacreacion, campomostrar, orden) VALUES (173, 10,'apellidos', NULL, 'apellidos', '01', NULL, NULL, NULL, NULL, 'Apellidos', 7);
INSERT INTO subvencion.plantillacontratocampos (idplantillacontratocampos, idplantillacontrato, campoplantilla, tablabd, campobd, status, usuariomodificacion, fechamodificacion, usuariocreacion, fechacreacion, campomostrar, orden) VALUES (174, 10,'dni', NULL, 'documento', '01', NULL, NULL, NULL, NULL, 'Doc. Identidad', 8);
INSERT INTO subvencion.plantillacontratocampos (idplantillacontratocampos, idplantillacontrato, campoplantilla, tablabd, campobd, status, usuariomodificacion, fechamodificacion, usuariocreacion, fechacreacion, campomostrar, orden) VALUES (175, 10,'ciudadperma', NULL, 'ciudad1', '01', NULL, NULL, NULL, NULL, 'Ciudad', 9);
INSERT INTO subvencion.plantillacontratocampos (idplantillacontratocampos, idplantillacontrato, campoplantilla, tablabd, campobd, status, usuariomodificacion, fechamodificacion, usuariocreacion, fechacreacion, campomostrar, orden) VALUES (176, 10,'email', NULL, 'email', '01', NULL, NULL, NULL, NULL, 'Email', 10);
INSERT INTO subvencion.plantillacontratocampos (idplantillacontratocampos, idplantillacontrato, campoplantilla, tablabd, campobd, status, usuariomodificacion, fechamodificacion, usuariocreacion, fechacreacion, campomostrar, orden) VALUES (177, 10,'telefoperm', NULL, 'telefono', '01', NULL, NULL, NULL, NULL, 'Telefono', 11);
INSERT INTO subvencion.plantillacontratocampos (idplantillacontratocampos, idplantillacontrato, campoplantilla, tablabd, campobd, status, usuariomodificacion, fechamodificacion, usuariocreacion, fechacreacion, campomostrar, orden) VALUES (178, 10,'fecha', NULL, NULL, '00', NULL, NULL, NULL, NULL, 'Fecha', 16);
INSERT INTO subvencion.plantillacontratocampos (idplantillacontratocampos, idplantillacontrato, campoplantilla, tablabd, campobd, status, usuariomodificacion, fechamodificacion, usuariocreacion, fechacreacion, campomostrar, orden) VALUES (179, 10,'monto', NULL, 'monto', '01', NULL, NULL, NULL, NULL, 'Monto', 12);
INSERT INTO subvencion.plantillacontratocampos (idplantillacontratocampos, idplantillacontrato, campoplantilla, tablabd, campobd, status, usuariomodificacion, fechamodificacion, usuariocreacion, fechacreacion, campomostrar, orden) VALUES (180, 10,'montoletras', NULL, 'montoletras', '01', NULL, NULL, NULL, NULL, 'Monto en Letras', 13);
INSERT INTO subvencion.plantillacontratocampos (idplantillacontratocampos, idplantillacontrato, campoplantilla, tablabd, campobd, status, usuariomodificacion, fechamodificacion, usuariocreacion, fechacreacion, campomostrar, orden) VALUES (181, 10,'titulo', NULL, 'titulo', '01', NULL, NULL, NULL, NULL, 'Titulo Profesional', 14);

INSERT INTO subvencion.plantillacontratocampos (idplantillacontratocampos, idplantillacontrato, campoplantilla, tablabd, campobd, status, usuariomodificacion, fechamodificacion, usuariocreacion, fechacreacion, campomostrar, orden) VALUES (182, 10,'numcontrato', NULL, 'numerocontrato', '01', NULL, NULL, NULL, NULL, 'Num Contrato', 17);
INSERT INTO subvencion.plantillacontratocampos (idplantillacontratocampos, idplantillacontrato, campoplantilla, tablabd, campobd, status, usuariomodificacion, fechamodificacion, usuariocreacion, fechacreacion, campomostrar, orden) VALUES (183, 10,'md', NULL, NULL, '00', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO subvencion.plantillacontratocampos (idplantillacontratocampos, idplantillacontrato, campoplantilla, tablabd, campobd, status, usuariomodificacion, fechamodificacion, usuariocreacion, fechacreacion, campomostrar, orden) VALUES (184, 10,'direccioperma', NULL, 'direcciopermanente', '01', NULL, NULL, NULL, NULL, 'Direccion', 15);
INSERT INTO subvencion.plantillacontratocampos (idplantillacontratocampos, idplantillacontrato, campoplantilla, tablabd, campobd, status, usuariomodificacion, fechamodificacion, usuariocreacion, fechacreacion, campomostrar, orden) VALUES (185, 10,'historialcontrato', NULL, 'historialcontrato', '01', NULL, NULL, NULL, NULL, 'Contratos Anteriores', 16);
INSERT INTO subvencion.plantillacontratocampos (idplantillacontratocampos, idplantillacontrato, campoplantilla, tablabd, campobd, status, usuariomodificacion, fechamodificacion, usuariocreacion, fechacreacion, campomostrar, orden) VALUES (186, 10,'fecha', NULL, 'fecha', '01', NULL, NULL, NULL, NULL, 'Fecha', 18);
INSERT INTO subvencion.plantillacontratocampos (idplantillacontratocampos, idplantillacontrato, campoplantilla, tablabd, campobd, status, usuariomodificacion, fechamodificacion, usuariocreacion, fechacreacion, campomostrar, orden) VALUES (187, 10,'mencion', NULL, 'mencion', '00', NULL, NULL, NULL, NULL, 'Mencion', 4);
INSERT INTO subvencion.plantillacontratocampos (idplantillacontratocampos, idplantillacontrato, campoplantilla, tablabd, campobd, status, usuariomodificacion, fechamodificacion, usuariocreacion, fechacreacion, campomostrar, orden) VALUES (188, 10,'direcciotemp', NULL, 'direcciontemporal', '00', NULL, NULL, NULL, NULL, 'Direccion Temp', 12);
INSERT INTO subvencion.plantillacontratocampos (idplantillacontratocampos, idplantillacontrato, campoplantilla, tablabd, campobd, status, usuariomodificacion, fechamodificacion, usuariocreacion, fechacreacion, campomostrar, orden) VALUES (189, 10,'ciudadtemp', NULL, 'ciudad2', '00', NULL, NULL, NULL, NULL, 'Ciudad Temp', 13);
INSERT INTO subvencion.plantillacontratocampos (idplantillacontratocampos, idplantillacontrato, campoplantilla, tablabd, campobd, status, usuariomodificacion, fechamodificacion, usuariocreacion, fechacreacion, campomostrar, orden) VALUES (189, 10,'gradoobtener', NULL, 'gradoobtener', '00', NULL, NULL, NULL, NULL, 'Grado a Obtener', 18);




