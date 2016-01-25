select mf.id_metamef,mf.id_meta,pa.id_planarea,pa.id_metamef,pa.obj_planarea,
re.id_resultado,re.des_resultado,re.id_planarea
,ac.id_actividad,ac.id_planarea,ac.id_resultado,ac.nom_actividad,ac.id_area,ac.num_actividad  
from presupuesto.plan_area pa,presupuesto.resultado re, presupuesto.actividad ac,presupuesto.metamef mf 
where pa.id_planarea=re.id_planarea  
and ac.id_resultado=re.id_resultado
and mf.id_metamef=pa.id_metamef
 and ac.num_anio='2009'
 and ac.id_area='28'  
 Order by num_actividad, nom_actividad


Select p.*,ef.cod_estructura, ef.nom_estructura, f.nom_fuente 
from presupuesto.programa p,presupuesto.estructura_funcional ef,presupuesto.fuente f
where p.id_estructura = ef.id_estructura 
and p.id_fuente = f.id_fuente
and p.fla_estado = 'A'
and p.num_anio = '2009' 
and ef.num_anio = '2009' 
and p.id_actividad = '8'
and p.id_resultado = '355'
Order by ef.cod_estructura, f.nom_fuente, p.id_programa



Select * From presupuesto.actividad Where num_anio = '2009'
and id_area = '28'
Order by num_actividad, nom_actividad

select * from presupuesto.persona where ema_persona='jdelcarpio'
