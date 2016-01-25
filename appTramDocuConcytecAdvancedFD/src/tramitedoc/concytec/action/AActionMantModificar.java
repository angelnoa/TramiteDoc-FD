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
/**
 * AUTOR		: Machuca Ovalle
 * FECHA		: 03-04-2006
 * VERSION		: 1.0
 * DESCRIPCIÓN	: Accion de Logeo  
 */

public class AActionMantModificar extends ValidaSessionAction{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println("DENTRO DEL MANTMODIFICAR DOC CCCCCCXXXXXX...");
		
		HttpSession session = request.getSession(true);
		Connection cnx = getConnection(request, "principal");
		System.out.println("El cnx............" + cnx);	    
	  		
		IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
	
		FFormMantModificar oForm = (FFormMantModificar) form;
		System.out.println("Antes del FFormMantModificar...");	  
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
		String codigo_recepcion ="";
		String asunto_documento ="";
		String observa_movimiento ="";
		String fecha_registro ="";
		String hora_registro ="";
		String procedencia ="";
		String firmadopor ="";
		String dirigido ="";
		String medio ="";
		String codigo_solicitud ="";
		String ls_correlativorecepcion="";
		String ls_msg_error="";
		String observa_documento="";
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
		
		/*codigo_documento = ((FFormMantModificar)form).getCodigo_documento();
		codigo_expediente = ((FFormMantModificar)form).getCodigo_expediente();
		descripcion_tipo = ((FFormMantModificar)form).getDescripcion_tipo();
		numero_documento = ((FFormMantModificar)form).getNumero_documento();
		
		fecha = ((FFormMantModificar)form).getFecha();
		hora = ((FFormMantModificar)form).getHora();
		observacion = ((FFormMantModificar)form).getObservacion();*/
		
		codigo_documento = ((FFormMantModificar)form).getCodigo_documento();
		codigo_expediente = ((FFormMantModificar)form).getCodigo_expediente();
		descripcion_tipo = ((FFormMantModificar)form).getDescripcion_tipo();
		numero_documento = ((FFormMantModificar)form).getNumero_documento();
		
		fecha = ((FFormMantModificar)form).getFecha();
		hora = ((FFormMantModificar)form).getHora();
		//observacion = ((FFormMantModificar)form).getObservacion();
		observa_documento = ((FFormMantModificar)form).getObserva_documento();
		
		
		
		//ls_codigo_oficina = ((FFormMantRecepcion)form).getCodigo_oficina();
		estado_movimiento = ((FFormMantModificar)form).getEstado_movimiento();
		codigo_recepcion = ((FFormMantModificar)form).getCodigo_recepcion();
		secuencia_movimiento = ((FFormMantModificar)form).getSecuencia_movimiento();
		asunto_documento = ((FFormMantModificar)form).getAsunto_documento();
		observa_movimiento = ((FFormMantModificar)form).getObserva_movimiento();
		
		fecha_registro = ((FFormMantModificar)form).getFecha_registro();
		hora_registro = ((FFormMantModificar)form).getHora_registro();
		procedencia = ((FFormMantModificar)form).getProcedencia();
		firmadopor = ((FFormMantModificar)form).getFirmadopor();
		dirigido = ((FFormMantModificar)form).getDirigido();
		medio = ((FFormMantModificar)form).getMedio();
		codigo_solicitud = ((FFormMantModificar)form).getCodigo_solicitud();
		
		
		
		//ls_codigo_oficina = ((FFormMantRecepcion)form).getCodigo_oficina();
		codigo_inicia = ((FFormMantModificar)form).getCodigo_inicia();
		naturaleza_documento = ((FFormMantModificar)form).getNaturaleza_documento();
		
		
		System.out.println("usuario... "+ usuario);
		System.out.println("codigo_documento... "+ codigo_documento);
		System.out.println("codigo_expediente... "+ codigo_expediente);
		System.out.println("codigo_expediente... "+ codigo_expediente);
		System.out.println("numero_documento... "+ numero_documento);
		System.out.println("fecha... "+ fecha);
		System.out.println("hora... "+ hora);
		//System.out.println("observacion... "+ observacion);
		
		System.out.println("estado_movimiento... "+ estado_movimiento);
		System.out.println("codigo_recepcion... "+ codigo_recepcion);
		System.out.println("secuencia_movimiento... "+ secuencia_movimiento);
		System.out.println("asunto_documento... "+ asunto_documento);
		System.out.println("observa_movimiento... "+ observa_movimiento);
		System.out.println("fecha_registro... "+ fecha_registro);
		System.out.println("hora_registro... "+ hora_registro);
		System.out.println("procedencia... "+ procedencia);
		System.out.println("firmadopor... "+ firmadopor);
		System.out.println("dirigido... "+ dirigido);
		System.out.println("medio... "+ medio);
		System.out.println("codigo_solicitud... "+ codigo_solicitud);
		
		
		
