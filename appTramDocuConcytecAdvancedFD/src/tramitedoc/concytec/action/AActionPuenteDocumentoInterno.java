package tramitedoc.concytec.action;


import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

import com.lowagie.text.Image;

import tramitedoc.concytec.bean.BArchivo;
import tramitedoc.concytec.bean.BDocumento;
import tramitedoc.concytec.bean.BInfoDocumento;
import tramitedoc.concytec.bean.BOficinas;
import tramitedoc.concytec.bean.DocumentoInternoBean;
import tramitedoc.concytec.bean.EstandarBean;
import tramitedoc.concytec.dao.interfaces.DocumentosInternosService;
import tramitedoc.concytec.dao.interfaces.IUsuarioDAO;
import tramitedoc.concytec.dao.sql.SqlUsuarioDAO;
import tramitedoc.concytec.form.FFormDocumentoInterno;
import tramitedoc.concytec.form.FFormMantDocumento;
import tramitedoc.concytec.service.DocumentosInternosServiceImplement;
import tramitedoc.concytec.util.Constantes;
import tramitedoc.concytec.util.Funciones;
import tramitedoc.concytec.util.IConstante;
import tramitedoc.concytec.util.PDFcreator;

public class AActionPuenteDocumentoInterno extends Action {
	protected  Log log = LogFactory.getLog(AActionPuenteDocumentoInterno.class);
	  public ActionForward execute(ActionMapping mapping,
			    ActionForm form,
			    HttpServletRequest request,
			    HttpServletResponse response)
			    throws Exception
			  {
		  		log.info("Entry AActionPuenteDocumentoInterno... ");
		  		ActionMessages mensajes = new ActionMessages();
		  		response.setContentType("application/pdf");
		  		response.setCharacterEncoding("UTF-8");
		  		request.setCharacterEncoding("UTF-8");
		  		
		  		 HttpSession session = request.getSession(false);        

			   /*Verificar si la sesion se perdio*/
			   if (session==null){
			        	
			        	return (mapping.findForward("expiracion"));
			        	
			   }   
		  		
			   	//Collection rs_upload_doc_internos_temp = new ArrayList();
		  		DocumentosInternosService service = new DocumentosInternosServiceImplement();	
		  		//FFormDocumentoInterno fformDocumentoInterno = (FFormDocumentoInterno) form;	
		  		//Funciones funciones = new Funciones();
		  		//PDFcreator pdf = new PDFcreator();
		  		String cargo_personal   =   String.valueOf(session.getAttribute("cargo_personal"));
		  		String codigo_oficina_user = (String) session.getAttribute("codigo_oficina_user");
		  		log.info("cargo_personal >"+cargo_personal);
		  		boolean bandera = true;
		  		int es_admitido=1;
		  		
		  		try{
		  			
		  		String tipo_ls = (String) request.getParameter("tipenvproy");	
		  		String codigo_documento = (String) request.getParameter("cdi");
		  		String codigo_expediente = (String) request.getParameter("cei");
		  		String codigo_recepcion = (String) request.getParameter("cri");
		  		String secuencia = (String) request.getParameter("sec");
		  		String ls_tipenv = (String) request.getParameter("tipenv");
		  		String secuencia_inicio = (String) request.getParameter("secini");
		  		String respuesta = "respuesta";
		  		
		  		codigo_documento=(codigo_documento==null)?"":codigo_documento;
		  		codigo_expediente=(codigo_expediente==null)?"":codigo_expediente;
		  		codigo_recepcion=(codigo_recepcion==null)?"":codigo_recepcion;
		  		secuencia=(secuencia==null)?"":secuencia;
		  		ls_tipenv=(ls_tipenv==null)?"":ls_tipenv;
		  		tipo_ls=(tipo_ls==null)?"":tipo_ls;
		  		secuencia_inicio=(secuencia_inicio==null)?"":secuencia_inicio;
		  		
		  		codigo_oficina_user=(codigo_oficina_user==null)?"":codigo_oficina_user;
		  		
		  		
		  		BInfoDocumento BInfoDocumentoVO = new BInfoDocumento();
		  		BInfoDocumentoVO = service.esRegistroDocumentoInterno(codigo_documento,codigo_oficina_user,secuencia,ls_tipenv);
		  		
		  		/***
		  		 * MODIFICADO PARA LISTAR PROYECTOS EN LA PAGINA LISTA DE PROYECTOS
		  		 */
		  		
		  		String cod_proy_ls = (String) request.getParameter("codproy");
	  			cod_proy_ls=(cod_proy_ls==null)?"0":cod_proy_ls;
	  			log.info("codproy "+cod_proy_ls);
	  			
	  			if(!cod_proy_ls.equals("0")){
		  			BInfoDocumentoVO.setInterno(false);
		  		}
		  		/**
		  		 * 
		  		 */
		  		log.info("BInfoDocumentoVO ->"+BInfoDocumentoVO.isInterno());
		  			
		  			if(BInfoDocumentoVO.isInterno()){
			  			log.info("Entre a opcion Documento anexado desde Bandeja Derivacion o Reiterativo --- YA TIENE HISTORIAL");
			  			int codigoDocumentoInterno = BInfoDocumentoVO.getCodigo_documento_interno();
			  			log.info("Codigo obtenido > "+codigoDocumentoInterno);
			  			int tipodoc = BInfoDocumentoVO.getCodigo_tipo_documento_interno();
			  			int estado = BInfoDocumentoVO.getEstado_documento_interno();

		  				log.info("tipodoc = "+tipodoc);
			  			String variable ="";
			  			variable = String.valueOf(codigoDocumentoInterno);
			  			session.setAttribute("ls_codigoDocumentoInterno", variable.trim());
			  			session.setAttribute("ls_codigo_documento", codigo_documento);
				  		//session.setAttribute("ls_codigo_expediente", codigo_expediente);
				  		//session.setAttribute("ls_codigo_recepcion", codigo_recepcion);
				  		session.setAttribute("ls_secuencia", secuencia);
				  		//session.setAttribute("ls_tipenv", ls_tipenv);
				  		
				  		
				  		respuesta = "preparar";

				  		log.info("En formato cadena > "+variable);
				  		log.info("Operacion > "+respuesta);
				  		log.info("Estado documento > "+estado);
				  		String pagina="exito";

		  				if(tipodoc==6){
		  					pagina="exitoccp";
		  				}
		  				if(tipodoc==7){	  		
		  					pagina="exitotdr";
		  				}
		  				
		  				if(estado==5){
		  					//por firmar
				  			if(cargo_personal.equals("3") || cargo_personal.equals("5") || cargo_personal.equals("11")){
				  				respuesta = "firmar";
				  				
				  				request.setAttribute("es_admiitido", es_admitido);
				  				
				  				
				  				
				  			}else{
				  				respuesta = "";
				  				pagina = "noadmitido";
				  				if(BInfoDocumentoVO.getTipo_envio().equals("R")){pagina = "noadmitido_breiterativa";}
				  				if(BInfoDocumentoVO.getTipo_envio().equals("D")){pagina = "noadmitido_bderiva";	}
				  			}
			  			}else{
			  				if(estado==3){
			  				//firmado
			  					if(BInfoDocumentoVO.getTipo_envio().equals("R")){
			  						log.info("Entre a opcion Documento anexado desde Bandeja Reiterativo --- YA ES FIRMADO ---NO TIENE HISTORIAL");
						  			session.setAttribute("ls_codigo_documento", codigo_documento);
							  		session.setAttribute("ls_codigo_expediente", codigo_expediente);
							  		session.setAttribute("ls_codigo_recepcion", codigo_recepcion);
							  		session.setAttribute("ls_secuencia", secuencia);
							  		session.setAttribute("ls_respuesta", "respuesta");
							  		request.setAttribute("ls_tipenv", ls_tipenv);
							  		bandera = false;
							  		pagina = "crearrespuesta";
			  					}
			  					
			  				}else{
			  					if(estado==9){
			  						//observado borrador
			  						log.info("Entre aqui...");

			  					}else {
			  						if(estado==10){
				  						//observado por firmar
			  							if(cargo_personal.equals("3") || cargo_personal.equals("5") || cargo_personal.equals("11")){
							  				respuesta = "firmar";
							  				/**
							  				 * OBTENGO OBSERVACION Y CODIGO MOTIVO
							  				 */
							  				log.info("---x");
							  				BInfoDocumento bInfoAdicional = new BInfoDocumento();
							  				bInfoAdicional = service.getDatosAdicionales(codigo_documento,secuencia_inicio);
							  				request.setAttribute("bInfoAdicional", bInfoAdicional);
							  				
							  				/**
							  				 * FIN
							  				 */
							  				
							  			}else{
							  				log.info("---y");
							  				respuesta = "";
							  				pagina = "noadmitido";
							  				
							  				log.info("-->>> "+BInfoDocumentoVO.getTipo_envio());
							  				if(BInfoDocumentoVO.getTipo_envio().equals("R")){pagina = "noadmitido_breiterativa";}
							  				if(BInfoDocumentoVO.getTipo_envio().equals("D")){pagina = "noadmitido_bderiva";	}
							  				if(BInfoDocumentoVO.getTipo_envio().equals("N")){pagina = "noadmitido_bderiva";	}
							  			}
				  					}
			  					}
			  				}
			  			}
		  				if(bandera){
		  				session.setAttribute("ls_respuesta", respuesta);
		  				request.setAttribute("cargarDocumento", "1");
		  				request.setAttribute("BInfoDocumentoVO_", BInfoDocumentoVO);
		  				}
		  				return mapping.findForward(pagina);

			  		}else{
			  			log.info("Entre a opcion Documento anexado desde Bandeja Derivacion o Reiterativo --- NO TIENE HISTORIAL");
			  			
			  			String oficina_origen_ls = (String) request.getParameter("ofini");
			  			oficina_origen_ls=(oficina_origen_ls==null)?"":oficina_origen_ls;
			  			log.info("oficina_origen_ls "+oficina_origen_ls+" codproy "+cod_proy_ls);
			  			String pagina_ls="exito";
			  			
			  			//if(!cod_proy_ls.equals("")){
			  			if(!cod_proy_ls.equals("0")){
			  				/*log.info("Entre a cargar proyecto --- NO TIENE HISTORIAL");
					  		int tipo_doc_interno_int = service.of_tipo_documento_interno(Integer.parseInt(cod_proy_ls.trim()));
					  		if(tipo_doc_interno_int==6){
					  			pagina_ls="exitoccp";
			  				}
			  				if(tipo_doc_interno_int==7){	  		
			  					pagina_ls="exitotdr";
			  				}	
			  				
			  				session.setAttribute("ls_codigo_documento", codigo_documento);
					  		session.setAttribute("ls_codigo_expediente", codigo_expediente);
					  		session.setAttribute("ls_codigo_recepcion", codigo_recepcion);
					  		session.setAttribute("ls_secuencia", secuencia);
					  		session.setAttribute("ls_respuesta", respuesta);
					  		request.setAttribute("ls_tipenv", ls_tipenv);
					  		request.setAttribute("cargarproyecto", "1");
					  		request.setAttribute("cod_proy_ls", cod_proy_ls);
					  		
				  		
				  			return mapping.findForward(pagina_ls);*/
			  				
			  				log.info("Entre a bandeja de proyecto --- NO TIENEN HISTORIAL");
			  				
			  				
					  		/*int tipo_doc_interno_int = service.of_tipo_documento_interno(Integer.parseInt(cod_proy_ls.trim()));
					  		if(tipo_doc_interno_int==6){
					  			pagina_ls="exitoccp";
			  				}
			  				if(tipo_doc_interno_int==7){	  		
			  					pagina_ls="exitotdr";
			  				}	*/


			  				ArrayList<EstandarBean> estadoDocumentoInternoList=null;
			  				Collection areasAdministrativasList=service.getLista_oficinas();
			  				ArrayList<EstandarBean> tipoDocumentoInternosList=service.getTipoDocumentosInternosLista();	
						    ArrayList<DocumentoInternoBean> listaProyectosxDocumento=service.getListaProyectosRecibidosCodigo(codigo_documento, codigo_oficina_user, secuencia, oficina_origen_ls);
				  			request.getSession().setAttribute("listaProyectosxDocumento",listaProyectosxDocumento);
				  			request.getSession().setAttribute("areasAdministrativasList",areasAdministrativasList);
				  			request.getSession().setAttribute("tipoDocumentoInternosList",tipoDocumentoInternosList);
				  			estadoDocumentoInternoList=service.getEstadoDocumentoInternoLista();
				  			request.getSession().setAttribute("estadoDocumentoInternoList",estadoDocumentoInternoList);//PARA LOS JEFES
					  		
				  			request.setAttribute("nuemro_registro_bandeja", codigo_documento);
				  			return mapping.findForward("ver-bandeja");
			  			}
			  			
			  			
			  			session.setAttribute("ls_codigo_documento", codigo_documento);
				  		session.setAttribute("ls_codigo_expediente", codigo_expediente);
				  		session.setAttribute("ls_codigo_recepcion", codigo_recepcion);
				  		session.setAttribute("ls_secuencia", secuencia);
				  		session.setAttribute("ls_respuesta", respuesta);
				  		request.setAttribute("ls_tipenv", ls_tipenv);
				  		
			  			return mapping.findForward("crearrespuesta");
			  		}
		  		
		  		
		  		

			  }catch (Exception e) {
		            e.printStackTrace();
			  }
			  return mapping.findForward("error");
		  		
}

	
}
