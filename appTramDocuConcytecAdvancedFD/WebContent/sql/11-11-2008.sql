Select cg.cla_gasto,cg.cod_generica,cg.nom_generica,eg.cod_nivel_1,eg.des_nivel_1,eg.clase_gasto  
From presupuesto.clase_gasto cg,presupuesto.especifica_clase_gasto eg 
where cg.cla_gasto=eg.clase_gasto
 Order by nom_generica

select clase_gasto.cla_gasto,clase_gasto.cod_generica,clase_gasto.nom_generica,

especifica_clase_gasto.cod_nivel_1,especifica_clase_gasto.des_nivel_1

from presupuesto.clase_gasto,presupuesto.especifica_clase_gasto

where clase_gasto.cod_generica=especifica_clase_gasto.clase_generica and

especifica_clase_gasto.clase_gasto='2111'

 

---------2 Nivel--------------------

 

select clase_gasto.cla_gasto,clase_gasto.cod_generica,clase_gasto.nom_generica,

especifica_clase_gasto.cod_nivel_1,especifica_clase_gasto.des_nivel_1,especifica_gasto.cod_especifica,

especifica_gasto.nom_especifica

from presupuesto.clase_gasto,presupuesto.especifica_clase_gasto,presupuesto.especifica_gasto

where clase_gasto.cod_generica=especifica_clase_gasto.clase_generica and

especifica_clase_gasto.cod_nivel_1=especifica_gasto.cod_nivel_1 and

especifica_clase_gasto.clase_gasto='2111' and especifica_clase_gasto.cod_nivel_1='21010101'
