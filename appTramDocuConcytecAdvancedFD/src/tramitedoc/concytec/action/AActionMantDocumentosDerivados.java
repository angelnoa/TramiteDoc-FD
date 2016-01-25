package tramitedoc.concytec.action;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import tramitedoc.concytec.dao.interfaces.IUsuarioDAO;
import tramitedoc.concytec.dao.sql.SqlUsuarioDAO;
import tramitedoc.concytec.form.FFormMantDocumentosDerivados;
import tramitedoc.concytec.util.ValidaSessionAction;
/**
 * AUTOR		: Machuca Ovalle
 * FECHA		: 03-04-2006
 * VERSION		: 1.0
 * DESCRIPCIÓN	: Accion de Logeo  
 */

public class AActionMantDocumentosDerivados extends ValidaSessionAction{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession(true);
	  	
		System.out.println("Dentro de AActionMantDocumentosDerivados");
		
		String ls_operacion = request.getParameter("operacion");
		System.out.println("la ls_operacion: " + ls_operacion);
		
		String codigo_oficina;
		String destinatario;
		String codigo_motivo;
		String observacion;
		String fecha_rpta;
		
		String ls_codigoPadre = null;
        String ls_documento = null;
        String ls_secuencia = null;
        String ls_usuario = null;
        String ls_oficina = null;
        String ls_pagina  = null;
        String ls_correla  = null;
        String ls_codigo_inicia  = null;
        String ls_codigo_expediente="";
        String ls_naturaleza_documento="";
        String ls_correlativo_deriva="";
        String ls_descripcion_oficina="";
        
        int ls_correla_conv  = 0;
		codigo_oficina = ((FFormMantDocumentosDerivados)form).getCodigo_oficina();
		destinatario = ((FFormMantDocumentosDerivados)form).getDestinatario();
		codigo_motivo = ((FFormMantDocumentosDerivados)form).getCodigo_motivo();
		observacion = ((FFormMantDocumentosDerivados)form).getObservacion();
		fecha_rpta = ((FFormMantDocumentosDerivados)form).getFecha_rpta();
		
		observacion=observacion.toUpperCase();
		System.out.println("observacion.UPERCASE.. "+ observacion);
		System.out.println(" fecha_rpta.. "+ fecha_rpta);
		//ls_oficina   = String.valueOf(session.getAttribute("oficina"));
		/*session.removeAttribute("documento");
   		session.removeAttribute("cod_padre");
   		session.removeAttribute("correlativo");
   		session.removeAttribute("secuencia");
   		*/
		ls_oficina   = String.valueOf(session.getAttribute("codigo_oficina"));
        ls_documento = String.valueOf(session.getAttribute("documento")); 
        ls_codigoPadre = String.valueOf(session.getAttribute("cod_padre")); 
        ls_usuario   = String.valueOf(session.getAttribute("nombreusuario"));
        ls_correla   = String.valueOf(session.getAttribute("correlativo"));
        ls_secuencia   = String.valueOf(session.getAttribute("secuencia"));
        ls_codigo_inicia   = String.valueOf(session.getAttribute("codigo_inicia"));
        ls_codigo_expediente = String.valueOf(session.getAttribute("codigo_expediente"));
        ls_naturaleza_documento = String.valueOf(session.getAttribute("naturaleza_documento"));
        ls_correlativo_deriva = String.valueOf(session.getAttribute("correlativo_deriva"));
        ls_descripcion_oficina = String.valueOf(session.getAttribute("descripcion_oficina"));
        
        System.out.println(" ls_oficina.. "+ ls_oficina);
        System.out.println(" ls_documento.. "+ ls_documento);
        System.out.println(" ls_codigoPadre.. "+ ls_codigoPadre);
        System.out.println(" ls_usuario.. "+ ls_usuario);
        System.out.println(" ls_correla.. "+ ls_correla);
        System.out.println(" ls_secuencia.. "+ ls_secuencia);
        System.out.println(" ls_codigo_inicia.. "+ ls_codigo_inicia);
        ls_correla_conv=Integer.parseInt(ls_correla.trim());
        System.out.println(" ls_correla_conv.. "+ ls_correla_conv);
        System.out.println(" ls_codigo_expediente.. "+ ls_codigo_expediente);
        System.out.println(" ls_naturaleza_documento.. "+ ls_naturaleza_documento);
        System.out.println(" ls_descripcion_oficina.. "+ ls_descripcion_oficina);
        
		try {
			
			Connection cnx = getConnection(request, "principal");
			System.out.println("El cnx  ==> " + cnx);
			
			IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO(); 
			
        	int li_operacion =0;
        	
        	if (ls_operacion.equals("N")){
                li_operacion = 1;
            }else if (ls_operacion.equals("M")){
    	            li_operacion = 2;
    	    } else if (ls_operacion.equals("E")){
                li_operacion = 3;
    	    } 
        	
        	switch(li_operacion){
       	 case 1:
       	 	 	
       	     	session.setAttribute("operacion", ls_operacion);
       	     	System.out.println("Ingresando Documento.........." );
       	     
       	     //li_retorno = ln_documentos.of_registrar_derivacion_mismo( ls_oficina, codOficina, codMotivo, observaciones, codPersona, ls_documento, ls_secuencia, ls_usuario, ls_codigoPadre) ;
       	     	if(ls_documento.equals(ls_codigoPadre)){
       	     		
       	     		
       	     		if(ls_naturaleza_documento.equals("I")){
       	     			
       	     		System.out.println("El ls_Documento es igual a codigo padre y el documento es Interno" );	
           	     	/*daoIUsuarioDAO.of_registrar_derivacion_interno(cnx,ls_oficina,codigo_oficina,codigo_motivo,observacion
           	    			,destinatario,ls_documento,ls_secuencia,ls_usuario,ls_codigoPadre,ls_correla_conv,fecha_rpta);
           	    	*/
           	    	System.out.println("Despues del Ingreso Documento,si codigo inicial es igual a cod documento" );	
       	     			
       	     		}else{
       	     			
       	     		System.out.println("El ls_Documento es igual a codigo padre y el documento es de Entrada" );	
           	     	//daoIUsuarioDAO.of_registrar_derivacion_mismo(cnx,ls_oficina,codigo_oficina,codigo_motivo,observacion
           	    	//		,destinatario,ls_documento,ls_secuencia,ls_usuario,ls_codigoPadre,ls_correla_conv,fecha_rpta,ls_codigo_expediente,ls_descripcion_oficina);
           	    	
           	    	System.out.println("Despues del Ingreso Documento,si codigo inicial es igual a cod documento" );	
       	     		}
       	     	
       	     	}else{
       	     		
       	     	System.out.println("El ls_Documento es diferente a codigo padre" );	
       	     	
       	     	daoIUsuarioDAO.of_registrar_derivacion_intent(cnx,ls_oficina,codigo_oficina,codigo_motivo,observacion
       	    			,destinatario,ls_documento,ls_secuencia,ls_usuario,ls_codigoPadre,ls_correla_conv,fecha_rpta,ls_correlativo_deriva,ls_descripcion_oficina);
       	    	
       	    	System.out.println("Despues del Ingreso Documento y Direccionando,si codigo inicial es diferente a cod documento" );	
       	    	
       	     	}
       	    	
				return (mapping.findForward("exito"));	
       	   
       	  
       	 case 2:
        	 	
                System.out.println("Modificacion de... Documento.........." );
               
              
				return (mapping.findForward("exito"));	
       	 case 3:
         	 	
             
                
				return (mapping.findForward("exito"));	
       	 }
    	   
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
       	return (mapping.findForward("error"));
	}
}
