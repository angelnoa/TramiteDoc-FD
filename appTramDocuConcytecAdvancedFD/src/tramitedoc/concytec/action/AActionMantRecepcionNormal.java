package tramitedoc.concytec.action;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import tramitedoc.concytec.dao.interfaces.DocumentosInternosService;
import tramitedoc.concytec.dao.interfaces.IReiterativoDAO;
import tramitedoc.concytec.dao.interfaces.IUsuarioDAO;
import tramitedoc.concytec.dao.sql.SqlReiterativoDAO;
import tramitedoc.concytec.dao.sql.SqlUsuarioDAO;
import tramitedoc.concytec.form.FFormMantRecepcion;
import tramitedoc.concytec.service.DocumentosInternosServiceImplement;
import tramitedoc.concytec.util.ValidaSessionAction;
/**
 * AUTOR		: Machuca Ovalle
 * FECHA		: 03-04-2006
 * VERSION		: 1.0
 * DESCRIPCIÓN	: Accion de Logeo  
 */

public class AActionMantRecepcionNormal extends ValidaSessionAction{
	protected  Log log = LogFactory.getLog(AActionMantRecepcionNormal.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionErrors errors = new ActionErrors();
		log.info("DENTRO DEL MANTRECEPCION NORMAL...");
		
		HttpSession session = request.getSession(true);
		Connection cnx = getConnection(request,"principal");
		log.info("El cnx............" + cnx);	    
	  		
		IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
		IReiterativoDAO rDAO=new SqlReiterativoDAO();
		
		/*************PARA RESCATAR EL DOCUMENTO INTERNO********/
		
		DocumentosInternosService service = new DocumentosInternosServiceImplement();
		
		/*****************/
		
				
		String ls_inicia_ls = String.valueOf(request.getAttribute("ls_inicia"));
		
		FFormMantRecepcion oForm = (FFormMantRecepcion) form;
		log.info("Antes del FFormMantRecepcion...");	  
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
		String codigo_recepcion;
		String secuencia_movimiento;
		String asunto_documento;
		String ls_correlativorecepcion="";
		String observa_movimiento="";
		String ls_codigo_persona="";
		
		String fecha_registro="";
		String hora_registro="";
		String procedencia="";
		String firmadopor="";
		String dirigido="";
		String medio="";
		String codigo_solicitud="";
		String observa_documento="";
		int li_retorno=0;
		String ls_cargo_personal="";
		String codigo_tipo="0";
		String codigooficina="0";
		String cod_ruta="0";
		String cod_modalidad="0";
		String descripcion="";
		String codigo_documento_busqueda="";
		String codigo_expediente_busqueda="";

		//Lista de Sessiones 
		 //usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
        codigo_oficina   =   String.valueOf(session.getAttribute("codigo_oficina"));
        log.info("El session codigo_oficina es :" + codigo_oficina);
        /*ls_persona   =   String.valueOf(session.getAttribute("rs_persona"));
        log.info("El session ls_persona es :" + ls_persona);
        ls_nombre_oficina   =   String.valueOf(session.getAttribute("nombre_oficina"));
        log.info("El session ls_nombre_oficina es :" + ls_nombre_oficina);
	        */
		usuario   = String.valueOf(session.getAttribute("nombreusuario"));
		ls_cargo_personal   =   String.valueOf(session.getAttribute("cargo_personal"));
		ls_codigo_persona =   String.valueOf(session.getAttribute("codigo_persona"));
		
		codigo_documento = ((FFormMantRecepcion)form).getCodigo_documento();
		codigo_expediente = ((FFormMantRecepcion)form).getCodigo_expediente();
		descripcion_tipo = ((FFormMantRecepcion)form).getDescripcion_tipo();
		numero_documento = ((FFormMantRecepcion)form).getNumero_documento();
		
		fecha = ((FFormMantRecepcion)form).getFecha();
		hora = ((FFormMantRecepcion)form).getHora();
		//observacion = ((FFormMantRecepcion)form).getObservacion();
		
		
		//ls_codigo_oficina = ((FFormMantRecepcion)form).getCodigo_oficina();
		estado_movimiento = ((FFormMantRecepcion)form).getEstado_movimiento();
		codigo_recepcion = ((FFormMantRecepcion)form).getCodigo_recepcion();
		secuencia_movimiento = ((FFormMantRecepcion)form).getSecuencia_movimiento();
		asunto_documento = ((FFormMantRecepcion)form).getAsunto_documento();
		codigo_recepcion = ((FFormMantRecepcion)form).getCodigo_recepcion();
		observa_movimiento = ((FFormMantRecepcion)form).getObserva_movimiento();
		
		fecha_registro = ((FFormMantRecepcion)form).getFecha_registro();
		hora_registro = ((FFormMantRecepcion)form).getHora_registro();
		procedencia = ((FFormMantRecepcion)form).getProcedencia();
		firmadopor = ((FFormMantRecepcion)form).getFirmadopor();
		dirigido = ((FFormMantRecepcion)form).getDirigido();
		medio = ((FFormMantRecepcion)form).getMedio();
		codigo_solicitud = ((FFormMantRecepcion)form).getCodigo_solicitud();
		observa_documento = ((FFormMantRecepcion)form).getObserva_documento();
		
		/*log.info("usuario... "+ usuario);
		log.info("codigo_documento... "+ codigo_documento);
		log.info("codigo_expediente... "+ codigo_expediente);
		log.info("codigo_expediente... "+ codigo_expediente);
		log.info("numero_documento... "+ numero_documento);
		log.info("fecha... "+ fecha);
		log.info("hora... "+ hora);*/
		//log.info("observacion... "+ observacion);
		
		/*log.info("codigo_oficina... "+ codigo_oficina);
		log.info("estado_movimiento... "+ estado_movimiento);
		log.info("secuencia_movimiento... "+ secuencia_movimiento);
		log.info("asunto_documento... "+ asunto_documento);
		log.info("codigo_recepcion... "+ codigo_recepcion);
		log.info("observa_movimiento... "+ observa_movimiento);
		log.info("ls_cargo_personal... "+ ls_cargo_personal);
		log.info("ls_codigo_persona... "+ ls_codigo_persona);
		
		log.info("fecha_registro... "+ fecha_registro);
		log.info("hora_registro... "+ hora_registro);
		log.info("procedencia... "+ procedencia);
		log.info("firmadopor... "+ firmadopor);
		log.info("dirigido... "+ dirigido);
		
		log.info("medio... "+ medio);
		log.info("codigo_solicitud... "+ codigo_solicitud);*/
		
		
		log.info("Modificacion de... MantRecepcion .........." );
		
   	 	Date fecha_recep = new Date(); // fecha y hora locales 
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy"); 
		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm"); 
		log.info("la fecha es.."+ formatoFecha.format(fecha_recep) ); 
		log.info("la hora es.."+ formatoHora.format(fecha_recep) ); 
		
		session.removeAttribute("fecha_rec");
		String codigo_documento_busqueda_ls = request.getParameter("codigo_documento_busqueda_ls");
		codigo_documento_busqueda_ls = (codigo_documento_busqueda_ls==null)? "":codigo_documento_busqueda_ls;
		
		try {
			
			if (tipo.equals("buscar") ){
				
				log.info("Si tipo es init " + tipo);
				log.info("Listar Buscar Documentos....." );
				
				Collection rs_recepcion_doc = new ArrayList();
				Collection rs_oficinas = new ArrayList();
				Collection rs_motivos = new ArrayList();
				
					//if(ls_cargo_personal.equals("3") || ls_cargo_personal.equals("10")){
				codigo_documento_busqueda = ((FFormMantRecepcion)form).getCodigo_documento_busqueda();
				codigo_expediente_busqueda = ((FFormMantRecepcion)form).getCodigo_expediente_busqueda();
				
				
				codigo_documento_busqueda = codigo_documento_busqueda_ls;
				
									
				log.info("El codigo_documento_busqueda es...." + codigo_documento_busqueda);
				log.info("El codigo_expediente_busqueda es...." + codigo_expediente_busqueda);
				
				String accesoaoficinas=service.getAccesosaOficinas(ls_codigo_persona);
				if(accesoaoficinas != null){
					accesoaoficinas=codigo_oficina+","+accesoaoficinas;
				}else{
					accesoaoficinas=codigo_oficina;
				}
				
				
					log.info("Si cargo personal es 3 o 10....." );
					
					//rs_recepcion_doc = daoIUsuarioDAO.lista_recepcion_documentos_normal(cnx,codigo_oficina);
					rs_recepcion_doc = daoIUsuarioDAO.lista_recepcion_documentos_buscar(cnx,codigo_oficina,accesoaoficinas,codigo_documento_busqueda,codigo_expediente_busqueda);
				      
					log.info("rs_recepcion_doc Cargo Secretaria, Director" + rs_recepcion_doc);
				
				session.setAttribute("operacion",tipo);
				request.setAttribute("FFormMantRecepcion", null);
				session.setAttribute("rs_recepcion_doc",rs_recepcion_doc);	
					
			}

			else if (tipo.equals("recepcion") ){
				
				log.info("Si tipo es init " + tipo);
				log.info("Listar Recepcion Documentos....." );
				
				Collection rs_recepcion_doc = new ArrayList();
		        
				System.out.println("codigo_documento_busqueda_ls "+codigo_documento_busqueda_ls);
				if(codigo_documento_busqueda_ls.equals("Buscar...") || codigo_documento_busqueda_ls.equals("")){
					ls_inicia_ls = "SI";
				}
				
				String accesoaoficinas=service.getAccesosaOficinas(ls_codigo_persona);
				if(accesoaoficinas != null){
					accesoaoficinas=codigo_oficina+","+accesoaoficinas;
				}else{
					accesoaoficinas=codigo_oficina;
				}
				
				//if(ls_cargo_personal.equals("3") || ls_cargo_personal.equals("10")){
				if(ls_cargo_personal.equals("11") || ls_cargo_personal.equals("13") || ls_cargo_personal.equals("10") || ls_cargo_personal.equals("1") || ls_cargo_personal.equals("3") || ls_cargo_personal.equals("2")){
					//Si es director o secretaria 
					log.info("Si cargo personal es 3 o 10....." );
					log.info("Codigo persona >> "+ls_codigo_persona);
					rs_recepcion_doc = daoIUsuarioDAO.lista_recepcion_documentos_normal(cnx,codigo_oficina,accesoaoficinas,ls_inicia_ls);
				      
					//log.info("rs_recepcion_doc Cargo Secretaria, Director"+ rs_recepcion_doc);
					session.setAttribute("operacion",tipo);
					session.setAttribute("rs_recepcion_doc",rs_recepcion_doc);	
				}else{
					
					log.info("Si cargo personal es diferente....." );
					
					log.info("Codigo persona >> "+ls_codigo_persona);
					rs_recepcion_doc = daoIUsuarioDAO.lista_recepcion_documentos_normal(cnx,codigo_oficina,accesoaoficinas,ls_codigo_persona,ls_inicia_ls);
				      
					//log.info("rs_recepcion_doc Cargo No es Secretaria o Director" + rs_recepcion_doc);
					
					session.setAttribute("operacion",tipo);
					session.setAttribute("rs_recepcion_doc",rs_recepcion_doc);
					
				}
				  
					//return (mapping.findForward("exito"));	
			}
			else if (tipo.equals("observacion")) {
				
				log.info("Entry a observacion ....");

				session.removeAttribute("mensaje_numreg");
				session.removeAttribute("mensaje_numexp");
				session.removeAttribute("mensaje_numenv");
				
				String pcodigo_documento = request.getParameter("valor1");				
				String pcodigo_expediente = request.getParameter("valor2");
				String pcodigo_recepcion = request.getParameter("valor3");
				String psecuencia = request.getParameter("valor4");
				String psecuencia_origen = request.getParameter("valor5");

				
				pcodigo_documento=(pcodigo_documento==null)?"":pcodigo_documento;
				pcodigo_expediente=(pcodigo_expediente==null)?"":pcodigo_expediente;
				pcodigo_recepcion=(pcodigo_recepcion==null)?"":pcodigo_recepcion;
				psecuencia=(psecuencia==null)?"":psecuencia;
				psecuencia_origen=(psecuencia_origen==null)?"":psecuencia_origen;
				
				log.info(pcodigo_documento+","+pcodigo_expediente+","+pcodigo_recepcion+","+psecuencia+","+psecuencia_origen);


			try {
				observacion="";
				estado_movimiento="2";
				System.out.println("Si tipo es editar init" + tipo);
   	    		System.out.println("Entregar recibir ls_codigo_inicia.equals(ls_codigoPadre) son diferentes" );	
   	    		System.out.println("La observacion es...."+ observacion);	
   	    		
   	    		if(observacion.equals("null")){
   	 			observacion="";
   	 				System.out.println("La observacion es null de lo contrario es igual a vacio.");
   	    		}
   	    		System.out.println("Modificando la secuencia="+psecuencia);
   	    		
   	    		String ls_mensaje=daoIUsuarioDAO.of_observar_doc_deriva(cnx,codigo_oficina,pcodigo_documento,usuario,fecha,hora,observacion,estado_movimiento,psecuencia);
   	    		//String ls_mensaje="SI";
   	    		Collection rs_recepcion_doc = new ArrayList();
   	    		
   	    		String accesoaoficinas=service.getAccesosaOficinas(ls_codigo_persona);
				if(accesoaoficinas != null){
					accesoaoficinas=codigo_oficina+","+accesoaoficinas;
				}else{
					accesoaoficinas=codigo_oficina;
				}
				
  	    		if(ls_cargo_personal.equals("3") || ls_cargo_personal.equals("10")){
					
					rs_recepcion_doc = daoIUsuarioDAO.lista_recepcion_documentos_normal(cnx,codigo_oficina,accesoaoficinas,"SI");
				      
					log.info("rs_recepcion_doc Cargo Secretaria, Director");
					
					session.setAttribute("operacion",tipo);
					session.setAttribute("rs_recepcion_doc",rs_recepcion_doc);	
					if(ls_mensaje.equals("SI")){
						request.setAttribute("operacionpopup", "G");
						request.setAttribute("mensaje", "Documento Observado con exito...!");
						request.setAttribute("mensaje_reg", "Nº de Reg: "+pcodigo_documento);
						request.setAttribute("mensaje_exp", "Nº de Exp: "+pcodigo_expediente);
					}else{
						request.setAttribute("operacionpopup", "B");
						request.setAttribute("mensaje", "Documento no se puede Observar...!");
						request.setAttribute("mensaje_reg", "Nº de Reg: "+pcodigo_documento);
						request.setAttribute("mensaje_exp", "Nº de Exp: "+pcodigo_expediente);
					}
					
					
				}else{
					
					rs_recepcion_doc = daoIUsuarioDAO.lista_recepcion_documentos_normal(cnx,codigo_oficina,accesoaoficinas,ls_codigo_persona,"SI");
				      
					log.info("rs_recepcion_doc Cargo No es Secretaria o Director");
					
					session.setAttribute("operacion",tipo);
					session.setAttribute("rs_recepcion_doc",rs_recepcion_doc);
					
					if(ls_mensaje.equals("SI")){
						request.setAttribute("operacionpopup", "G");
						request.setAttribute("mensaje", "Documento Observado con exito...!");
						request.setAttribute("mensaje_reg", "Nº de Reg: "+pcodigo_documento);
						request.setAttribute("mensaje_exp", "Nº de Exp: "+pcodigo_expediente);
					}else{
						request.setAttribute("operacionpopup", "B");
						request.setAttribute("mensaje", "Documento no se puede Observar...!");
						request.setAttribute("mensaje_reg", "Nº de Reg: "+pcodigo_documento);
						request.setAttribute("mensaje_exp", "Nº de Exp: "+pcodigo_expediente);
					}
				}
				request.setAttribute("FFormMantRecepcion", null);
				
   	    		
			}catch (SQLException e) {
				e.printStackTrace();
				closeConnection(cnx);
	
			}
				
			}
			else if (tipo.equalsIgnoreCase("editar_cop")){
				
				session.setAttribute("operacion",tipo);
				
				log.info("codigo_documento "+codigo_documento);
				log.info("secuencia_movimiento "+secuencia_movimiento);
				
				FFormMantRecepcion obj=daoIUsuarioDAO.obtenerDetalleBandejaRecepcion(cnx,codigo_documento,secuencia_movimiento);

				//oForm.setCodigo_documento(bRecepc)
				request.setAttribute("FFormMantRecepcion", obj);
				
				return mapping.getInputForward();
			}
			else if (tipo.equalsIgnoreCase("editar")){
			
				log.info("Si tipo es editar init" + tipo);
				log.info("Dentro de Editar en MantRecepcion");	
   	    		log.info("Entregar recibir ls_codigo_inicia.equals(ls_codigoPadre) son diferentes" );	
   	    		log.info("codigo_documento "+codigo_documento);
				log.info("secuencia_movimiento "+secuencia_movimiento);
   	    		
      	    	 daoIUsuarioDAO.of_recibir_doc_edit(cnx,codigo_oficina,codigo_documento,usuario,fecha,hora,observa_documento,estado_movimiento,codigo_recepcion,secuencia_movimiento);
				
      	    	
   	    		Collection rs_recepcion_doc = new ArrayList();
		        
   	    		/*rs_recepcion_doc = daoIUsuarioDAO.lista_recepcion_documentos_normal(cnx,codigo_oficina);
			      
				log.info("el rs_recepcion_doc es ......"+ rs_recepcion_doc);
				log.info("rs_recepcion_doc" + rs_recepcion_doc);
				
				session.setAttribute("operacion",tipo);
				session.setAttribute("rs_recepcion_doc",rs_recepcion_doc);*/
					
   	    		if(ls_cargo_personal.equals("3") || ls_cargo_personal.equals("10")){
					
					//rs_recepcion_doc = daoIUsuarioDAO.lista_recepcion_documentos_normal(cnx,codigo_oficina);
				      
					log.info("rs_recepcion_doc Cargo Secretaria, Director" + rs_recepcion_doc);
					
					session.setAttribute("operacion",tipo);
					//session.setAttribute("rs_recepcion_doc",rs_recepcion_doc);	
				}else{
					
					//rs_recepcion_doc = daoIUsuarioDAO.lista_recepcion_documentos_normal(cnx,codigo_oficina,ls_codigo_persona);
				      
					log.info("rs_recepcion_doc Cargo No es Secretaria o Director" + rs_recepcion_doc);
					
					session.setAttribute("operacion",tipo);
					//session.setAttribute("rs_recepcion_doc",rs_recepcion_doc);
					
				}

   	    		
			return mapping.getInputForward();
	
		} 
			else if (tipo.equalsIgnoreCase("enviar")){

			try {
				
				mensaje="CODIGO DE RECEPCION GENERADO ES:"+ ls_correlativorecepcion;
   	    		session.setAttribute("mensaje",mensaje);
   	    		
				log.info("Entregar recibir ls_codigo_inicia.equals(ls_codigoPadre) son diferentes" );	
				/*
				if(codigo_oficina.equals("1")){
			
					daoIUsuarioDAO.of_preliquidar_doc(cnx,codigo_oficina,codigo_documento,usuario,
							fecha,hora,observa_documento,estado_movimiento,secuencia_movimiento,
							asunto_documento,codigo_tipo,codigooficina,cod_ruta,cod_modalidad,descripcion);
					
				}else{*/
					
					if(rDAO.esDocumentoReiterado(codigo_documento,secuencia_movimiento)){
						log.info("entry 1");
						rDAO.recibirDocumentoReiterado(codigo_oficina,codigo_documento,usuario,
								fecha,hora,observa_documento,estado_movimiento,codigo_recepcion,
								secuencia_movimiento,asunto_documento,ls_cargo_personal);
					}else{
						log.info("entry 2");
						daoIUsuarioDAO.of_recibir_doc(cnx,codigo_oficina,codigo_documento,usuario,
								fecha,hora,observa_documento,estado_movimiento,codigo_recepcion,
								secuencia_movimiento,asunto_documento,ls_cargo_personal);
					}
					
				//}
					
					
				
				
				if(cnx.isClosed()){
					log.info("cnx.isClosed()");
					cnx = getConnection(request, "principal");
				}
				 
     	    	daoIUsuarioDAO.of_modificar_fecharespuesta(cnx,codigo_oficina,codigo_documento);
  	    		log.info("Despues de Limpiar la fecha de Respuesta 3" );
				
  	    		Collection rs_recepcion_doc = new ArrayList();
		        
				/*  rs_recepcion_doc = daoIUsuarioDAO.lista_recepcion_documentos_normal(cnx,codigo_oficina);
			      
				log.info("el rs_recepcion_doc es ......"+ rs_recepcion_doc);
				log.info("rs_recepcion_doc" + rs_recepcion_doc);
				
				session.setAttribute("operacion",tipo);
				session.setAttribute("rs_recepcion_doc",rs_recepcion_doc);*/
  	    		 
  	    		
  	    		String accesoaoficinas=service.getAccesosaOficinas(ls_codigo_persona);
  	    		 
				if(accesoaoficinas != null){
					accesoaoficinas=codigo_oficina+","+accesoaoficinas;
				}else{
					accesoaoficinas=codigo_oficina;
				}
				
  	    		int[] cantidades =new int[5];
  	    		if(ls_cargo_personal.equals("3") || ls_cargo_personal.equals("10")){
					
					//rs_recepcion_doc = daoIUsuarioDAO.lista_recepcion_documentos_normal(cnx,codigo_oficina,String.valueOf(request.getAttribute("ls_inicia")));
  	    			rs_recepcion_doc = daoIUsuarioDAO.lista_recepcion_documentos_normal(cnx,codigo_oficina,accesoaoficinas,"SI");
  	    			String acceso="";
  	    			cantidades=daoIUsuarioDAO.total_control_recepcion_documentos(codigo_oficina,"",acceso);
  	    			
					log.info("rs_recepcion_doc Cargo Secretaria, Director");
					
					session.setAttribute("operacion",tipo);
					session.setAttribute("rs_recepcion_doc",rs_recepcion_doc);	
					request.setAttribute("messageExito",codigo_documento);
					//agregado por mpelaezs
					errors.add( ActionErrors.GLOBAL_MESSAGE, new ActionMessage("mensaje.registro"));
					saveErrors(request, errors);
					
				}else{
					
					//rs_recepcion_doc = daoIUsuarioDAO.lista_recepcion_documentos_normal(cnx,codigo_oficina,ls_codigo_persona,String.valueOf(request.getAttribute("ls_inicia")));
					rs_recepcion_doc = daoIUsuarioDAO.lista_recepcion_documentos_normal(cnx,codigo_oficina,accesoaoficinas,ls_codigo_persona,"SI");
					String acceso="";
					cantidades=daoIUsuarioDAO.total_control_recepcion_documentos(codigo_oficina,ls_codigo_persona,acceso);  
					log.info("rs_recepcion_doc Cargo No es Secretaria o Director");
					
					session.setAttribute("operacion",tipo);
					session.setAttribute("rs_recepcion_doc",rs_recepcion_doc);
					request.setAttribute("messageExito",codigo_documento);
					//agregado por mpelaezs
					errors.add( ActionErrors.GLOBAL_MESSAGE, new ActionMessage("mensaje.registro"));
					saveErrors(request, errors);
					
				}
  	    		//session.setAttribute("cantidadBandejaRecepcion", cantidades[0]);
				//session.setAttribute("cantidadBandejaAtencionUrgente", cantidades[1]);
				//session.setAttribute("cantidadBandejaFechaRptaPlazo", cantidades[2]);
				//session.setAttribute("cantidadBandejaFechaRptaLimite", cantidades[3]);
				//session.setAttribute("cantidadBandejaFechaRptaFuera", cantidades[4]);
				
				request.setAttribute("FFormMantRecepcion", null);
				
				
			}catch (SQLException e) {
				e.printStackTrace();
				closeConnection(cnx);
				//return mapping.getInputForward();
	
			}
			

		} 

		
		oForm.reset(mapping, request);
		oForm.setTipo("nuevo");
		return (mapping.findForward("exito"));
 
    	   
        } 
		
        catch (Exception e) {
            e.printStackTrace();
        }finally{
        	closeConnection(cnx);
		}
       	return (mapping.findForward("error"));
	}
}
