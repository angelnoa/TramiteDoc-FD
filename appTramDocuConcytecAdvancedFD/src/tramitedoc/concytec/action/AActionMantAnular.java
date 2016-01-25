package tramitedoc.concytec.action;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;
import javax.sql.DataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import tramitedoc.concytec.dao.*;
import tramitedoc.concytec.form.*;
import tramitedoc.concytec.bean.*;
import tramitedoc.concytec.dao.interfaces.*;
import tramitedoc.concytec.util.*;
import tramitedoc.concytec.dao.sql.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class AActionMantAnular extends ValidaSessionAction{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		log.info("DENTRO DEL MANTANULAR DOC...");
		
		HttpSession session = request.getSession(true);
		
		Connection cnx = getConnection(request, "principal");
		log.info("El cnx............" + cnx);	    
	  		
		IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
	
		/************************/
		String ls_variable = (String)session.getAttribute("anulafirmado");
		ls_variable=(ls_variable==null)?"":ls_variable;
		/*************************/
		
		FFormMantAnular oForm = (FFormMantAnular) form;
		log.info("Antes del FFormMantAnular...");	  
		String tipo = oForm.getTipo() == null ? "init" : oForm.getTipo().trim();
		log.info("Despues del tipo...");	 
		log.info("El tipo 1 "+ tipo);
		tipo = tipo.equals("") ? "init" : tipo;
		log.info("El tipo 2 "+ tipo);
		
		String codigo_documento;
		String codigo_expediente;
		String descripcion_tipo;
		String numero_documento;
		String ls_codigo_oficina;
		String fecha;
		String hora;
		String observacion;
		String mensaje;
		String usuario;
		String codigo_oficina;
		String estado_movimiento;
		String secuencia_movimiento;
		String codigo_inicia;
		String naturaleza_documento;
		String codigo_documento_busqueda_ls;
		String ls_correlativorecepcion="";
		String ls_msg_error="";
		
		int li_retorno=0;
		
		
        codigo_oficina   =   String.valueOf(session.getAttribute("codigo_oficina"));
        log.info("El session codigo_oficina es :" + codigo_oficina);
     
		usuario   = String.valueOf(session.getAttribute("nombreusuario"));
		
				
		codigo_documento_busqueda_ls = request.getParameter("codigo_documento_busqueda_ls");
		codigo_documento_busqueda_ls = (codigo_documento_busqueda_ls==null)? "":codigo_documento_busqueda_ls.trim();
		log.info("codigo_doc "+codigo_documento_busqueda_ls);
		
		
		
		
		log.info("Modificacion de... MantAnular .........." );
		
				
   	 	Date fecha_recep = new Date(); // fecha y hora locales 
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy"); 
		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm"); 
		log.info("la fecha es.."+ formatoFecha.format(fecha_recep) ); 
		log.info("la hora es.."+ formatoHora.format(fecha_recep) ); 
		
		session.removeAttribute("error");
		try {
			
			
			if (tipo.equals("anular") ){
				
				log.info("Si tipo es init " + tipo);
				log.info("Codigo doc .. "+codigo_documento_busqueda_ls);
				log.info("Listar Anular Documentos....." );
				
				String ls_inicia = (String)request.getAttribute("inicia");
				ls_inicia=(ls_inicia==null)? "":ls_inicia.trim();
				log.info("-----> "+ls_inicia);
				//String ls_document = (String)request.getAttribute("document");

				
				Collection rs_anular_doc = new ArrayList();
				
		        if(!ls_inicia.equals("SI") && ls_variable.equals("SI")){
		        	log.info("Entry a");
				   rs_anular_doc = daoIUsuarioDAO.lista_documentos_anular(cnx, codigo_oficina,codigo_documento_busqueda_ls,ls_variable);
		        }else{
		        	if(!ls_inicia.equals("SI")){
		        		log.info("Entry b");
		        		rs_anular_doc = daoIUsuarioDAO.lista_documentos_anular(cnx, codigo_oficina,codigo_documento_busqueda_ls,"");
		        	}else{
		        		rs_anular_doc = null;
		        	}
		        }
		        
		        if(ls_variable.equals("SI")){
					 request.setAttribute("vertitulo",0);
			    }
		        
						        	
					session.setAttribute("operacion",tipo);
					session.setAttribute("rs_anular_doc",rs_anular_doc);
					
					//return (mapping.findForward("exito"));	
			}
			
			else if (tipo.equalsIgnoreCase("editar")){
				
				//String ls_document = (String)request.getAttribute("document");
				
				log.info("Si tipo es editar init" + tipo);
				log.info("Dentro de Editar en MantRecepcion");	

   	    		log.info("Entregar recibir ls_codigo_inicia.equals(ls_codigoPadre) son diferentes" );	
   	    		
   	    		codigo_documento = ((FFormMantAnular)form).getCodigo_documento();
   	 		codigo_expediente = ((FFormMantAnular)form).getCodigo_expediente();
   	 		descripcion_tipo = ((FFormMantAnular)form).getDescripcion_tipo();
   	 		numero_documento = ((FFormMantAnular)form).getNumero_documento();
   	 		
   	 		fecha = ((FFormMantAnular)form).getFecha();
   	 		hora = ((FFormMantAnular)form).getHora();
   	 		observacion = ((FFormMantAnular)form).getObservacion();
   	 		
   	 		
   	 		//ls_codigo_oficina = ((FFormMantRecepcion)form).getCodigo_oficina();
   	 		estado_movimiento = ((FFormMantAnular)form).getEstado_movimiento();
   	 		secuencia_movimiento = ((FFormMantAnular)form).getSecuencia_movimiento();
   	 		codigo_inicia = ((FFormMantAnular)form).getCodigo_inicia();
   	 		naturaleza_documento = ((FFormMantAnular)form).getNaturaleza_documento();
   	 		
   	 		
   	 		log.info("usuario... "+ usuario);
   	 		log.info("codigo_documento... "+ codigo_documento);
   	 		log.info("codigo_expediente... "+ codigo_expediente);
   	 		log.info("codigo_expediente... "+ codigo_expediente);
   	 		log.info("numero_documento... "+ numero_documento);
   	 		log.info("fecha... "+ fecha);
   	 		log.info("hora... "+ hora);
   	 		log.info("observacion... "+ observacion);
   	 		
   	 		log.info("codigo_oficina... "+ codigo_oficina);
   	 		log.info("estado_movimiento... "+ estado_movimiento);
   	 		log.info("secuencia_movimiento... "+ secuencia_movimiento);
   	 		
   	    		log.info("La observacion es...."+ observacion);	
   	    		
   	    		if(observacion.equals("null")){
   	 			observacion="";
   	 			log.info("La observacion es null de lo contrario es igual a vacio.");
   	    		}
   	    		log.info("La observacion ahora es...."+ observacion);
   	    		
      	    	
   	    		Collection rs_anular_doc = new ArrayList();
		        
   	    		if(ls_variable.equals("SI")){
		        	request.setAttribute("vertitulo",0);
		        }
					
				session.setAttribute("operacion",tipo);
				
			
					
			return mapping.getInputForward();
	
		} 
			else if (tipo.equalsIgnoreCase("enviar")){

			try {
				
				//mensaje="CODIGO DE RECEPCION GENERADO ES:"+ ls_correlativorecepcion;
   	    		//session.setAttribute("mensaje",mensaje);
				//String ls_document = (String)session.getAttribute("document");
				
   	    	codigo_documento = ((FFormMantAnular)form).getCodigo_documento();
   	 		codigo_expediente = ((FFormMantAnular)form).getCodigo_expediente();
   	 		descripcion_tipo = ((FFormMantAnular)form).getDescripcion_tipo();
   	 		numero_documento = ((FFormMantAnular)form).getNumero_documento();
   	 		
   	 		fecha = ((FFormMantAnular)form).getFecha();
   	 		hora = ((FFormMantAnular)form).getHora();
   	 		observacion = ((FFormMantAnular)form).getObservacion();
   	 		
   	 		
   	 		//ls_codigo_oficina = ((FFormMantRecepcion)form).getCodigo_oficina();
   	 		estado_movimiento = ((FFormMantAnular)form).getEstado_movimiento();
   	 		secuencia_movimiento = ((FFormMantAnular)form).getSecuencia_movimiento();
   	 		codigo_inicia = ((FFormMantAnular)form).getCodigo_inicia();
   	 		naturaleza_documento = ((FFormMantAnular)form).getNaturaleza_documento();
   	 		
   	 		
   	 		log.info("usuario... "+ usuario);
   	 		log.info("codigo_documento... "+ codigo_documento);
   	 		log.info("codigo_expediente... "+ codigo_expediente);
   	 		log.info("codigo_expediente... "+ codigo_expediente);
   	 		log.info("numero_documento... "+ numero_documento);
   	 		log.info("fecha... "+ fecha);
   	 		log.info("hora... "+ hora);
   	 		log.info("observacion... "+ observacion);
   	 		
   	 		log.info("codigo_oficina... "+ codigo_oficina);
   	 		log.info("estado_movimiento... "+ estado_movimiento);
   	 		log.info("secuencia_movimiento... "+ secuencia_movimiento);
   	 		
   	    		
   	    		if (estado_movimiento.equals("3") || estado_movimiento.equals("2")) 
                {//Si esta tramitado o por recibir
   	    			
   	    			daoIUsuarioDAO.of_anular(cnx,codigo_documento,codigo_oficina,secuencia_movimiento,observacion,estado_movimiento );
   					
   	    			
   	   	    		Collection rs_anular_doc = new ArrayList();
   			        
   					
   						
   						session.setAttribute("operacion",tipo);
   						session.setAttribute("rs_anular_doc",rs_anular_doc);
   						session.setAttribute("listaFrameBusquedaTemp",null);
   						request.setAttribute("mensaje_sistema", "El documento "+codigo_documento+ " ha sido anulado");       
                	
                }
                else
                {
                    ls_msg_error = "No se puede Anular el Documento, el Estado del Documento debe" +
                    		" ser Tramite o Por Recibir..!";
                    request.setAttribute("mensaje_sistema", ls_msg_error);       
                   
                }
   	    		
   	    		if(ls_variable.equals("SI")){
		        	request.setAttribute("vertitulo",0);
		        }
   	    		
				
				
				
			}catch (SQLException e) {
				e.printStackTrace();
			//	closeConnection(cnx);
				
	
			}
			

		} else if (tipo.equalsIgnoreCase("desanular")){

			try {
				
				
			String ls_document = (String)request.getAttribute("document");	
			
   	    	codigo_documento = ((FFormMantAnular)form).getCodigo_documento();
   	 		codigo_expediente = ((FFormMantAnular)form).getCodigo_expediente();
   	 		descripcion_tipo = ((FFormMantAnular)form).getDescripcion_tipo();
   	 		numero_documento = ((FFormMantAnular)form).getNumero_documento();
   	 		
   	 		fecha = ((FFormMantAnular)form).getFecha();
   	 		hora = ((FFormMantAnular)form).getHora();
   	 		observacion = ((FFormMantAnular)form).getObservacion();
   	 		
   	 		
   	 		//ls_codigo_oficina = ((FFormMantRecepcion)form).getCodigo_oficina();
   	 		estado_movimiento = ((FFormMantAnular)form).getEstado_movimiento();
   	 		secuencia_movimiento = ((FFormMantAnular)form).getSecuencia_movimiento();
   	 		codigo_inicia = ((FFormMantAnular)form).getCodigo_inicia();
   	 		naturaleza_documento = ((FFormMantAnular)form).getNaturaleza_documento();
   	 		
   	 		
   	 		log.info("usuario... "+ usuario);
   	 		log.info("codigo_documento... "+ codigo_documento);
   	 		log.info("codigo_expediente... "+ codigo_expediente);
   	 		log.info("codigo_expediente... "+ codigo_expediente);
   	 		log.info("numero_documento... "+ numero_documento);
   	 		log.info("fecha... "+ fecha);
   	 		log.info("hora... "+ hora);
   	 		log.info("observacion... "+ observacion);
   	 		
   	 		log.info("codigo_oficina... "+ codigo_oficina);
   	 		log.info("estado_movimiento... "+ estado_movimiento);
   	 		log.info("secuencia_movimiento... "+ secuencia_movimiento);
   	 		
   	    		
   	    		if (estado_movimiento.equals("9")) 
                {
   	    			
   	    			daoIUsuarioDAO.of_anular(cnx,codigo_documento,codigo_oficina,
   	    					secuencia_movimiento,observacion,estado_movimiento );
   					
   	   	    		Collection rs_anular_doc = new ArrayList();
   			        
   	   	    		
   	   	    	if(ls_variable.equals("SI")){
 				   rs_anular_doc = daoIUsuarioDAO.lista_documentos_anular(cnx, codigo_oficina,codigo_documento_busqueda_ls,ls_variable);
 		        }else{
 		        	rs_anular_doc = daoIUsuarioDAO.lista_documentos_anular(cnx, codigo_oficina,codigo_documento_busqueda_ls,"");
 		        }
 		        
 		        if(ls_variable.equals("SI")){
 					 request.setAttribute("vertitulo",0);
 			    }
					
   						
   						session.setAttribute("operacion",tipo);
   						session.setAttribute("rs_anular_doc",rs_anular_doc);
   						
                	
                }
                else
                {
                    ls_msg_error = "No se puede Desanular el Documento, el Estado del Documento debe" +
                    		" ser Anulado..!";
                    session.setAttribute("error", ls_msg_error);       

                }
   	    		
   	    		
				
				
				
			}catch (SQLException e) {
				e.printStackTrace();
			//	closeConnection(cnx);
				
	
			}
			

		} 

		
		oForm.reset(mapping, request);
		oForm.setTipo("anular");
		return (mapping.findForward("exito"));
 
    	   
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