		System.out.println("codigo_oficina... "+ codigo_oficina);
		System.out.println("estado_movimiento... "+ estado_movimiento);
		System.out.println("secuencia_movimiento... "+ secuencia_movimiento);
		
		System.out.println("Modificacion de... MantRecepcion .........." );
		
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
		try {
			
			
			if (tipo.equals("modificar") ){
				
				System.out.println("Si tipo es init " + tipo);
				System.out.println("Listar Modificar Documentos....." );
				
				Collection rs_modificar_doc = new ArrayList();
				Collection rs_medios = new ArrayList();
				Collection rs_solicitud = new ArrayList();
				
   	    		rs_medios = daoIUsuarioDAO.of_listar_medios(cnx);
   	    		rs_solicitud = daoIUsuarioDAO.of_lista_solicitud(cnx);
				rs_modificar_doc = daoIUsuarioDAO.lista_documentos_modificar(cnx, codigo_oficina);
				//rs_modificar_doc = daoIUsuarioDAO.lista_recepcion_documentos_normal(cnx, codigo_oficina);
			      
					System.out.println("el rs_modificar_doc es ......"+ rs_modificar_doc);
					
					System.out.println("rs_modificar_doc" + rs_modificar_doc);
					
					session.setAttribute("operacion",tipo);
					session.setAttribute("rs_medios",rs_medios);
					session.setAttribute("rs_solicitud",rs_solicitud);
					session.setAttribute("rs_modificar_doc",rs_modificar_doc);
					
					//return (mapping.findForward("exito"));	
			}
			
			else if (tipo.equalsIgnoreCase("editar")){
			try {
				System.out.println("Si tipo es editar init" + tipo);
				System.out.println("Dentro de Editar en MantRecepcion");	

   	    		System.out.println("Entregar recibir ls_codigo_inicia.equals(ls_codigoPadre) son diferentes" );	
   	    		
   	    		//System.out.println("La observacion es...."+ observacion);	
   	    		
   	    		Collection rs_medios = new ArrayList();
				Collection rs_solicitud = new ArrayList();
				Collection rs_modificar_doc = new ArrayList();
				
   	    		
   	    		
   	    		/*if(observacion.equals("null")){
   	 			observacion="";
   	 			System.out.println("La observacion es null de lo contrario es igual a vacio.");
   	    		}
   	    		System.out.println("La observacion ahora es...."+ observacion);
   	    		*/
   	    		
   	    		daoIUsuarioDAO.of_modificar_doc(cnx,codigo_oficina,codigo_documento,usuario,fecha,hora
   	    				,observa_documento,estado_movimiento,fecha_registro,procedencia,hora_registro,
   	    				firmadopor,dirigido,medio,codigo_solicitud,numero_documento,asunto_documento);
   	    		
   	    		
   	    		rs_medios = daoIUsuarioDAO.of_listar_medios(cnx);
   	    		rs_solicitud = daoIUsuarioDAO.of_lista_solicitud(cnx);
				rs_modificar_doc = daoIUsuarioDAO.lista_documentos_modificar(cnx, codigo_oficina);
   	    		//rs_modificar_doc = daoIUsuarioDAO.lista_recepcion_documentos_normal(cnx, codigo_oficina);
			      
					System.out.println("el rs_modificar_doc es ......"+ rs_modificar_doc);
					
					System.out.println("rs_modificar_doc" + rs_modificar_doc);
					
					session.setAttribute("operacion",tipo);
					session.setAttribute("rs_medios",rs_medios);
					session.setAttribute("rs_solicitud",rs_solicitud);
					session.setAttribute("rs_modificar_doc",rs_modificar_doc);
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
				
				Collection rs_modificar_doc = new ArrayList();
	    		Collection rs_medios = new ArrayList();
				Collection rs_solicitud = new ArrayList();
					
   	    			//daoIUsuarioDAO.of_modificar_doc(cnx,codigo_oficina,codigo_documento,usuario,fecha,hora,observacion,estado_movimiento);
   	    			daoIUsuarioDAO.of_eliminar_documento(cnx, codigo_documento, codigo_oficina, secuencia_movimiento);
   	    			
   	    			
   	    			rs_medios = daoIUsuarioDAO.of_listar_medios(cnx);
   	   	    		rs_solicitud = daoIUsuarioDAO.of_lista_solicitud(cnx);
   					rs_modificar_doc = daoIUsuarioDAO.lista_documentos_modificar(cnx, codigo_oficina);
   	    			//rs_modificar_doc = daoIUsuarioDAO.lista_recepcion_documentos_normal(cnx, codigo_oficina);
   				      
   						System.out.println("el rs_modificar_doc es ......"+ rs_modificar_doc);
   						
   						System.out.println("rs_modificar_doc" + rs_modificar_doc);
   						
   						session.setAttribute("operacion",tipo);
   						session.setAttribute("rs_medios",rs_medios);
   						session.setAttribute("rs_solicitud",rs_solicitud);
   						session.setAttribute("rs_modificar_doc",rs_modificar_doc);
   						
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
