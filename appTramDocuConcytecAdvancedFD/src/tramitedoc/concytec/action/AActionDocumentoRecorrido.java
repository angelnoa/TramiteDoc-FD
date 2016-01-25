package tramitedoc.concytec.action;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import tramitedoc.concytec.bean.BCodPadre;
import tramitedoc.concytec.bean.BMesaPartes;
import tramitedoc.concytec.dao.interfaces.IUsuarioDAO;
import tramitedoc.concytec.dao.sql.SqlUsuarioDAO;
import tramitedoc.concytec.util.ValidaSessionAction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * AUTOR		: Machuca Ovalle
 * FECHA		: 03-04-2006
 * VERSION		: 1.0
 * DESCRIPCIÓN	: Accion de Logeo  
 */

public class AActionDocumentoRecorrido extends ValidaSessionAction{
	protected  Log log = LogFactory.getLog(AActionDocumentoRecorrido.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
		 HttpSession session = request.getSession(false);        
		 Connection cnx=null;
	        /*Verificar si la sesion se perdio*/
	        if (session==null){
	        	
	        	return (mapping.findForward("expiracion"));
	        	
	        }   
		
	        String ls_documento=null;
	        String ls_secuencia=null;
	        String ls_fecharpta=null;
	        String ls_pagina=null;
	        String ls_codigo_oficina=null;
	        String ls_accion=null;
	        String ls_tiposegumiento=null;
	       
	        ls_documento = String.valueOf(session.getAttribute("documento"));
	        ls_secuencia = String.valueOf(session.getAttribute("secuencia"));
	        ls_codigo_oficina = String.valueOf(session.getAttribute("codigo_oficina"));
	        ls_accion = String.valueOf(session.getAttribute("accion"));
	        ls_tiposegumiento = String.valueOf(session.getAttribute("tiposegumiento"));
	        ls_fecharpta = String.valueOf(session.getAttribute("fecha_rpta"));
	        //Funciones funciones=new Funciones();
	        log.info("DENTRO DE RECORRIDO FECHA DIFERENTE DE NULL");
		    log.info("el ls_documento" + ls_documento);
		    log.info("el ls_secuencia" + ls_secuencia);
		    log.info("el ls_codigo_oficina" + ls_codigo_oficina);
		    log.info("el ls_accion" + ls_accion);
		    log.info("el ls_tiposegumiento" + ls_tiposegumiento);
		    log.info("el ls_fecharpta" + ls_fecharpta);
		    
		try {
			
			
		    
			cnx = getConnection(request, "principal");
			log.info("El cnx  ==> " + cnx);
			
			String ls_ano  = "";
	    	String ls_mes  = "";
	    	String ls_dia_mov  = "";
	    	String ls_dia_rpta  = "";
	    	String ls_dia_sist  = "";
	    	String ls_mes_mov  = "";
	    	String ls_mes_rpta  = "";
	    	String ls_mes_sist  = "";
	    	
	    	//BMesaPartesVO = new BMesaPartes();
			IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
			
			if(ls_tiposegumiento.equals("D")){
				BCodPadre BCodPadreVO=daoIUsuarioDAO.of_obtener_padre(cnx,ls_documento,ls_secuencia);
				
				log.info("El BCodPadreVO" + BCodPadreVO);
				
				String ls_doc_inicia=BCodPadreVO.getCodigo_inicia();
				String ls_fecha_movimiento=BCodPadreVO.getFecha_movimiento();
				String ls_fecha_rpta=BCodPadreVO.getFecha_rpta();
				String ls_fecha_sistema=BCodPadreVO.getFecha_sistema();
				
				log.info("El ls_doc_inicia" + ls_doc_inicia);
				log.info("El ls_fecha_movimiento" + ls_fecha_movimiento);
				log.info("El ls_fecha_rpta" + ls_fecha_rpta);
				log.info("El ls_fecha_sistema" + ls_fecha_sistema);
				
					log.info("Si existe fecha de respuesta extraendo los dias");
					ls_dia_mov = ls_fecha_movimiento.substring(0, 2);
					ls_dia_rpta = ls_fecha_rpta.substring(0, 2);
					ls_dia_sist = ls_fecha_sistema.substring(0, 2);
					
		        	log.info("ls_dia_mov: " + ls_dia_mov );
		        	log.info("ls_dia_rpta: " + ls_dia_rpta );
		        	log.info("ls_dia_sist: " + ls_dia_sist );
		        	
		        	log.info("Si existe fecha de respuesta extraendo los meses");
					ls_mes_mov = ls_fecha_movimiento.substring(3, 5);
					ls_mes_rpta = ls_fecha_rpta.substring(3, 5);
					ls_mes_sist = ls_fecha_sistema.substring(3, 5);
					
		        	log.info("ls_mes_mov: " + ls_mes_mov );
		        	log.info("ls_mes_rpta: " + ls_mes_rpta);
		        	log.info("ls_mes_sist: " + ls_mes_sist);
		        	
		        	///////////////convirtiendo a entero los dias//////////////
		        	
		        	int dia_mov=Integer.parseInt(ls_dia_mov.trim());
		        	int dia_rpta=Integer.parseInt(ls_dia_rpta.trim());
		        	int dia_sist=Integer.parseInt(ls_dia_sist.trim());
		        	
		        	log.info("dia_mov: " + dia_mov);
		        	log.info("dia_rpta: " + dia_rpta);
		        	log.info("dia_sist: " + dia_sist);
		        	
		        	///////////////convirtiendo a entero los meses//////////////
		        	
		        	int mes_mov=Integer.parseInt(ls_mes_mov.trim());
		        	int mes_rpta=Integer.parseInt(ls_mes_rpta.trim());
		        	int mes_sist=Integer.parseInt(ls_mes_sist.trim());
		        	
		        	log.info("mes_mov: " + mes_mov);
		        	log.info("mes_rpta: " + mes_rpta);
		        	log.info("mes_sist: " + mes_sist);
		        	
		        	int dia_rpta_tot;
		        	int dia_rpta_sist;
		        	int dia_band;
		        	
		        	
		        	BMesaPartes detalleConsulta=daoIUsuarioDAO.of_detalle_documento(cnx,ls_codigo_oficina,ls_documento,ls_secuencia);
			          
					log.info("El detalleConsulta " + detalleConsulta);
					
		        	session.removeAttribute("amarillo");
		        	session.removeAttribute("verde");
		        	session.removeAttribute("rojo");
		        	session.removeAttribute("rs_recorrido");
					session.removeAttribute("dia_mov");
					session.removeAttribute("dia_rpta");
					session.removeAttribute("dia_sist");
					session.removeAttribute("fecha_rpta");
					session.removeAttribute("dias_bandeja");
					
					if(mes_rpta == mes_mov){
						
						log.info("Si los meses son iguales");
						dia_rpta_tot=dia_rpta-dia_mov;
			        	log.info("dia_rpta_tot: " + dia_rpta_tot);
			        	
			        	dia_rpta_sist=dia_sist-dia_mov;
			        	log.info("dia_rpta_sist: " + dia_rpta_sist);
			        	
			        	String dias_bandeja=String.valueOf(dia_rpta_sist);
			        	String dias_respuesta=String.valueOf(dia_rpta_tot);
			        	
			        	log.info("El dias_bandeja" + dias_bandeja);
			        	log.info("El dias_respuesta" + dias_respuesta);
			        	
			
						Collection rs_recorrido=daoIUsuarioDAO.of_recorrido(cnx,ls_doc_inicia);
						
						log.info("El rs_recorrido" + rs_recorrido);
						
						
						if(dia_rpta_tot>dia_rpta_sist){
			        		
							if(dia_rpta_sist<0){
								log.info("El color es rojo");
				        		session.setAttribute("rojo","3");
							}else{
								log.info("El color es verde");
				        		session.setAttribute("verde","1");
							}
			        	}
			        	if(dia_rpta_tot==dia_rpta_sist){
			        		
			        		log.info("El color es amarillo");
			        		session.setAttribute("amarillo","2");
			        	}
			        	if(dia_rpta_tot<dia_rpta_sist){
			        		
			        		log.info("El color es rojo");
			        		session.setAttribute("rojo","3");
			        	}
			        	
			        	session.setAttribute("rs_recorrido",rs_recorrido);
						session.setAttribute("dia_mov",ls_dia_mov);
						session.setAttribute("dia_rpta",ls_dia_rpta);
						session.setAttribute("dia_sist",ls_dia_sist);
						session.setAttribute("fecha_rpta",ls_fecha_rpta);
						session.setAttribute("dias_bandeja",dias_bandeja);
						session.setAttribute("detalleConsulta",detalleConsulta);
					}
		        	
					if(mes_rpta > mes_mov){
						
						log.info("Si el mes de respuesta es mayor al mes de registro");
						
						dia_rpta_tot=dia_rpta-dia_mov;
			        	log.info("dia_rpta_tot: " + dia_rpta_tot);
			        	
			        	dia_rpta_sist=dia_sist-dia_mov;
			        	log.info("dia_rpta_sist: " + dia_rpta_sist);
			        	
			        	String dias_bandeja=String.valueOf(dia_rpta_sist);
			        	String dias_respuesta=String.valueOf(dia_rpta_tot);
			        	
			        	log.info("El dias_bandeja" + dias_bandeja);
			        	log.info("El dias_respuesta" + dias_respuesta);
			        	
			        	
			        	if(dia_rpta_sist>=0){
			        		log.info("Si el dia_rpta_sist es mayor o igual a cero");
			        		if(dia_rpta_tot<dia_rpta_sist){
				        		
				        		log.info("El color es verde");
				        		session.setAttribute("verde","1");
				        	}
			        		
			        		if(dia_rpta_tot==dia_rpta_sist){
				        		
				        		log.info("El color es amarillo");
				        		session.setAttribute("amarillo","2");
				        	}
			        		
			        	}else{
			        		
			        		log.info("Si el dia_rpta_sist es negativo");
			        		
			        		if(dia_rpta_tot<dia_rpta_sist){
				        		
				        		log.info("El color es rojo");
				        		session.setAttribute("rojo","3");
				        	}
			        		
			        	}

						Collection rs_recorrido=daoIUsuarioDAO.of_recorrido(cnx,ls_doc_inicia);
						
						log.info("El rs_recorrido" + rs_recorrido);
				
						/*String ls_observacion_derivacion=funciones.of_observacion_derivacion(cnx, ls_documento);
						log.info("El ls_observacion_derivacion" + ls_observacion_derivacion);*/
						
			        	session.setAttribute("rs_recorrido",rs_recorrido);
						session.setAttribute("dia_mov",ls_dia_mov);
						session.setAttribute("dia_rpta",ls_dia_rpta);
						session.setAttribute("dia_sist",ls_dia_sist);
						session.setAttribute("fecha_rpta",ls_fecha_rpta);
						session.setAttribute("dias_bandeja",dias_bandeja);
						session.setAttribute("detalleConsulta",detalleConsulta);
						session.setAttribute("tiposegumiento",ls_tiposegumiento);
						//session.setAttribute("observacion_derivacion",ls_observacion_derivacion);
						
					}
					
					if(ls_accion.equals("fecharecorrido")){
						
						log.info("Si el Fecha Recorrido exite");
						return (mapping.findForward("recorridoreporte"));
					}else{
						
						log.info("Si el Fecha Recorrido es null");
						return (mapping.findForward("recorrido"));
					}	
			
			}else{
				log.info("Si el tipo de Seguimiento es RRRRRRRRRR.. ");
				
				/***
				 * 
				 * String fecha1 = "12/05/2013";  
					 String fecha2 = "13/05/2013";  
					 String fecha3 = "14/05/2013";  
					   
					 //Obtenemos la fecha del sistema y la convertirmos al String  con el formato en el que vamos a trabajar
					 Date fechaActual = new Date();
					        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
					        String fechaSistema=formateador.format(fechaActual);
					         
					
					   
					 String resultadoMenor=objetoPrincipal.compararFechasConDate(fecha1,fechaSistema);
					 System.out.println(resultadoMenor+"\n");
				 */
				
				
				
				log.info("El ls_documento es LLLL.. " + ls_documento);
				log.info("El ls_secuencia es LLLL.. " + ls_secuencia);
				log.info("El ls_codigo_oficina es LLLL.. " + ls_codigo_oficina);
				
				/*BCodPadre BCodPadreVO=daoIUsuarioDAO.of_obtener_padre(cnx,ls_documento,ls_secuencia);
				log.info("El BCodPadreVO" + BCodPadreVO);
				String ls_fecha_rpta=BCodPadreVO.getFecha_rpta();*/
				
				//BMesaPartes detalleConsulta=daoIUsuarioDAO.of_detalle_documento(cnx,ls_codigo_oficina,ls_documento,ls_secuencia);
				/***********************************************/
				  
				  BCodPadre BCodPadreVO=daoIUsuarioDAO.of_obtener_padre(cnx,ls_documento,ls_secuencia);
				
				log.info("El BCodPadreVO" + BCodPadreVO);
				
				String ls_doc_inicia=BCodPadreVO.getCodigo_inicia();
				String ls_fecha_movimiento=BCodPadreVO.getFecha_movimiento();
				String ls_fecha_rpta=BCodPadreVO.getFecha_rpta();
				String ls_fecha_sistema=BCodPadreVO.getFecha_sistema();
				
				log.info("El ls_doc_inicia" + ls_doc_inicia);
				log.info("El ls_fecha_movimiento" + ls_fecha_movimiento);
				log.info("El ls_fecha_rpta" + ls_fecha_rpta);
				log.info("El ls_fecha_sistema" + ls_fecha_sistema);
				
					log.info("Si existe fecha de respuesta extraendo los dias");
					ls_dia_mov = ls_fecha_movimiento.substring(0, 2);
					ls_dia_rpta = ls_fecha_rpta.substring(0, 2);
					ls_dia_sist = ls_fecha_sistema.substring(0, 2);
					
		        	log.info("ls_dia_mov: " + ls_dia_mov );
		        	log.info("ls_dia_rpta: " + ls_dia_rpta );
		        	log.info("ls_dia_sist: " + ls_dia_sist );
		        	
		        	log.info("Si existe fecha de respuesta extraendo los meses");
					ls_mes_mov = ls_fecha_movimiento.substring(3, 5);
					ls_mes_rpta = ls_fecha_rpta.substring(3, 5);
					ls_mes_sist = ls_fecha_sistema.substring(3, 5);
					
		        	log.info("ls_mes_mov: " + ls_mes_mov );
		        	log.info("ls_mes_rpta: " + ls_mes_rpta);
		        	log.info("ls_mes_sist: " + ls_mes_sist);
		        	
		        	///////////////convirtiendo a entero los dias//////////////
		        	
		        	int dia_mov=Integer.parseInt(ls_dia_mov.trim());
		        	int dia_rpta=Integer.parseInt(ls_dia_rpta.trim());
		        	int dia_sist=Integer.parseInt(ls_dia_sist.trim());
		        	
		        	log.info("dia_mov: " + dia_mov);
		        	log.info("dia_rpta: " + dia_rpta);
		        	log.info("dia_sist: " + dia_sist);
		        	
		        	///////////////convirtiendo a entero los meses//////////////
		        	
		        	int mes_mov=Integer.parseInt(ls_mes_mov.trim());
		        	int mes_rpta=Integer.parseInt(ls_mes_rpta.trim());
		        	int mes_sist=Integer.parseInt(ls_mes_sist.trim());
		        	
		        	log.info("mes_mov: " + mes_mov);
		        	log.info("mes_rpta: " + mes_rpta);
		        	log.info("mes_sist: " + mes_sist);
		        	
		        	int dia_rpta_tot;
		        	int dia_rpta_sist;
		        	int dia_band;
		        	
		        	
		        	BMesaPartes detalleConsulta=daoIUsuarioDAO.of_detalle_documento(cnx,ls_codigo_oficina,ls_documento,ls_secuencia);
			          
					log.info("El detalleConsulta " + detalleConsulta);
					
		        	session.removeAttribute("amarillo");
		        	session.removeAttribute("verde");
		        	session.removeAttribute("rojo");
		        	session.removeAttribute("rs_recorrido");
					session.removeAttribute("dia_mov");
					session.removeAttribute("dia_rpta");
					session.removeAttribute("dia_sist");
					session.removeAttribute("fecha_rpta");
					session.removeAttribute("dias_bandeja");
					
					if(mes_rpta == mes_mov){
						
						log.info("Si los meses son iguales");
						dia_rpta_tot=dia_rpta-dia_mov;
			        	log.info("dia_rpta_tot: " + dia_rpta_tot);
			        	
			        	dia_rpta_sist=dia_sist-dia_mov;
			        	log.info("dia_rpta_sist: " + dia_rpta_sist);
			        	
			        	String dias_bandeja=String.valueOf(dia_rpta_sist);
			        	String dias_respuesta=String.valueOf(dia_rpta_tot);
			        	
			        	log.info("El dias_bandeja" + dias_bandeja);
			        	log.info("El dias_respuesta" + dias_respuesta);
			        	
			
						//Collection rs_recorrido=daoIUsuarioDAO.of_recorrido(cnx,ls_doc_inicia);
						
						//log.info("El rs_recorrido" + rs_recorrido);
						
						
						if(dia_rpta_tot>dia_rpta_sist){
							if(dia_rpta_sist<0){
								log.info("El color es rojo");
				        		session.setAttribute("rojo","3");
							}else{
				        		log.info("El color es verde");
				        		session.setAttribute("verde","1");
							}
			        	}
			        	if(dia_rpta_tot==dia_rpta_sist){
			        		
			        		log.info("El color es amarillo");
			        		session.setAttribute("amarillo","2");
			        	}
			        	if(dia_rpta_tot<dia_rpta_sist){
			        		
			        		log.info("El color es rojo");
			        		session.setAttribute("rojo","3");
			        	}
			        	
			        	//session.setAttribute("rs_recorrido",rs_recorrido);
						session.setAttribute("dia_mov",ls_dia_mov);
						session.setAttribute("dia_rpta",ls_dia_rpta);
						session.setAttribute("dia_sist",ls_dia_sist);
						session.setAttribute("fecha_rpta",ls_fecha_rpta);
						session.setAttribute("dias_bandeja",dias_bandeja);
						session.setAttribute("detalleConsulta",detalleConsulta);
					}
		        	
					if(mes_rpta > mes_mov){
						
						log.info("Si el mes de respuesta es mayor al mes de registro");
						
						dia_rpta_tot=dia_rpta-dia_mov;
			        	log.info("dia_rpta_tot: " + dia_rpta_tot);
			        	
			        	dia_rpta_sist=dia_sist-dia_mov;
			        	log.info("dia_rpta_sist: " + dia_rpta_sist);
			        	
			        	String dias_bandeja=String.valueOf(dia_rpta_sist);
			        	String dias_respuesta=String.valueOf(dia_rpta_tot);
			        	
			        	log.info("El dias_bandeja" + dias_bandeja);
			        	log.info("El dias_respuesta" + dias_respuesta);
			        	
			        	
			        	if(dia_rpta_sist>=0){
			        		log.info("Si el dia_rpta_sist es mayor o igual a cero");
			        		if(dia_rpta_tot<dia_rpta_sist){
				        		
				        		log.info("El color es verde");
				        		session.setAttribute("verde","1");
				        	}
			        		
			        		if(dia_rpta_tot==dia_rpta_sist){
				        		
				        		log.info("El color es amarillo");
				        		session.setAttribute("amarillo","2");
				        	}
			        		
			        	}else{
			        		
			        		log.info("Si el dia_rpta_sist es negativo");
			        		
			        		if(dia_rpta_tot<dia_rpta_sist){
				        		
				        		log.info("El color es rojo");
				        		session.setAttribute("rojo","3");
				        	}
			        		
			        	}
					}	
				 /***********************/
		          
				log.info("El detalleConsulta " + detalleConsulta);
				log.info("Imprimiendo fecha respuesta... " + ls_fecharpta);
				/*String ls_observacion_derivacion=funciones.of_observacion_derivacion(cnx, ls_documento);
				log.info("El ls_observacion_derivacion" + ls_observacion_derivacion);*/
				
				Collection listaDocumento =  daoIUsuarioDAO.of_lista_frame_historial(cnx,ls_documento,ls_secuencia,"");
				log.info("El listaDocumento es "+ listaDocumento);
				session.setAttribute("rs_listaHistorial",listaDocumento);
				session.setAttribute("tiposegumiento",ls_tiposegumiento);
				session.setAttribute("detalleConsulta",detalleConsulta);
				session.setAttribute("fecha_rpta",ls_fecharpta);
				//session.setAttribute("observacion_derivacion",ls_observacion_derivacion);
				/*session.setAttribute("documento",ls_documento);
				session.setAttribute("secuencia",ls_secuencia);
				session.setAttribute("codigo_oficina",ls_codigo_oficina);*/
				
				if(ls_accion.equals("fecharecorrido")){
					
					log.info("Si el Fecha Recorrido exite");
					return (mapping.findForward("recorridoreporte"));
				}else{
					
					log.info("Si el Fecha Recorrido es null");
					return (mapping.findForward("recorrido"));
				}

				
			}
			
			
        } 
        catch (Exception e) {
            e.printStackTrace();
        }finally{
        	closeConnection(cnx);	
        }
       	return (mapping.findForward("error"));
	}
	
	private String compararFechasConDate(String fecha1, String fechaActual) {  
		  System.out.println("Parametro String Fecha 1 = "+fecha1+"\n" +
		    "Parametro String fechaActual = "+fechaActual+"\n");  
		  String resultado="";
		  try {
		   /**Obtenemos las fechas enviadas en el formato a comparar*/
		   SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy"); 
		   Date fechaDate1 = formateador.parse(fecha1);
		   Date fechaDate2 = formateador.parse(fechaActual);
		    
		   System.out.println("Parametro Date Fecha 1 = "+fechaDate1+"\n" +
		     "Parametro Date fechaActual = "+fechaDate2+"\n");
		    
		    if ( fechaDate1.before(fechaDate2) ){
		    resultado= "La Fecha 1 es menor ";
		    }else{
		     if ( fechaDate2.before(fechaDate1) ){
		      resultado= "La Fecha 1 es Mayor ";
		     }else{
		      resultado= "Las Fechas Son iguales ";
		     } 
		    }
		  } catch (ParseException e) {
		   System.out.println("Se Produjo un Error!!!  "+e.getMessage());
		  }  
		  return resultado;
		 }
}
