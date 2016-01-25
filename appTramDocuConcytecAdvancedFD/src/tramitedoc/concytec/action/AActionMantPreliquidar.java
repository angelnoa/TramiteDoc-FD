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

public class AActionMantPreliquidar extends ValidaSessionAction{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println("DENTRO DEL MANTPRELIQUIDAR...");
		
		HttpSession session = request.getSession(true);
		Connection cnx = getConnection(request, "principal");
		System.out.println("El cnx............" + cnx);	    
	  		
		IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
	
		FFormMantPreliquidar oForm = (FFormMantPreliquidar) form;
		System.out.println("Antes del FFormMantPreliquidar...");	  
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
		String codigo_recepcion;
		String asunto_documento;
		
		String codigo_tipo;
		String cod_oficina_rem;
		String cod_ruta;
		String cod_modalidad;
		String descripcion;
		
		
		String ls_correlativorecepcion="";
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
		
		codigo_documento = ((FFormMantPreliquidar)form).getCodigo_documento();
		codigo_expediente = ((FFormMantPreliquidar)form).getCodigo_expediente();
		descripcion_tipo = ((FFormMantPreliquidar)form).getDescripcion_tipo();
		numero_documento = ((FFormMantPreliquidar)form).getNumero_documento();
		
		fecha = ((FFormMantPreliquidar)form).getFecha();
		hora = ((FFormMantPreliquidar)form).getHora();
		observacion = ((FFormMantPreliquidar)form).getObservacion();
		
		
		//ls_codigo_oficina = ((FFormMantRecepcion)form).getCodigo_oficina();
		estado_movimiento = ((FFormMantPreliquidar)form).getEstado_movimiento();
		secuencia_movimiento = ((FFormMantPreliquidar)form).getSecuencia_movimiento();
		codigo_recepcion = ((FFormMantPreliquidar)form).getCodigo_recepcion();
		asunto_documento = ((FFormMantPreliquidar)form).getAsunto_documento();
		
		codigo_tipo = ((FFormMantPreliquidar)form).getCodigo_tipo();
		cod_oficina_rem = ((FFormMantPreliquidar)form).getCod_oficina_rem();
		cod_ruta = ((FFormMantPreliquidar)form).getCod_ruta();
		cod_modalidad = ((FFormMantPreliquidar)form).getCod_modalidad();
		descripcion = ((FFormMantPreliquidar)form).getDescripcion();
		
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
		System.out.println("codigo_recepcion... "+ codigo_recepcion);
		System.out.println("asunto_documento... "+ asunto_documento);
		
		System.out.println("codigo_tipo... "+ codigo_tipo);
		System.out.println("cod_oficina_rem... "+ cod_oficina_rem);
		System.out.println("cod_ruta... "+ cod_ruta);
		System.out.println("cod_modalidad... "+ cod_modalidad);
		System.out.println("descripcion... "+ descripcion);
		
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
		
		 Collection rs_oficina = daoIUsuarioDAO.of_lista_oficinas(cnx);
		 Collection rs_tipodoc_todos = daoIUsuarioDAO.of_lista_tipdoc_todos(cnx);
		 Collection rs_ruta = daoIUsuarioDAO.of_lista_rutas(cnx);
		
		session.removeAttribute("fecha_rec");
		try {
			
			
			if (tipo.equals("preliquidar") ){
				
				System.out.println("Si tipo es init " + tipo);
				System.out.println("Listar Recepcion Documentos....." );
				
				Collection rs_recepcion_doc = new ArrayList();
		        
				if(codigo_oficina.equals("1")){
					
					rs_recepcion_doc = daoIUsuarioDAO.lista_preliquidar_documentos(cnx,codigo_oficina);
				      
					System.out.println("el rs_recepcion_doc es ......"+ rs_recepcion_doc);
				}
				  
					
					session.setAttribute("operacion",tipo);
					session.setAttribute("rs_oficina",rs_oficina);
					session.setAttribute("rs_tipodoc",rs_tipodoc_todos);
					session.setAttribute("rs_ruta",rs_ruta);
					session.setAttribute("rs_recepcion_doc",rs_recepcion_doc);
					
					//return (mapping.findForward("exito"));	
			}
			
			else if (tipo.equalsIgnoreCase("editar")){
			try {
				System.out.println("Si tipo es editar init" + tipo);
				System.out.println("Dentro de Editar en MantPreliquidar");	

   	    		System.out.println("Entregar recibir ls_codigo_inicia.equals(ls_codigoPadre) son diferentes" );	
   	    		
   	    		System.out.println("La observacion es...."+ observacion);	
   	    		
   	    		if(observacion.equals("null")){
   	 			observacion="";
   	 			System.out.println("La observacion es null de lo contrario es igual a vacio.");
   	    		}
   	    		System.out.println("La observacion ahora es...."+ observacion);
   	    		
      	    	 //daoIUsuarioDAO.of_recibir_doc_edit(cnx,codigo_oficina,codigo_documento,usuario,fecha,hora,observacion,estado_movimiento,ls_correlativorecepcion);
				
      	    	
   	    		Collection rs_recepcion_doc = new ArrayList();
		        
   	    		if(codigo_oficina.equals("1")){
					
					rs_recepcion_doc = daoIUsuarioDAO.lista_preliquidar_documentos(cnx,codigo_oficina);
				      
					System.out.println("el rs_recepcion_doc es ......"+ rs_recepcion_doc);
				}
				
				session.setAttribute("operacion",tipo);
				session.setAttribute("rs_oficina",rs_oficina);
				session.setAttribute("rs_tipodoc",rs_tipodoc_todos);
				session.setAttribute("rs_ruta",rs_ruta);
				session.setAttribute("rs_recepcion_doc",rs_recepcion_doc);
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
			else if (tipo.equalsIgnoreCase("enviar")){

			try {
				
				mensaje="CODIGO DE RECEPCION GENERADO ES:"+ ls_correlativorecepcion;
   	    		session.setAttribute("mensaje",mensaje);
   	    
				System.out.println("Entregar recibir ls_codigo_inicia.equals(ls_codigoPadre) son diferentes" );	
     	    	 daoIUsuarioDAO.of_preliquidar_doc(cnx,codigo_oficina,codigo_documento,usuario,fecha,
     	    			 hora,observacion,estado_movimiento,secuencia_movimiento,
     	    			 asunto_documento,codigo_tipo,cod_oficina_rem,cod_ruta,cod_modalidad,descripcion);
				
     	    	daoIUsuarioDAO.of_modificar_fecharespuesta(cnx,codigo_oficina,codigo_documento);
  	    		System.out.println("Despues de Limpiar la fecha de Respuesta 3" );
				
  	    		Collection rs_recepcion_doc = new ArrayList();
		        
  	    		if(codigo_oficina.equals("1")){
					
					rs_recepcion_doc = daoIUsuarioDAO.lista_preliquidar_documentos(cnx,codigo_oficina);
				      
					System.out.println("el rs_recepcion_doc es ......"+ rs_recepcion_doc);
				}
				
				session.setAttribute("operacion",tipo);
				session.setAttribute("rs_oficina",rs_oficina);
				session.setAttribute("rs_tipodoc",rs_tipodoc_todos);
				session.setAttribute("rs_ruta",rs_ruta);
				session.setAttribute("rs_recepcion_doc",rs_recepcion_doc);
				
				
				
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
