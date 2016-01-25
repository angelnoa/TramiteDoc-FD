Select * From presupuesto.resultado Where num_anio = '2009' 
Select * From presupuesto.resultado Where num_anio = '2009' and id_planarea = '4' and id_area = '28' Order by des_resultado


select ac.id_actividad,ac.id_planarea,ac.num_actividad,ac.nom_actividad,ac.des_actividad,ac.id_resultado,ac.num_anio,
	re.id_resultado,re.id_planarea,re.id_area,
	ar.id_area,ar.nom_area from presupuesto.actividad ac,presupuesto.resultado re,presupuesto.area ar
	where ac.id_resultado=re.id_resultado and re.id_area=ar.id_area and ac.num_anio='2009' and ac.id_resultado='' order by id_actividad asc