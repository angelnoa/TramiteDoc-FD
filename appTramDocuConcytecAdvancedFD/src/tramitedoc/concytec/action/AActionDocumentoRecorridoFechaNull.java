package tramitedoc.concytec.action;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
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
import tramitedoc.concytec.util.Funciones;
import tramitedoc.concytec.util.ValidaSessionAction;
/**
 * AUTOR		: Machuca Ovalle
 * FECHA		: 03-04-2006
 * VERSION		: 1.0
 * DESCRIPCIÓN	: Accion de Logeo  
 */

public class AActionDocumentoRecorridoFechaNull extends ValidaSessionAction{
	protected  Log log = LogFactory.getLog(AActionDocumentoRecorridoFechaNull.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Connection cnx=null;
		 HttpSession session = request.getSession(false);        

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
	        String ls_numero_referencia=null;
	        
	        ls_documento = String.valueOf(session.getAttribute("documento"));
	        ls_secuencia = String.valueOf(session.getAttribute("secuencia"));
	        ls_codigo_oficina = String.valueOf(session.getAttribute("codigo_oficina"));
	        
	        ls_accion = String.valueOf(session.getAttribute("accion"));
	        ls_tiposegumiento = String.valueOf(session.getAttribute("tiposegumiento"));
	        ls_numero_referencia = String.valueOf(session.getAttribute("numero_referencia"));
	        
	        Funciones funciones=new Funciones();
	        log.info("DENTRO DE RECORRIDO FECHA NULL");
		    log.info("el ls_documento" + ls_documento);
		    log.info("el ls_secuencia" + ls_secuencia);
		    log.info("el ls_codigo_oficina" + ls_codigo_oficina);
		    log.info("el ls_accion" + ls_accion);
		    log.info("el ls_tiposegumiento" + ls_tiposegumiento);
		    //Funciones funciones=new Funciones();
		    
		try {
	
			cnx = getConnection(request, "principal");
			log.info("El cnx  ==> " + cnx);
			
			String ls_ano  = "";
	    	String ls_mes  = "";
	    	String ls_dia_mov  = "";
	    	String ls_dia_rpta  = "";
	    	String ls_dia_sist  = "";
	    	
	    	//BMesaPartesVO = new BMesaPartes();
			IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
			
			if(ls_tiposegumiento.equals("D")){
				
				log.info("Si el tipo de Seguimiento es DDDDDDDDD ");
				BCodPadre BCodPadreVO=daoIUsuarioDAO.of_obtener_padre(cnx,ls_documento,ls_secuencia);
				
				log.info("El BCodPadreVO" + BCodPadreVO);
				
				String ls_doc_inicia=BCodPadreVO.getCodigo_inicia();
				String ls_fecha_movimiento=BCodPadreVO.getFecha_movimiento();
				String ls_fecha_sistema=BCodPadreVO.getFecha_sistema();
				
				log.info("El ls_doc_inicia" + ls_doc_inicia);
				log.info("El ls_fecha_movimiento" + ls_fecha_movimiento);
				log.info("El ls_fecha_sistema" + ls_fecha_sistema);
				
				log.info("Si existe fecha de respuesta extraendo los dias");
				ls_dia_mov = ls_fecha_movimiento.substring(0, 2);
				ls_dia_sist = ls_fecha_sistema.substring(0, 2);
				
	        	log.info("ls_dia_mov: " + ls_dia_mov );
	        	log.info("ls_dia_sist: " + ls_dia_sist );
	        	
	        	
	        	
	        	///////////////convirtiendo a entero los dias//////////////
	        	
	        	int dia_mov=Integer.parseInt(ls_dia_mov.trim());
	        	int dia_sist=Integer.parseInt(ls_dia_sist.trim());
	        	
	        	log.info("dia_mov: " + dia_mov);
	        	log.info("dia_sist: " + dia_sist);
	        	
	        	
	        	int dia_rpta_tot;
	        	int dia_rpta_sist;
	        	int dia_band;
	        	
	        	
	        	
	        	session.removeAttribute("rs_recorrido");
				session.removeAttribute("dia_mov");
				session.removeAttribute("dia_sist");
				session.removeAttribute("dias_bandeja");
				
			
		        	dia_rpta_sist=dia_sist-dia_mov;
		        	log.info("dia_rpta_sist: " + dia_rpta_sist);
		        	
		        	String dias_bandeja=String.valueOf(dia_rpta_sist);
		        	log.info("El dias_bandeja" + dias_bandeja);
		        	
		      //  	BMesaPartes detalleConsulta=daoIUsuarioDAO.of_detalle_documento(cnx,ls_codigo_oficina,ls_documento,ls_secuencia);
			          
			//		log.info("El detalleConsulta " + detalleConsulta);
					
					/*String ls_observacion_derivacion=funciones.of_observacion_derivacion(cnx, ls_documento);
					log.info("El ls_observacion_derivacion" + ls_observacion_derivacion);*/
					
					Collection rs_recorrido= new ArrayList();
					rs_recorrido = daoIUsuarioDAO.of_recorrido(cnx,ls_documento);
					
					//log.info("El rs_recorrido" + rs_recorrido);
					
					//session.removeAttribute("rs_recorrido");
					
					session.setAttribute("rs_recorrido",rs_recorrido);

					session.setAttribute("dia_mov",ls_dia_mov);
					session.setAttribute("dia_sist",ls_dia_sist);
					session.setAttribute("dias_bandeja",dias_bandeja);
				//	session.setAttribute("detalleConsulta",detalleConsulta);
					session.setAttribute("tiposegumiento",ls_tiposegumiento);
					//session.setAttribute("observacion_derivacion",ls_observacion_derivacion);
					
					if(ls_accion.equals("fecharecorrido")){
						
						log.info("Si el Fecha Recorrido exite");
						return (mapping.findForward("recorridoreporte"));
					}else{
						
						log.info("Si el Fecha Recorrido es null");
						return (mapping.findForward("recorrido"));
					}
					
			}else{
				log.info("Si el tipo de Seguimiento es RRRRRRRRRR.. ");
				
				log.info("El ls_documento es LLLL.. " + ls_documento);
				log.info("El ls_secuencia es LLLL.. " + ls_secuencia);
				log.info("El ls_codigo_oficina es LLLL.. " + ls_codigo_oficina);
				log.info("El ls_numero_referencia es LLLL.. " + ls_numero_referencia);
				
				String ls_max_secuencia_envio= funciones.of_max_secuencia_envio(cnx,ls_documento);
				
				BMesaPartes detalleConsulta=daoIUsuarioDAO.of_detalle_documento(cnx,ls_codigo_oficina,ls_documento,ls_max_secuencia_envio);
				log.info("El detalleConsulta " + detalleConsulta);
				
				/*String ls_observacion_derivacion=funciones.of_observacion_derivacion(cnx, ls_documento);
				log.info("El ls_observacion_derivacion" + ls_observacion_derivacion);*/
				
				Collection listaDocumento =  daoIUsuarioDAO.of_lista_frame_historial(cnx,ls_documento,ls_secuencia,ls_numero_referencia);
				log.info("El listaDocumento es "+ listaDocumento);
				session.setAttribute("rs_listaHistorial",listaDocumento);
				session.setAttribute("tiposegumiento",ls_tiposegumiento);
				session.setAttribute("detalleConsulta",detalleConsulta);
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
        }
        finally{
        	closeConnection(cnx);	
        }
       	return (mapping.findForward("error"));
	}
}
