package tramitedoc.concytec.action;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import tramitedoc.concytec.bean.BArchivo;
import tramitedoc.concytec.bean.BMesaPartes;
import tramitedoc.concytec.bean.BUsuario;
import tramitedoc.concytec.dao.interfaces.DocumentosInternosService;
import tramitedoc.concytec.dao.interfaces.IAdministradorDAO;
import tramitedoc.concytec.dao.interfaces.IUsuarioDAO;
import tramitedoc.concytec.dao.sql.SqlAdministradorDAO;
import tramitedoc.concytec.dao.sql.SqlUsuarioDAO;
import tramitedoc.concytec.form.FFormMantSeguimiento;
import tramitedoc.concytec.service.DocumentosInternosServiceImplement;
import tramitedoc.concytec.util.Funciones;
import tramitedoc.concytec.util.IConstante;
import tramitedoc.concytec.util.ValidaSessionAction;
/**
 * AUTOR		: Machuca Ovalle
 * FECHA		: 03-04-2006uplñ
 * VERSION		: 1.0
 * DESCRIPCIÓN	: Accion de Logeo  
 */
public class AActionListaDocumentos extends ValidaSessionAction implements IConstante{
	protected  Log log = LogFactory.getLog(AActionListaDocumentos.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Connection cnx=null;
		try {
			
			log.info("Dentro de AActionListaDocumentos");
			HttpSession session = request.getSession(true);   
			
			cnx = getConnection(request, "principal");
			log.info("El cnx  ==> " + cnx);
			
			IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
			DocumentosInternosService service = new DocumentosInternosServiceImplement();
			Collection rs_upload_doc = new ArrayList();
			Collection rs_upload_doc_temp = new ArrayList();
			
			String  rs_mesa_partes="";
			String  ls_operacion="";
			String  ls_tipo="";
			String  ls_usuario="";
			String  usuario_actual="";
			String  codigoOficinaDelUSuario="";
			String cargo_personal="";
			
			Funciones funciones=new Funciones();
			boolean lb_existen_datos = false;
			
			
			
			ls_tipo=request.getParameter("tipo");
			ls_operacion=request.getParameter("operacion");
			String ls_inicia = request.getParameter("inicia");
			ls_inicia=(ls_inicia==null)?"":ls_inicia;
			request.setAttribute("inicia", ls_inicia);
			
			String ls_document = request.getParameter("document");
			ls_document=(ls_document==null)?"":ls_document;
			
			request.setAttribute("document", ls_document);
						
			if(ls_inicia.equals("SI")){
				session.setAttribute("anulafirmado", ls_document);
			}
			
			
			String ls_codigo_doc=request.getParameter("codigo_doc");
    		request.setAttribute("codigo_doc", ls_codigo_doc);
    		usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
    		codigoOficinaDelUSuario = funciones.of_codigo_oficina(cnx, usuario_actual);
    		session.setAttribute("nombreusuario", usuario_actual);
			session.setAttribute("codigo_oficina", codigoOficinaDelUSuario);
    		
    		
			if (ls_tipo.equals("recepcion")) {
				
				//session.removeAttribute("mensaje_recepcion");
				
				log.info("Dentro de Lista Action documen..");
				/*session.removeAttribute("mensaje_numreg");
				session.removeAttribute("mensaje_numexp");
				session.removeAttribute("mensaje_numenv");*/
				
				session.removeAttribute("codigo_documento_env");
				session.removeAttribute("codigo_expediente_env");
				session.removeAttribute("codigo_documento_env");
				
				
        		log.info("El usuario actual es.." + usuario_actual);
        		
        		log.info("El codigo de Oficina es.." + codigoOficinaDelUSuario);
        		
				if (ls_operacion.equals("R")) {
						 
					//ls_usuario   = String.valueOf(session.getAttribute("nombreusuario"));
					 session.removeAttribute("rs_recepcion_doc");
					 session.removeAttribute("mensaje_recepcion");
					 
					
					
					return (mapping.findForward("recepcion"));
					}
				
				else if (ls_operacion.equals("RN")) { 
					System.out.println("hello");
					//ls_usuario   = String.valueOf(session.getAttribute("nombreusuario"));
					session.removeAttribute("rs_recepcion_doc");
					session.removeAttribute("mensaje_recepcion");					
					session.setAttribute("tiporecepcion", ls_operacion);
					request.setAttribute("ls_inicia", ls_inicia);

					return (mapping.findForward("recepcionnormal"));
					}
				
			}
			
			if (ls_tipo.equals("derivar")) {
				
				log.info("Entry a Derivar ....");
				
				
				//session.removeAttribute("mensaje_recepcion");
				session.removeAttribute("mensaje_numreg");
				session.removeAttribute("mensaje_numexp");
				session.removeAttribute("mensaje_numenv");
				//session.removeAttribute("cargo_personal");
				//session.removeAttribute("codigo_oficina");
				
				String pcodigo_documento = request.getParameter("file");
				String psecuencia = request.getParameter("sec");
				pcodigo_documento=(pcodigo_documento==null)?"":pcodigo_documento;
				psecuencia=(psecuencia==null)?"":psecuencia;
				
				if(!pcodigo_documento.equals("")){
					log.info("Reversion de la recepcion.....");
					
				}
				
				usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
				cargo_personal   =   String.valueOf(session.getAttribute("cargo_personal"));
        		log.info("El usuario actual es.." + usuario_actual);
        		
        		log.info("El codigo de Oficina es.." + codigoOficinaDelUSuario);
        		log.info("El cargo_personal es.." + cargo_personal);
        		
				if (ls_operacion.equals("D")) { 
					 
					/*usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
	        		log.info("El usuario actual es.." + usuario_actual);
	        		*/
					session.setAttribute("cargo_personal", cargo_personal);
					request.setAttribute("ls_inicia", ls_inicia);
					//session.setAttribute("correlativorecepcion", ls_correlativorecepcion);
					
					return (mapping.findForward("derivar"));
					}
				
				
			}
			
			if (ls_tipo.equals("regresarbd")) {
				
				log.info("Entry a Regresar Bandeja Derivacion ....");
				
				session.removeAttribute("mensaje_numreg");
				session.removeAttribute("mensaje_numexp");
				session.removeAttribute("mensaje_numenv");
				
				
				usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
				cargo_personal   =   String.valueOf(session.getAttribute("cargo_personal"));
        		log.info("El usuario actual es.." + usuario_actual);
        		log.info("El codigo de Oficina es.." + codigoOficinaDelUSuario);
        		log.info("El cargo_personal es.." + cargo_personal);
        		
				 
					 
					/*usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
	        		log.info("El usuario actual es.." + usuario_actual);
	        		*/
					session.setAttribute("cargo_personal", cargo_personal);
					request.setAttribute("ls_inicia", "SI");
					
					
					return (mapping.findForward("derivar"));
					
				
				
			}
			 
			if (ls_tipo.equals("seguimiento")) {
				
				log.info("Dentro de Seguimiento XXXXXXXXXXX..");
				BUsuario usu=(BUsuario)session.getAttribute("nombre_usuario");
				session.removeAttribute("seguimientoList");
				
				String ls_flag=(String)session.getAttribute("flag");
				/*Connection cnx = getConnection(request, "principal");
				log.info("El cnx  ==> " + cnx);*/
				
				//usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
        		if(usuario_actual==null || usuario_actual==""){
        			log.info("Entro en usuario_actual null");
        			usuario_actual =  String.valueOf(session.getAttribute("nombreusers"));
        			codigoOficinaDelUSuario = funciones.of_codigo_oficina(cnx, usuario_actual);
        			session.setAttribute("nombreusuario", usuario_actual);
        		}
        			
				log.info("El usuario actual es.." + usuario_actual);
        		log.info("El codigo de Oficina es.." + codigoOficinaDelUSuario);
        		
        		FFormMantSeguimiento formMantSeg = (FFormMantSeguimiento) form;
        		IAdministradorDAO daoIAdministradorDAO = new SqlAdministradorDAO();
        		
        		Collection listatipodoc = daoIAdministradorDAO.of_lista_tipodocumento(cnx);
				Collection listasolicitud = daoIAdministradorDAO.of_lista_solicitud(cnx);
				Collection rs_motivos = daoIUsuarioDAO.of_lista_motivos(cnx);
				String ls_procedencia="";
				String ls_sle_fecha="";
				String ls_sle_fecha_recepcion="";
				String ls_sle_fecha_derivacion="";
				String ls_medio="";
				String ls_codigo_tipo="";
				String oficinaDelFormulario="";
				String ls_codigo_solicitud="";
				String ls_codigo_documento="";
				String ls_codigo_expediente="";
				String ls_firmadopor="";
				String ls_dirigido="";
				String ls_estado="";
				String ls_numero_documento="";
				String ls_doc_resp="";
				String ls_asunto_documento="";
				String ls_codigo_motivo="";
				String ls_sle_fecha_inicio="";
				String ls_sle_fecha_fin="";
				String observacion = "";
				ls_procedencia=formMantSeg.getProcedencia();
				ls_sle_fecha=formMantSeg.getSle_fecha();
	       		 //ls_sle_fecha_recepcion=formMantSeg.getSle_fecha_recepcion();
	       		 //ls_sle_fecha_derivacion=formMantSeg.getSle_fecha_derivacion();
				ls_sle_fecha_inicio=formMantSeg.getSle_fecha_inicio();
				ls_sle_fecha_fin=formMantSeg.getSle_fecha_fin();
	       		 ls_medio=formMantSeg.getMedio();
	       		 ls_codigo_tipo=formMantSeg.getCodigo_tipo();
	       		 
	       		 oficinaDelFormulario=formMantSeg.getCodigo_oficina();
	       		 ls_codigo_solicitud=formMantSeg.getCodigo_solicitud();
	       		 ls_codigo_documento=formMantSeg.getCodigo_documento();
	       		 ls_codigo_expediente=formMantSeg.getCodigo_expediente();
	       		 ls_firmadopor=formMantSeg.getFirmadopor();
	       		 ls_dirigido=formMantSeg.getDirigido();
	       		 ls_estado=formMantSeg.getEstado();
	       		 ls_numero_documento=formMantSeg.getNumero_documento();
	       		 ls_doc_resp=formMantSeg.getDoc_resp();
	       		 ls_asunto_documento=formMantSeg.getAsunto_documento();
	       		 ls_codigo_motivo=formMantSeg.getCodigo_motivo();
	       		 observacion=formMantSeg.getObservacion();
	       		 
	       		 log.info("Observacion="+observacion);
        		 
        		try {
        			
        		
				if (ls_operacion.equals("S")) {
					Collection rs_oficina=null;
					ls_flag=(usu==null || usu.getFlag()==null)?"":usu.getFlag();
					if( ls_flag.equals("S")){
						log.info("Es usuario S");
						rs_oficina = daoIUsuarioDAO.of_lista_oficinas(cnx);	
						
					}else if(ls_flag.equals("M")){
						log.info("Es usuario M");
						rs_oficina = daoIUsuarioDAO.of_lista_oficinas_jefes(cnx,codigoOficinaDelUSuario);	
						
					}else{					
						log.info("Es usuario N o P");
						rs_oficina = daoIUsuarioDAO.of_lista_oficinas(cnx,codigoOficinaDelUSuario);						
					}
					session.setAttribute("rs_oficina_lista", rs_oficina);
					
			log.info("ls_sle_fecha_inicio "+ls_sle_fecha_inicio);	
			Collection listaFrameBusqueda = new ArrayList();
			Collection listaFrameBusqueda_xlsEspecial = new ArrayList();
			
			/**
			 * SI NO INGRESA NADA DEBE DE SETEARSE LA FECHA DE INICIO AL PRIMER DIA DEL MES ACTUAL
			 */
			ls_procedencia = (ls_procedencia==null)? "":ls_procedencia.trim();
			ls_codigo_documento =(ls_codigo_documento==null)? "":ls_codigo_documento.trim();
			ls_sle_fecha =(ls_sle_fecha==null)? "":ls_sle_fecha.trim();
			ls_medio =(ls_medio==null)? "":ls_medio.trim();
			ls_codigo_tipo =(ls_codigo_tipo==null)? "":ls_codigo_tipo.trim();
			ls_codigo_solicitud =(ls_codigo_solicitud==null)? "":ls_codigo_solicitud.trim();
			ls_codigo_expediente =(ls_codigo_expediente==null)? "":ls_codigo_expediente.trim();
			ls_firmadopor =(ls_firmadopor==null)? "":ls_firmadopor.trim();
			ls_dirigido =(ls_dirigido==null)? "":ls_dirigido.trim();
			ls_estado =(ls_estado==null)? "":ls_estado.trim();
			ls_numero_documento =(ls_numero_documento==null)? "":ls_numero_documento.trim();
			ls_doc_resp =(ls_doc_resp==null)? "":ls_doc_resp.trim();
			ls_asunto_documento =(ls_asunto_documento==null)? "":ls_asunto_documento.trim();
			ls_sle_fecha_inicio =(ls_sle_fecha_inicio==null)? "":ls_sle_fecha_inicio.trim();
			ls_sle_fecha_fin =(ls_sle_fecha_fin==null)? "":ls_sle_fecha_fin.trim();
			ls_codigo_motivo =(ls_codigo_motivo==null)? "":ls_codigo_motivo.trim();
			ls_flag =(ls_flag==null)? "":ls_flag.trim();
			observacion =(observacion==null)? "":observacion.trim();

			/*log.info("1"+ls_procedencia+"1");
			log.info("1"+ls_codigo_documento+"1");
			log.info("1"+ls_sle_fecha+"1");
			log.info("1"+ls_medio+"1");
			log.info("1"+ls_codigo_tipo+"1");
			log.info("1"+ls_codigo_solicitud+"1");
			log.info("1"+ls_codigo_expediente+"1");
			log.info("1"+ls_firmadopor+"1");
			log.info("1"+ls_dirigido+"1");
			log.info("1"+ls_estado+"1");
			log.info("1"+ls_numero_documento+"1");
			log.info("1"+ls_doc_resp+"1");
			log.info("1"+ls_asunto_documento+"1");
			log.info("1"+ls_sle_fecha_inicio+"1");
			log.info("1"+ls_sle_fecha_fin+"1");
			log.info("1"+ls_codigo_motivo+"1");
			log.info("1"+ls_flag+"1");
			log.info("1"+observacion+"1");
			
			//oficinaDelFormulario
			//codigoOficinaDelUSuario
			//ls_flag.equals*/
			
			String mesActual = getMesActual();
			String annoActual = getAnioActual();
			
			int mesEnNumero = Integer.parseInt(mesActual)+1;

			if(mesEnNumero>=10){
				session.setAttribute("fecha_inicio", "01/"+mesEnNumero+"/"+annoActual);
			}else{
				session.setAttribute("fecha_inicio", "01/0"+mesEnNumero+"/"+annoActual);
			}
			
			if(ls_procedencia.equals("") && ls_codigo_documento.equals("") && ls_sle_fecha.equals("")  
			 			&& (ls_medio.equals("") || ls_medio.equals("0")) && (ls_codigo_tipo.equals("") || ls_codigo_tipo.equals("0")) &&  (ls_codigo_solicitud.equals("") || ls_codigo_solicitud.equals("0"))  
			 			&& ls_codigo_expediente.equals("") && ls_firmadopor.equals("") && ls_dirigido.equals("") 
			 			&& (ls_estado.equals("") || ls_estado.equals("0")) && ls_numero_documento.equals("") && ls_doc_resp.equals("")
			 			&& ls_asunto_documento.equals("")&& ls_sle_fecha_inicio.equals("")&& ls_sle_fecha_fin.equals("")
			 			&& (ls_codigo_motivo.equals("") || ls_codigo_motivo.equals("0")) && observacion.equals("")){
			 		log.info("Todo vacio--");
			 		ls_sle_fecha_inicio=(String)session.getAttribute("fecha_inicio");
			 		ls_sle_fecha_fin=null;
			 }else{
				 log.info("algo lleno");
			 }
			
			ls_codigo_solicitud=(ls_codigo_solicitud.equals(""))? "0":ls_codigo_solicitud;
			ls_codigo_motivo=(ls_codigo_motivo.equals(""))? "0":ls_codigo_motivo;
			ls_codigo_tipo=(ls_codigo_tipo.equals(""))? "0":ls_codigo_tipo;
			ls_medio=(ls_medio.equals(""))? "0":ls_medio;
			ls_estado=(ls_estado.equals(""))? "0":ls_estado;
			
			if(!ls_inicia.equals("SI")){		
				log.info("1--");
			        listaFrameBusqueda = daoIUsuarioDAO.of_busca_documentos(cnx, codigoOficinaDelUSuario, ls_procedencia,
					ls_sle_fecha, ls_medio, ls_codigo_tipo, oficinaDelFormulario, ls_codigo_solicitud, ls_codigo_documento,
					ls_codigo_expediente,ls_firmadopor,ls_dirigido,ls_estado,ls_numero_documento,ls_doc_resp,
					ls_asunto_documento,ls_sle_fecha_inicio,ls_sle_fecha_fin,ls_codigo_motivo,ls_flag,observacion);

			        /*listaFrameBusqueda_xlsEspecial=daoIUsuarioDAO.of_busca_documentos_especial_xls(cnx, codigoOficinaDelUSuario, ls_procedencia,
					ls_sle_fecha, ls_medio, ls_codigo_tipo, oficinaDelFormulario, ls_codigo_solicitud, ls_codigo_documento,
					ls_codigo_expediente,ls_firmadopor,ls_dirigido,ls_estado,ls_numero_documento,ls_doc_resp,
					ls_asunto_documento,ls_sle_fecha_inicio,ls_sle_fecha_fin,ls_codigo_motivo,ls_flag,observacion);*/
			        
			        formMantSeg.setSeguimientoList(listaFrameBusqueda);
			        session.setAttribute("listaFrameBusquedaTemp", listaFrameBusqueda);
			        
					//session.setAttribute("listaFrameBusqueda_xlsEspecial",listaFrameBusqueda_xlsEspecial);
			       

			}else{
				//ENTRO CON SI
				log.info("2--");
				

				if((Collection)session.getAttribute("listaFrameBusquedaTemp")==null){
					System.out.println("enetre");
						listaFrameBusqueda = daoIUsuarioDAO.of_busca_documentos(cnx, codigoOficinaDelUSuario, ls_procedencia,
						ls_sle_fecha, ls_medio, ls_codigo_tipo, oficinaDelFormulario, ls_codigo_solicitud, ls_codigo_documento,
						ls_codigo_expediente,ls_firmadopor,ls_dirigido,ls_estado,ls_numero_documento,ls_doc_resp,
						ls_asunto_documento,(String)session.getAttribute("fecha_inicio"),null,ls_codigo_motivo,ls_flag,observacion);
						
						log.info(listaFrameBusqueda.size());
						session.setAttribute("listaFrameBusquedaTemp", listaFrameBusqueda);
						formMantSeg.setSeguimientoList(listaFrameBusqueda);
				}else{
					System.out.println("enetre see");
					formMantSeg.setSeguimientoList((Collection)session.getAttribute("listaFrameBusquedaTemp"));
				}
				
			}
			
			//formMantSeg.setSeguimientoList(listaFrameBusqueda);
			//session.setAttribute("rs_lista_todos",listaFrameBusqueda);
			//session.setAttribute("listaFrameBusqueda_xlsEspecial",listaFrameBusqueda_xlsEspecial);
					 
					
					
					session.setAttribute("listatipodoc", listatipodoc);
					session.setAttribute("listasolicitud", listasolicitud);
					
					
					session.setAttribute("procedencia", ls_procedencia);
					session.setAttribute("sle_fecha", ls_sle_fecha);
					session.setAttribute("sle_fecha_inicio", ls_sle_fecha_inicio);
					session.setAttribute("sle_fecha_fin", ls_sle_fecha_fin);
					session.setAttribute("medio", ls_medio);
					session.setAttribute("codigo_tipo", ls_codigo_tipo);
					//session.setAttribute("codigo_oficina", oficinaDelFormulario);
					session.setAttribute("codigo_solicitud", ls_codigo_solicitud);
					session.setAttribute("codigo_documento", ls_codigo_documento);
					session.setAttribute("codigo_expediente", ls_codigo_expediente);
					session.setAttribute("firmadopor", ls_firmadopor);
					session.setAttribute("dirigido", ls_dirigido);
					session.setAttribute("estado", ls_estado);
					session.setAttribute("rs_motivos", rs_motivos);
					
	        		 
					return (mapping.findForward("seguimiento"));
					}
				
        		} 
                catch (Exception e) {
                    e.printStackTrace();
                }
				
			}

			if (ls_tipo.equals("detalleseg")) {
				log.info("Dentro de Detalle SEG..");
				session.removeAttribute("accion");
				session.removeAttribute("tiposegumiento");				
				
				//log.info("Dentro de detalleseg");
				usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
        		//log.info("El usuario actual es.." + usuario_actual);
        		codigoOficinaDelUSuario = funciones.of_codigo_oficina(cnx, usuario_actual);
        		//log.info("El codigo de Oficina es.." + codigo_oficina);
        		
        		String codigo_documento=request.getParameter("codigo_documento");
        		String codigo_expediente=request.getParameter("codigo_expediente");
        		String fecha_rpta=request.getParameter("fecha_rpta");
        		String secuencia_movimiento=request.getParameter("secuencia_movimiento");
        		String numero_referencia=request.getParameter("numero_referencia");
        		
        		String ls_tiposegumiento=request.getParameter("tiposegumiento");
        		log.info("El ls_tiposegumiento es.." + ls_tiposegumiento);
        		log.info("El codigo_documento es.." + codigo_documento);
        		log.info("El codigo_expediente es.." + codigo_expediente);
        		log.info("El fecha_rpta es.." + fecha_rpta);
        		log.info("El secuencia_movimiento es.." + secuencia_movimiento);
        		log.info("El numero_referencia es.." + numero_referencia);
        		
				if (ls_operacion.equals("S")) { 
					log.info("Dentro de SSSSS..");
					if(fecha_rpta==null || fecha_rpta.equals("") ){
						log.info("Si la fecha es null o vacio..");
						session.setAttribute("codigo_oficina", codigoOficinaDelUSuario);
						session.setAttribute("documento", codigo_documento);
						session.setAttribute("secuencia", secuencia_movimiento);
						session.setAttribute("tiposegumiento", ls_tiposegumiento);
						session.setAttribute("numero_referencia", numero_referencia);
						
	            		return (mapping.findForward("recorridofecnull"));
	            		
	            	}else{
	            		log.info("Si la fecha es diferente de null o vacio..");
	            		session.setAttribute("codigo_oficina", codigoOficinaDelUSuario);
	            		session.setAttribute("documento", codigo_documento);
						session.setAttribute("secuencia", secuencia_movimiento);
						session.setAttribute("tiposegumiento", ls_tiposegumiento);
						session.setAttribute("fecha_rpta", fecha_rpta);
						
	            		return (mapping.findForward("recorrido"));
	            		 }
					
					}
				
			}
			
			if (ls_tipo.equals("archivar")) {
				
				usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
        		log.info("El usuario actual es.." + usuario_actual);
        		
        		log.info("El codigo de Oficina es.." + codigoOficinaDelUSuario);
        		
				if (ls_operacion.equals("A")) { 
					 
					/*usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
	        		log.info("El usuario actual es.." + usuario_actual);
	        		*/
					//session.setAttribute("correlativorecepcion", ls_correlativorecepcion);
					
					return (mapping.findForward("archivar"));
					}
				
			}
			
			if (ls_tipo.equals("anular")) {
				
				usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
        		log.info("El usuario actual es.." + usuario_actual);
        		
        		log.info("El codigo de Oficina es.." + codigoOficinaDelUSuario);
        		
        		
        		
				if (ls_operacion.equals("A")) { 
					 
					/*usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
	        		log.info("El usuario actual es.." + usuario_actual);
	        		*/
					//session.setAttribute("correlativorecepcion", ls_correlativorecepcion);
					request.setAttribute("inicia", "SI");
					return (mapping.findForward("anular"));
				}
				
			}
			
			if (ls_tipo.equals("modificar")) {
				
				
				session.removeAttribute("operacionpopup");
				session.removeAttribute("mensaje_reg");
				session.removeAttribute("mensaje_exp");
				session.removeAttribute("mensaje");
				
				usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
        		log.info("El usuario actual es.." + usuario_actual);
        		
        		log.info("El codigo de Oficina es.." + codigoOficinaDelUSuario);
        		
				if (ls_operacion.equals("M")) { 
					 
					/*usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
	        		log.info("El usuario actual es.." + usuario_actual);
	        		*/
					 
					
					//session.setAttribute("correlativorecepcion", ls_correlativorecepcion);
					
					return (mapping.findForward("modificar"));
					}
				
				else if (ls_operacion.equals("MR")) { 
					 
					/*usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
	        		log.info("El usuario actual es.." + usuario_actual);
	        		*/
					session.setAttribute("operacion", ls_operacion);
					//session.setAttribute("correlativorecepcion", ls_correlativorecepcion);
					
					return (mapping.findForward("modificar"));
					}
				else if (ls_operacion.equals("ME")) { 
					 
					log.info("Dentro de Seguimiento..");
					
						usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
		        		//log.info("El usuario actual es.." + usuario_actual);
		        		codigoOficinaDelUSuario = funciones.of_codigo_oficina(cnx, usuario_actual);
		        		//log.info("El codigo de Oficina es.." + codigo_oficina);
		        		
		        		String codigo_documento=request.getParameter("codigo_documento");
		        		String codigo_expediente=request.getParameter("codigo_expediente");
		        		String ls_codigo_oficina=request.getParameter("codigo_oficina");
		        		
		        		log.info("El codigo_documento es.." + codigo_documento);
		        		log.info("El codigo_expediente es.." + codigo_expediente);
		        		log.info("El ls_codigo_oficina es.." + ls_codigo_oficina);
		        		
		        		Collection rs_oficina = daoIUsuarioDAO.of_lista_oficinas(cnx);		 
		    			Collection listaFrameBusqueda = daoIUsuarioDAO.of_busca_documentos_registrados(cnx, codigoOficinaDelUSuario, codigo_documento, codigo_expediente,codigoOficinaDelUSuario);
		    						 
    					//log.info("El listaFrameBusqueda es .."+ listaFrameBusqueda);
    					session.setAttribute("rs_lista_todos",listaFrameBusqueda);     					
    					session.setAttribute("rs_oficina_lista", rs_oficina);
		    					
					return (mapping.findForward("modificardocent"));
					}
				
				else if (ls_operacion.equals("MD")) { 
					 log.info("Entry ListaDocumentos - Modificar - MD");
					/*usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
	        		log.info("El usuario actual es.." + usuario_actual);
	        		*/
					
					session.setAttribute("operacion", ls_operacion);
					request.setAttribute("inicia", ls_inicia);
					
					return (mapping.findForward("modificarderiv"));
					}
				
				
			}
			
			if (ls_tipo.equals("preliquidar")) {
				
				usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
        		log.info("El usuario actual es.." + usuario_actual);
        		
        		log.info("El codigo de Oficina es.." + codigoOficinaDelUSuario);
        		
				if (ls_operacion.equals("P")) {
					//session.setAttribute("correlativorecepcion", ls_correlativorecepcion);
					
					return (mapping.findForward("precerrar"));
					}
				
			}
			
			if (ls_tipo.equals("liquidar")) {
				
				usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
        		log.info("El usuario actual es.." + usuario_actual);
        		
        		log.info("El codigo de Oficina es.." + codigoOficinaDelUSuario);
        		
				if (ls_operacion.equals("L")) { 					 
					
					//session.setAttribute("correlativorecepcion", ls_correlativorecepcion);
					
					return (mapping.findForward("cerrar"));
					}
				
			}
			
			if (ls_tipo.equals("selecrecorrido")) {
				
				log.info("Dentro de selec Recorrido..");
				usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
        		log.info("El usuario actual es.." + usuario_actual);
        		
        		log.info("El codigo de Oficina es.." + codigoOficinaDelUSuario);
        		
        		String ls_tiposegumiento= request.getParameter("tiposegumiento");
        		
        		String ls_codigo_documento=request.getParameter("codigo_documento");
        		String ls_codigo_expediente=request.getParameter("codigo_expediente");
        		String ls_fecha_rpta=request.getParameter("fecha_rpta");
        		String ls_secuencia_movimiento=request.getParameter("secuencia_movimiento");
        		
        		log.info("El ls_tiposegumiento es.." + ls_tiposegumiento);
        		log.info("El ls_codigo_documento es.." + ls_codigo_documento);
        		log.info("El ls_codigo_expediente es.." + ls_codigo_expediente);
        		log.info("El ls_fecha_rpta es.." + ls_fecha_rpta);
        		log.info("El ls_secuencia_movimiento es.." + ls_secuencia_movimiento);
        		
				if (ls_tiposegumiento.equals("R")) { 
					 
					log.info("El ls_tiposegumiento es..RRRRRR....");					
					session.setAttribute("documento", ls_codigo_documento);
					session.setAttribute("secuencia", ls_secuencia_movimiento);
					session.setAttribute("tiposegumiento", ls_tiposegumiento);
					
					if(ls_fecha_rpta.equals("") || ls_fecha_rpta==null ){
						log.info("La fecha es NULL o vacio....");
						return (mapping.findForward("recorridofecnull"));
					}else{
						log.info("La fecha diferente NULL o vacio....");
						return (mapping.findForward("recorrido"));

					}

				}else{
					log.info("El ls_tiposegumiento es..DDDDDD....");
					session.setAttribute("nombreusuario", usuario_actual);
					session.setAttribute("codigo_oficina", codigoOficinaDelUSuario);
					session.setAttribute("documento", ls_codigo_documento);
					session.setAttribute("secuencia", ls_secuencia_movimiento);
					session.setAttribute("tiposegumiento", ls_tiposegumiento);
					
					if(ls_fecha_rpta.equals("") || ls_fecha_rpta==null ){
						log.info("La fecha es NULL o vacio....");
						return (mapping.findForward("recorridofecnull"));
					}else{
						log.info("La fecha diferente NULL o vacio....");
						return (mapping.findForward("recorrido"));

					}
				}
				
			}
			
			if (ls_tipo.equals("modificardoc")) {
				
				session.removeAttribute("accion");
				session.removeAttribute("tiposegumiento");
				session.removeAttribute("operacionper");
				
				log.info("Dentro de Detalle Modificar DOC..");
				
				usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
        		
        		
        		
        		String codigo_documento=request.getParameter("codigo_documento");
        		String codigo_expediente=request.getParameter("codigo_expediente");
        		String secuencia_movimiento=request.getParameter("secuencia_movimiento");
        		
        		log.info("El codigo_documento es.." + codigo_documento);
        		log.info("El codigo_expediente es.." + codigo_expediente);
        		log.info("El secuencia_movimiento es.." + secuencia_movimiento);
        		
        		BMesaPartes BMesaPartesVO=daoIUsuarioDAO.of_consulta_documento(cnx, codigo_documento, secuencia_movimiento);
        		
        		/*
        		 * mostrar si existe archivos adjuntos  mpelaezs
        		 * */
        		int numeroArchivosAdjuntos=0;
        		Collection lista =daoIUsuarioDAO.lista_upload_documentos_detalle(cnx,Integer.parseInt(codigo_documento));
        		if(!lista.equals(null))
        		{	log.info("mpelaezs: ES diferente a vacio, tiene archivos adjuntos: " +lista.size());
        			numeroArchivosAdjuntos=lista.size();        			
        		}
        		session.setAttribute("numeroArchivosAdjuntos",numeroArchivosAdjuntos);
        		/*
        		 * hasta aqui
        		 * */
        		
        		String ls_codigo_of=BMesaPartesVO.getCodigo_oficina();
        		log.info("El ls_codigo_of es.." + ls_codigo_of);
        		Collection rs_oficina = daoIUsuarioDAO.of_lista_oficinas(cnx);
        		Collection rs_destinatario = daoIUsuarioDAO.of_lista_personas_oficinas(ls_codigo_of);
        		Collection rs_solicitud = daoIUsuarioDAO.of_lista_solicitud(cnx);
        		
				log.info("Si la fecha es null o vacio..");
				session.setAttribute("codigo_oficina", codigoOficinaDelUSuario);
				session.setAttribute("documento", codigo_documento);
				session.setAttribute("secuencia", secuencia_movimiento);
				session.setAttribute("docEntrada", BMesaPartesVO);
				session.setAttribute("operacion","M");
				//session.setAttribute("operacionper","T");
				session.setAttribute("rs_oficina", rs_oficina);
				session.setAttribute("rs_destinatario", rs_destinatario);
				session.setAttribute("rs_solicitud", rs_solicitud);
				
	            return (mapping.findForward("modificardoc"));
	            			
			}
			
			if (ls_tipo.equals("uploaddoc")) {
				
				session.removeAttribute("accion");
				session.removeAttribute("tiposegumiento");
				session.removeAttribute("operacionper");
				
				log.info("Dentro de Detalle uploaddoc DOC..");
				usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
        		
        		
        		String ls_nombre_archivo=request.getParameter("nombre_archivo");
        		String valor=request.getParameter("fileroute");
        		String iddetupload_ls=request.getParameter("iddetupload");
        		BArchivo BArchivoVO = new BArchivo();
        		
        		valor = (valor == null)? "":valor;
        		iddetupload_ls= iddetupload_ls==null? "0":iddetupload_ls;
        		
        		log.info("El ls_nombre_archivo es.." + ls_nombre_archivo);
        		log.info("valor > "+valor);
        		
        		//String ruta="/home/PROYECTOS/PROYECTO01/upload/docTramite/"+ls_nombre_archivo;
        		
        		//String ruta="/var/www/virtual/dsicconcytec.com/htdocs/docTramite/"+ls_nombre_archivo;
        		//String ruta="/var/www/virtual/dsicconcytec.com/htdocs/"+ls_nombre_archivo;
        		//String ruta="/upload/docTramite/"+ls_nombre_archivo;
        		//String filePath = "/upload/docTramite";
        		//String ruta="/documents/"+ls_nombre_archivo;
        		//String ruta="C:\\upload\\docTramite\\"+ls_nombre_archivo;
        		
        		File scfile = new File(filePath, ls_nombre_archivo);
        		String nombre_archivo="";
        		if(!iddetupload_ls.equals("0")){
        			BArchivoVO=service.getDetalleFile(iddetupload_ls);
        			nombre_archivo=BArchivoVO.getNombre_archivo();
        			scfile = new File(BArchivoVO.getRutaDetalleUpload(), nombre_archivo);
        			
        			if(valor.equals("F")){
        				
        				nombre_archivo=ls_nombre_archivo;
        				log.info("Nombre Archivo:" +BArchivoVO.getNombre_archivo());
        				scfile = new File(filePath, ls_nombre_archivo);
        			}
        			
        		}else{
        			
        		if(!valor.equals("")){
        			if(valor.equals("F")){
        				
        				//scfile=filePathFirmados + ls_nombre_archivo;
        				
        				scfile = new File(filePathFirmados, ls_nombre_archivo);
        				
        			}
        			if(valor.equals("V")){
        				scfile = new File(filePathVisados, ls_nombre_archivo);
        			}
        			if(valor.equals("O")){
        				scfile = new File(filePath, ls_nombre_archivo);
        			}
        			if(valor.equals("T")){
        				
        				String id = request.getParameter("id");
        				id = id == null ? "0" : id;
        				int idd = Integer.parseInt(id);
        				rs_upload_doc_temp=(Collection) session.getAttribute("rs_upload_doc");
        				
        				if (rs_upload_doc_temp != null && rs_upload_doc_temp.size() > 0) {
        					BArchivoVO = (BArchivo) rs_upload_doc_temp.toArray()[idd];
        					log.info("new File("+filePathTemporal+", "+BArchivoVO.getNombre_archivo_cifrado()+");");
        				    scfile = new File(BArchivoVO.getRutaTemporal(), BArchivoVO.getFecha_hora_actual()+ BArchivoVO.getNombre_archivo_cifrado());
        				    nombre_archivo=BArchivoVO.getNombre_archivo();
        				}    
        			}
        			
        		}}

        		log.info("rutaBase:" +scfile);
        		
        		try {
        			FileInputStream file = new FileInputStream(scfile);
        			int longitud = file.available();
        			byte b[] = new byte[longitud];
            		file.read(b);
            		file.close();
            		
            		response.setHeader("Content-Disposition",
                    		"attachment;filename="+ nombre_archivo);

                    		ServletOutputStream salida = response.getOutputStream();
                    		salida.write(b);
                    		salida.flush();
                    		salida.close();

                    		
                } catch( FileNotFoundException e ) {
                	log.info("Archivo no encontrado..");
                    /* Hacer algo */
                 }catch (IOException io){
                	 log.info("Error de entrada Salida..");
                 }

				log.info("Si la fecha es null o vacio..");
				
				
	            return (null);
	            			
			}
			
			else if (ls_tipo.equals("reporte_pdf")
					|| ls_tipo.equals("reporte_excel")) {

				
				log.info("Dentro de Reporte PDF EXCEL.." );
				
				 FFormMantSeguimiento formMantSeg = (FFormMantSeguimiento) form;
				 
				 usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
	        	log.info("El usuario actual es.." + usuario_actual);
	        	
	        	log.info("El codigo de Oficina es.." + codigoOficinaDelUSuario);
	        		
				String ls_procedencia="";
				String ls_sle_fecha="";
				String ls_sle_fecha_inicio="";
				String ls_sle_fecha_fin="";
				String ls_medio="";
				String ls_codigo_tipo="";
				String ls_codigo_oficina="";
				String ls_codigo_solicitud="";
				String ls_codigo_documento="";
				String ls_codigo_expediente="";
				String ls_firmadopor="";
				String ls_dirigido="";
				String ls_estado="";
				String ls_numero_documento="";
	       		String ls_doc_resp="";
	       		String ls_asunto_documento="";
	       		String ls_codigo_motivo="";
	       		
        		 ls_procedencia=String.valueOf(session.getAttribute("procedencia")).trim();
        		 ls_sle_fecha=String.valueOf(session.getAttribute("sle_fecha")).trim();
        		 ls_sle_fecha_inicio=String.valueOf(session.getAttribute("sle_fecha_inicio")).trim();
        		 ls_sle_fecha_fin=String.valueOf(session.getAttribute("sle_fecha_fin")).trim();
        		 ls_medio=String.valueOf(session.getAttribute("medio")).trim();
        		 ls_codigo_tipo=String.valueOf(session.getAttribute("codigo_tipo")).trim();
        		
        		 ls_codigo_oficina=String.valueOf(session.getAttribute("codigo_oficina")).trim();
        		 ls_codigo_solicitud=String.valueOf(session.getAttribute("codigo_solicitud")).trim();
        		 ls_codigo_documento=String.valueOf(session.getAttribute("codigo_documento")).trim();
        		 ls_codigo_expediente=String.valueOf(session.getAttribute("codigo_expediente")).trim();
        		 ls_firmadopor=String.valueOf(session.getAttribute("firmadopor")).trim();
        		 ls_dirigido=String.valueOf(session.getAttribute("dirigido")).trim();
        		 ls_estado=String.valueOf(session.getAttribute("estado")).trim();
        		 
        		 ls_numero_documento=String.valueOf(session.getAttribute("numero_documento")).trim();
        		 ls_doc_resp=String.valueOf(session.getAttribute("doc_resp")).trim();
        		 ls_asunto_documento=String.valueOf(session.getAttribute("asunto_documento")).trim();
        		 ls_codigo_motivo=String.valueOf(session.getAttribute("codigo_motivo")).trim();
				
        		Collection listaFrameBusquedaReport = daoIUsuarioDAO.of_busca_documentos_report(cnx, codigoOficinaDelUSuario, ls_procedencia,
    					ls_sle_fecha, ls_medio, ls_codigo_tipo, ls_codigo_oficina, ls_codigo_solicitud, ls_codigo_documento,
    					ls_codigo_expediente,ls_firmadopor,ls_dirigido,ls_estado,ls_numero_documento,ls_doc_resp,
    					ls_asunto_documento,ls_sle_fecha_inicio,ls_sle_fecha_fin,ls_codigo_motivo);
        		
        		
        		formMantSeg.setSeguimientoList(listaFrameBusquedaReport);
			
				session.setAttribute("rs_lista_report",listaFrameBusquedaReport );


				
				return (mapping.findForward("reportpdfexcel"));
				//return null;
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
	
	
	public void exportarReporteExcel(String rutaReporte,
			HttpServletResponse response, HttpServletRequest request,
			Map jasperParams, Connection cnxJDBC) throws Exception {

		HttpSession session = request.getSession(true);
		try {
			String ruta = session.getServletContext().getRealPath("/")
					+ "/reportes/" + rutaReporte;

			String ruta_imagen = session.getServletContext().getRealPath("/")
					+ "/img/";

			String ruta_subreporte = session.getServletContext().getRealPath(
					"/")
					+ "/reportes/subreportes/";
			// String ruta =
			// "D:\\eclipse\\workspace\\proyDirectorios\\WebContent\\"
			// + "reportes\\" + rutaReporte;

			log.info("La ruta completa es:" + ruta);
			if (!new File(ruta).exists())
				throw new Exception("Ruta del reporte no existe. </br>" + ruta);

			InputStream entrada = new FileInputStream(ruta);

			Map params = jasperParams == null ? new HashMap() : jasperParams;
			params.put("RPT_IMAGEN", ruta_imagen);

			/*
			 * params .put("rpt_ruta_logo",
			 * "/usr/local/tomcat/webapps/axesflow/layout/img/logo_acceso.jpg");
			 */
			// params.put("REPORT_LOCALE", Locale.US);
			JasperPrint jasper = JasperFillManager.fillReport(entrada, params,
					cnxJDBC);

			response.setContentType("application/vnd.ms-excel");
			ServletOutputStream salida = response.getOutputStream();

			JRExporter exporter = new JRXlsExporter();

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasper);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, salida);

			exporter.exportReport();

			salida.close();

		} catch (Exception sos) {
			// no se cierra el flujo, porque al lanzar esta excepción, todavia
			// reenvia a la pagina de error y el flujo no debe estar cerrado.
			sos.printStackTrace();

		}

	}
	
	public void exportarReportePDF(String rutaReporte,
			HttpServletResponse response, HttpServletRequest request,
			Map jasperParams, Connection cnxJDBC) throws Exception {

		HttpSession session = request.getSession(true);
		try {
			String ruta = session.getServletContext().getRealPath("/")
					+ "/reportes/" + rutaReporte;

			String ruta_imagen = session.getServletContext().getRealPath("/")
					+ "/img/";

			String ruta_subreporte = session.getServletContext().getRealPath(
					"/")
					+ "/reportes/subreportes/";
			// String ruta =
			// "D:\\eclipse\\workspace\\proyDirectorios\\WebContent\\"
			// + "reportes\\" + rutaReporte;

			log.info("La ruta completa es:" + ruta);
			if (!new File(ruta).exists())
				throw new Exception("Ruta del reporte no existe. </br>" + ruta);

			InputStream entrada = new FileInputStream(ruta);

			Map params = jasperParams == null ? new HashMap() : jasperParams;
			params.put("RPT_IMAGEN", ruta_imagen);

			/*
			 * params .put("rpt_ruta_logo",
			 * "/usr/local/tomcat/webapps/axesflow/layout/img/logo_acceso.jpg");
			 */
			// params.put("REPORT_LOCALE", Locale.US);
			JasperPrint jasper = JasperFillManager.fillReport(entrada, params,
					cnxJDBC);

			response.setContentType("application/pdf");
			ServletOutputStream salida = response.getOutputStream();

			JRExporter exporter = new JRPdfExporter();

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasper);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, salida);

			exporter.exportReport();

			salida.close();

		} catch (Exception sos) {
			// no se cierra el flujo, porque al lanzar esta excepción, todavia
			// reenvia a la pagina de error y el flujo no debe estar cerrado.
			sos.printStackTrace();

		}

	}
	
	private String getDiaActual() {
		Calendar c = new GregorianCalendar(); 
		String dia = Integer.toString(c.get(Calendar.DAY_OF_WEEK_IN_MONTH));
		return dia;
	}
	
	private String getAnioActual() {
		Calendar c = new GregorianCalendar(); 
		String anio = Integer.toString(c.get(Calendar.YEAR));
		return anio;
	}

	private String getMesActual() {
		Calendar c = new GregorianCalendar(); 
		String fecha = Integer.toString(c.get(Calendar.MONTH));
		return fecha;
	}
}
