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
import tramitedoc.concytec.service.DocumentosInternosServiceImplement;
import tramitedoc.concytec.util.*;
import tramitedoc.concytec.dao.sql.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
/**
 * AUTOR		: Machuca Ovalle
 * FECHA		: 03-04-2006
 * VERSION		: 1.0
 * DESCRIPCIÓN	: Accion de Logeo  
 */

public class AActionMantModDeriva extends ValidaSessionAction{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println("DENTRO DEL AActionMantModDeriva DERIVA DOC...");
		
		HttpSession session = request.getSession(true);
		Connection cnx = getConnection(request, "principal");
		System.out.println("El cnx............" + cnx);	    
	  		
		IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
		Funciones funciones = new Funciones();
		DocumentosInternosService service = new DocumentosInternosServiceImplement();	

		FFormMantModificar oForm = (FFormMantModificar) form;
		System.out.println("Antes del AActionMantModDeriva...");	  
		String tipo = oForm.getTipo() == null ? "init" : oForm.getTipo().trim();
		System.out.println("Despues del tipo...");	 
		System.out.println("El tipo 1 "+ tipo);
		tipo = tipo.equals("") ? "init" : tipo;
		System.out.println("El tipo 2 "+ tipo);
		
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
		String ls_operacion;
		
		String ls_correlativorecepcion="";
		String ls_msg_error="";
		
		int li_retorno=0;
		
		//Lista de Sessiones 
		 //usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
        codigo_oficina   =   String.valueOf(session.getAttribute("codigo_oficina"));
        System.out.println("El session codigo_oficina es :" + codigo_oficina);
        /*ls_persona   =   String.valueOf(session.getAttribute("rs_persona"));
        System.out.println("El session ls_persona es :" + ls_persona);
        ls_nombre_oficina   =   String.valueOf(session.getAttribute("nombre_oficina"));
        System.out.println("El session ls_nombre_oficina es :" + ls_nombre_oficina);
	        */
		usuario   = String.valueOf(session.getAttribute("nombreusuario"));
		ls_operacion   = String.valueOf(session.getAttribute("operacionmrmd"));
		
		codigo_documento = ((FFormMantModificar)form).getCodigo_documento();
		codigo_expediente = ((FFormMantModificar)form).getCodigo_expediente();
		descripcion_tipo = ((FFormMantModificar)form).getDescripcion_tipo();
		numero_documento = ((FFormMantModificar)form).getNumero_documento();
		
		fecha = ((FFormMantModificar)form).getFecha();
		hora = ((FFormMantModificar)form).getHora();
		observacion = ((FFormMantModificar)form).getObservacion();
		
		
		//ls_codigo_oficina = ((FFormMantRecepcion)form).getCodigo_oficina();
		estado_movimiento = ((FFormMantModificar)form).getEstado_movimiento();
		secuencia_movimiento = ((FFormMantModificar)form).getSecuencia_movimiento();
		codigo_inicia = ((FFormMantModificar)form).getCodigo_inicia();
		naturaleza_documento = ((FFormMantModificar)form).getNaturaleza_documento();
		
		
		System.out.println("usuario... "+ usuario);
		System.out.println("codigo_documento... "+ codigo_documento);
		System.out.println("codigo_expediente... "+ codigo_expediente);
		System.out.println("codigo_expediente... "+ codigo_expediente);
		System.out.println("numero_documento... "+ numero_documento);
		System.out.println("fecha... "+ fecha);
		System.out.println("hora... "+ hora);
		System.out.println("observacion... "+ observacion);
		
		System.out.println("codigo_oficina... "+ codigo_oficina);
		System.out.println("estado_movimiento... "+ estado_movimiento);
		System.out.println("secuencia_movimiento... "+ secuencia_movimiento);
		
		System.out.println("Modificacion de... Modificacion Derivacion .........." );
		
		/*li_retorno = daoIUsuarioDAO.parametro(cnx);
		
   	 	System.out.println("li_retorno" + li_retorno);
   	 	
   	 	ls_correlativorecepcion=String.valueOf(li_retorno);
   	 	System.out.println("ls_correlativorecepcion" + ls_correlativorecepcion);*/
		
   	 	Date fecha_recep = new Date(); // fecha y hora locales 
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy"); 
		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm"); 
		System.out.println("la fecha es.."+ formatoFecha.format(fecha_recep) ); 
		System.out.println("la hora es.."+ formatoHora.format(fecha_recep) ); 
		
		session.removeAttribute("error");
		session.removeAttribute("mensaje_recepcion");
		session.removeAttribute("mensaje");
		
		String codigo_documento_busqueda_ls = request.getParameter("codigo_documento_busqueda_ls");
		codigo_documento_busqueda_ls = (codigo_documento_busqueda_ls==null)? "":codigo_documento_busqueda_ls;
		
