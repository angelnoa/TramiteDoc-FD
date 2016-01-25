select * from presupuesto.estructura_funcional where
id_padre='0';
select * from presupuesto.estructura_funcional where
id_padre='46';
select * from presupuesto.estructura_funcional where
id_padre='47';
select * from presupuesto.estructura_funcional where
id_padre='48';
select * from presupuesto.estructura_funcional where
id_padre='49';
select * from presupuesto.estructura_funcional where
id_padre='50';

CREATE TABLE presupuesto.metamef
(
  id_metamef int8 NOT NULL DEFAULT (0)::bigint,
  id_funcion int8 NOT NULL,
id_programa int8 NOT NULL,
id_subprograma int8 NOT NULL,
id_actividad int8 NOT NULL,
id_componente int8 NOT NULL,
id_meta int8 NOT NULL,
id_area int8 NOT NULL,
  usr_registro varchar(25),
  usr_modificacion varchar(25),
  fec_registro timestamp,
  fec_modificacion timestamp,
  CONSTRAINT xpkmetamef PRIMARY KEY (id_metamef)
) 
WITH OIDS;
ALTER TABLE presupuesto.area OWNER TO postgres;

