Select c.id_categoria, c.nom_categoria, c.niv_categoria, c.cat_padre, c.num_orden, uxc.fla_mantenimiento,uxc.id_categoria,uxc.id_usuario
From presupuesto.usuarioxcategoria uxc, presupuesto.categoria c 
Where uxc.id_usuario = '1' and uxc.fla_acceso = 'A' and c.fla_estado='A' and
	  uxc.id_categoria = c.id_categoria and c.cat_padre = 0 
Order by num_orden

insert into presupuesto.categoria(id_categoria,cat_padre,id_usuario,nom_categoria,niv_categoria,num_orden,
fec_registro,fec_modificacion,fla_estado) values ('187','0','-1','Maestros','1','2',
'2008-10-28 00:00:00','2008-10-28 00:00:00','A'); 

insert into presupuesto.usuarioxcategoria(id_categoria,id_usuario,fla_acceso,fla_mantenimiento) values 
('187','1','A','A'); 

Select c.id_categoria, c.nom_categoria, c.niv_categoria, c.cat_padre, c.num_orden, uxc.fla_mantenimiento
From presupuesto.usuarioxcategoria uxc, presupuesto.categoria c 
Where uxc.id_usuario = '1' and uxc.fla_acceso = 'A' 
and uxc.id_categoria = c.id_categoria and cat_padre='187'
Order by c.nom_categoria

insert into presupuesto.categoria(id_categoria,cat_padre,id_usuario,nom_categoria,niv_categoria,num_orden,
fec_registro,fec_modificacion,fla_estado) values ('188','187','-1','Objetivo General','2','1',
'2008-10-28 00:00:00','2008-10-28 00:00:00','A'); 
insert into presupuesto.categoria(id_categoria,cat_padre,id_usuario,nom_categoria,niv_categoria,num_orden,
fec_registro,fec_modificacion,fla_estado) values ('189','187','-1','Area','2','2',
'2008-10-28 00:00:00','2008-10-28 00:00:00','A'); 
insert into presupuesto.categoria(id_categoria,cat_padre,id_usuario,nom_categoria,niv_categoria,num_orden,
fec_registro,fec_modificacion,fla_estado) values ('190','187','-1','Meta MEF','2','3',
'2008-10-28 00:00:00','2008-10-28 00:00:00','A'); 


insert into presupuesto.usuarioxcategoria(id_categoria,id_usuario,fla_acceso,fla_mantenimiento) values 
('188','1','A','A'); 
insert into presupuesto.usuarioxcategoria(id_categoria,id_usuario,fla_acceso,fla_mantenimiento) values 
('189','1','A','A'); 
insert into presupuesto.usuarioxcategoria(id_categoria,id_usuario,fla_acceso,fla_mantenimiento) values 
('190','1','A','A'); 




insert into presupuesto.categoria(id_categoria,cat_padre,id_usuario,nom_categoria,niv_categoria,num_orden,
fec_registro,fec_modificacion,fla_estado) values ('191','187','-1','Objetivo Especifico','2','4',
'2008-10-30 00:00:00','2008-10-30 00:00:00','A'); 