		try {
			
			
			if (tipo.equals("modificar") ){
				
				System.out.println("Si tipo es init " + tipo);
				System.out.println("Listar Modificar Documentos....." );
				
				Collection rs_modificar_doc = new ArrayList();
		        
				String codigo_doc=(String)request.getAttribute("codigo_doc");
				String ls_inicia = (String)request.getAttribute("inicia");
				ls_inicia=ls_inicia==null?"":ls_inicia;
				
				codigo_doc = (codigo_doc==null)? "":codigo_doc;
				codigo_doc = (codigo_doc.equals(""))? codigo_documento_busqueda_ls.trim(): codigo_doc; 
				
				log.info("codigo a buscar "+codigo_doc);
				if(!ls_inicia.equals("SI")){
					rs_modificar_doc = daoIUsuarioDAO.lista_documentos_modificar_deriva(cnx, codigo_oficina,codigo_doc);	
				}
				
				session.setAttribute("operacion",tipo);
				session.setAttribute("rs_modificar_doc",rs_modificar_doc);
				
				if(rs_modificar_doc == null || rs_modificar_doc.isEmpty()){
					session.setAttribute("mostrar_listado", null);
				}else{
					session.setAttribute("mostrar_listado", "SI");
				}
					
					//return (mapping.findForward("exito"));	
			}
			
			else if (tipo.equalsIgnoreCase("editar")){
			
				System.out.println("Si tipo es editar init" + tipo);
				System.out.println("Dentro de Editar en MantRecepcion");	

   	    		System.out.println("Entregar recibir ls_codigo_inicia.equals(ls_codigoPadre) son diferentes" );	
   	    		
   	    		System.out.println("La observacion es...."+ observacion);	
   	    		
   	    		if(observacion.equals("null")){
   	 			observacion="";
   	 			System.out.println("La observacion es null de lo contrario es igual a vacio.");
   	    		}
   	    		System.out.println("La observacion ahora es...."+ observacion);
   	    		
   	    		//String ls_mensaje=daoIUsuarioDAO.of_modificar_doc_deriva(cnx,codigo_oficina,codigo_documento,usuario,fecha,hora,observacion,estado_movimiento,secuencia_movimiento);
   	    		
   	    		//Collection rs_modificar_doc = new ArrayList();
		        //String codigo_doc=(String)session.getAttribute("codigo_doc");
		        
				//rs_modificar_doc = daoIUsuarioDAO.lista_documentos_modificar_deriva(cnx, codigo_oficina,codigo_doc);
			      
					
					
					session.setAttribute("operacion",tipo);
					//session.setAttribute("rs_modificar_doc",rs_modificar_doc);
					//session.setAttribute("mensaje",ls_mensaje);
				//session.setAttribute("fecha_rec", formatoFecha.format(fecha));
                // session.setAttribute("hora", formatoHora.format(fecha));	
   	    		//return (mapping.findForward("exito"));	
   	    		
			
					
			return mapping.getInputForward();
	
		} 
			
			else if (tipo.equalsIgnoreCase("enviar")){
				try {
					System.out.println("Si tipo es editar init" + tipo);
					System.out.println("Dentro de Editar en MantRecepcion");	

	   	    		System.out.println("Entregar recibir ls_codigo_inicia.equals(ls_codigoPadre) son diferentes" );	
	   	    		
	   	    		System.out.println("La observacion es...."+ observacion);	
	   	    		
	   	    		if(observacion.equals("null")){
			   	 			observacion="";
			   	 			System.out.println("La observacion es null de lo contrario es igual a vacio.");
	   	    		}
	   	    		System.out.println("La observacion ahora es...."+ observacion);
	   	    		
	   	    		
	   	    		secuencia_movimiento = funciones.of_max_secuencia_documento_estado_derivado(cnx, codigo_documento);
	   	    		System.out.println("Modificando la secuencia="+secuencia_movimiento);
	   	    		
	   	    		String ls_mensaje ="";
	   	    		
	   	    		if(service.esRegistroDocumentoInterno(codigo_documento, codigo_oficina, secuencia_movimiento)){
	   	    			ls_mensaje = "EL DOCUMENTO NO SE PUEDE REVERTIR POR SER UN DOCUMENTO CON FIRMA DIGITAL!";
	   	    		}else{
	   	    			//ls_mensaje = "DAAAAAA   !";
	   	    			ls_mensaje=daoIUsuarioDAO.of_modificar_doc_deriva(cnx,codigo_oficina,codigo_documento,usuario,fecha,hora,observacion,estado_movimiento,secuencia_movimiento);
	   	    		}
	   	    		
	   	    		
	   	 //   		Collection rs_modificar_doc = new ArrayList();
			        //String codigo_doc = (String)session.getAttribute("codigo_doc");
					//rs_modificar_doc = daoIUsuarioDAO.lista_documentos_modificar_deriva(cnx, codigo_oficina,codigo_doc);
				      
						//session.setAttribute("mostrar_listado", "NO");
	   	    		    session.removeAttribute("rs_modificar_doc");
	   	    			session.removeAttribute("mostrar_listado");
						
						session.setAttribute("operacion",tipo);
						//session.setAttribute("rs_modificar_doc",rs_modificar_doc);
						session.setAttribute("mensaje",ls_mensaje);
					//session.setAttribute("fecha_rec", formatoFecha.format(fecha));
	                // session.setAttribute("hora", formatoHora.format(fecha));	
	   	    		//return (mapping.findForward("exito"));	
	   	    		
				}catch (SQLException e) {
					rollbackConnection(cnx);
					e.printStackTrace();
					session.setAttribute("MsjeError", "Error al actualizar al empleado: " + e.getMessage());
					//closeConnection(cnx);
					//return mapping.getInputForward();
		
				}
						
				return mapping.getInputForward();
		
			} 
			else if (tipo.equalsIgnoreCase("eliminar")){

			try {

   	    			//daoIUsuarioDAO.of_modificar_doc(cnx,codigo_oficina,codigo_documento,usuario,fecha,hora,observacion,estado_movimiento);
   	    			daoIUsuarioDAO.of_eliminar_documento(cnx, codigo_documento, codigo_oficina, secuencia_movimiento);
   	    			
   	    			Collection rs_modificar_doc = new ArrayList();
   			        
   					rs_modificar_doc = daoIUsuarioDAO.lista_documentos_modificar_deriva(cnx, codigo_oficina,"");
   				      
   						
   						
   						session.setAttribute("operacion",tipo);
   						session.setAttribute("rs_modificar_doc",rs_modificar_doc);
   						
                	//return (mapping.findForward("exito"));
                   //ls_pagina = "pag_archivar.jsp";
              
				
			}catch (SQLException e) {
				e.printStackTrace();
				closeConnection(cnx);
				//return mapping.getInputForward();
	
			}
			

		} else if (tipo.equals("copias") ){
			
			System.out.println("Si tipo es init " + tipo);
			System.out.println("Listar Copias Documentos....." );
			
			String ls_secuencia_movimiento="";
			String ls_codigo_documento="";
			Collection rs_lista_doc_deriva = new ArrayList();
	        
			 ls_secuencia_movimiento=request.getParameter("secuencia_movimiento");
			 ls_codigo_documento=request.getParameter("codigo_documento");
			 
			 System.out.println("Si ls_secuencia_movimiento es... " + ls_secuencia_movimiento);
			 System.out.println("Si ls_codigo_documento es.. " + ls_codigo_documento);
			
			 
			rs_lista_doc_deriva = daoIUsuarioDAO.lista_documentos_modificar_deriva_pendientes
			(cnx, ls_codigo_documento,ls_secuencia_movimiento);
		      
				System.out.println("el rs_lista_doc_deriva es ......"+ rs_lista_doc_deriva);
				
				session.setAttribute("operacion",tipo);
				session.setAttribute("rs_lista_doc_deriva",rs_lista_doc_deriva);
				
				return (mapping.findForward("popup"));	
		}
			
		else if (tipo.equalsIgnoreCase("elimcopofi")){

			try {
				
				
				String ls_secuencia_movimiento="";
				String ls_codigo_documento="";
				Collection rs_lista_doc_deriva = new ArrayList();
		        
				 ls_secuencia_movimiento=request.getParameter("secuencia_movimiento");
				 ls_codigo_documento=request.getParameter("codigo_documento");
				 
				 System.out.println("el ls_codigo_documento es ......"+ ls_codigo_documento);
				 System.out.println("el ls_secuencia_movimiento es ......"+ ls_secuencia_movimiento);
				 
   	    			daoIUsuarioDAO.of_eliminar_documento_copia(cnx, codigo_documento,secuencia_movimiento);
   	    			
   	    			Collection rs_modificar_doc = new ArrayList();
   			        
   	    			rs_lista_doc_deriva = daoIUsuarioDAO.lista_documentos_modificar_deriva_pendientes
   	 			(cnx, ls_codigo_documento,ls_secuencia_movimiento);
   	 		      
   	 				System.out.println("el rs_lista_doc_deriva es ......"+ rs_lista_doc_deriva);
   	 				
   	 				session.setAttribute("operacion",tipo);
   	 				session.setAttribute("rs_lista_doc_deriva",rs_lista_doc_deriva);
   	 				
   	 				return (mapping.findForward("popup"));	
   						
                	//return (mapping.findForward("exito"));
                   //ls_pagina = "pag_archivar.jsp";
              
				
			}catch (SQLException e) {
				e.printStackTrace();
				closeConnection(cnx);
				//return mapping.getInputForward();
	
			}
			

		}

		closeConnection(cnx);
		oForm.reset(mapping, request);
		oForm.setTipo("nuevo");
		return (mapping.findForward("exito"));
 
    	   
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
       	return (mapping.findForward("error"));
	}
}
