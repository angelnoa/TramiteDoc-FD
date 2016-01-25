package tramitedoc.concytec.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.lowagie.text.Phrase;

import tramitedoc.concytec.bean.BInfoInv;
import tramitedoc.concytec.bean.BUsuario;
import tramitedoc.concytec.bean.DocumentoInternoBean;
import tramitedoc.concytec.bean.EstandarBean;
import tramitedoc.concytec.dao.interfaces.DocumentosInternosService;
import tramitedoc.concytec.form.FFormMantSeguimientoDocumentoInterno;
import tramitedoc.concytec.service.DocumentosInternosServiceImplement;

/**
 * AUTOR		: Moises Pelaez S
 * FECHA		: 03-04-2012
 * VERSION		: 1.0
 * DESCRIPCIÓN	: Carga las listas necesarias para crear un documento interno  
 */

public class AActionListaDocumentosFirmados extends Action {
	protected  Log log = LogFactory.getLog(AActionListaDocumentosFirmados.class);
		private List names = new ArrayList();
	  public ActionForward execute(ActionMapping mapping,
			    ActionForm form,
			    HttpServletRequest request,
			    HttpServletResponse response)
			    throws Exception
			  {response.setCharacterEncoding("UTF-8");
		  		request.setCharacterEncoding("UTF-8");
		  		
		  		HttpSession session = request.getSession(false);        

			        /*Verificar si la sesion se perdio*/
			        if (session==null){
			        	
			        	return (mapping.findForward("expiracion"));
			        	
			        }  
		  		//tipo=seguimiento&operacion=S&inicia=SI
		  		int BORRADOR=0;
		  		int es_admitido=0;
		  		int bandeja=0;
		  		//nuevo
		  		String  codigo_tipo_documento_interno="";
		  		String ls_cod_ofic = "";
		  		String ls_cod_pers = "";
		  		String ls_init ="";
		  		String ls_nom_documento = "";
		  		String ls_nrodoc ="";
		  		int li_operacion=0;
		  		String pagina = "exito";
		  		
		  		
		  		//fin
		  		log.info("Entry AActionListaDocumentosFirmados... ");
		  		/*****REMUEVO LA LISTA DE DOC FIRMADOS INTERNOS y OTROS****/
		  		session.removeAttribute("rs_upload_doc_internos");
		  		session.removeAttribute("cantidad_lista");
		  		session.removeAttribute("docInterno_d");
		  		/**************************************************/
		  		FFormMantSeguimientoDocumentoInterno fformBusquedaDocumentoInterno =(FFormMantSeguimientoDocumentoInterno) form;  
		  		//fformBusquedaDocumentoInterno.setDocumentoInterno(new DocumentoInternoBean());
		  		//fformBusquedaDocumentoInterno.setArchivos(null);
		  		
		  		try{
		  		
		  		/*********AQUI POR ALGUNA RAZON SALTAN ERRORES*********/
		  		String usuario_actual="";
				String ls_codigo_persona = String.valueOf(session.getAttribute("codigo_persona"));
		  		
		  		BUsuario userSystem =(BUsuario) session.getAttribute("nombre_usuario");
		  		if(userSystem==null){
		  			userSystem = new BUsuario();
		  		}
		  		usuario_actual =   String.valueOf(session.getAttribute("nombreusuario"));
		  		
		  		log.info("usuario_actual >>> "+usuario_actual);
		  		userSystem.setC_usuario(usuario_actual);
		  				  		
		  		String cargo_personal   =   String.valueOf(session.getAttribute("cargo_personal"));
		  		log.info("cargo_personal >"+cargo_personal);
		  		/*****************FIN DE ERRORES************************/
		  		
		  		//nuevo
		  		String bandejaxfirmar = (String) session.getAttribute("_bandejaxfirmar");
		  		String ls_para_firma = (String) session.getAttribute("para_firma");
		  		String ls_respuesta = (String) session.getAttribute("ls_respuesta");
		  		String ls_tipo_envio = (String)request.getAttribute("ls_tipenv");
		  		
		  		String nombreArchivoVer = (String) request.getAttribute("nombreArchivoVer");
       	    	String tipoDocumentoVer = (String) request.getAttribute("tipoDocumentoVer");
       	    	String codigoOficinaVer = (String) request.getAttribute("codigoOficinaVer");
		  		
       	    	nombreArchivoVer=(nombreArchivoVer==null)?"":nombreArchivoVer;
       	    	tipoDocumentoVer=(tipoDocumentoVer==null)?"":tipoDocumentoVer;
       	    	codigoOficinaVer=(codigoOficinaVer==null)?"":codigoOficinaVer;
       	    	
       	    	if(!nombreArchivoVer.equals("") && !tipoDocumentoVer.equals("") && !codigoOficinaVer.equals("")){
       	    		request.setAttribute("nombreArchivoVer_", nombreArchivoVer);
	       	    	request.setAttribute("tipoDocumentoVer_", tipoDocumentoVer);
	       	    	request.setAttribute("codigoOficinaVer_", codigoOficinaVer);
       	    	}
       	    			  		
       	    	ls_init = request.getParameter("tipo");
		  		String ls_inicia = request.getParameter("inicia");	  		
		  		codigo_tipo_documento_interno=request.getParameter("tipodoc");
		  		ls_cod_ofic=request.getParameter("cod_ofic");
		  		ls_cod_pers=request.getParameter("cod_pers");
		  		ls_nom_documento=request.getParameter("nomdoc");
		  		ls_nrodoc=request.getParameter("nrodoc");
		  		String ls_operacion=request.getParameter("operacion"); //para el tipo de documento NORMALES, CCP, TDR
		  				  		
		  		ls_operacion=(ls_operacion==null)?"":ls_operacion;
		 		
		  		ls_para_firma=(ls_para_firma==null)?"":ls_para_firma;
		  		ls_inicia=(ls_inicia==null)?"":ls_inicia;
		  		log.info("ls_inicia-> "+ls_inicia);
		  		ls_init=(ls_init==null)?"":ls_init;
		  		log.info("ls_init-> "+ls_init);
		  		
		  		ls_tipo_envio=(ls_tipo_envio==null)? "":ls_tipo_envio;
			  		
		  		codigo_tipo_documento_interno=(codigo_tipo_documento_interno==null)?"":codigo_tipo_documento_interno;
		  		ls_cod_ofic=(ls_cod_ofic==null)?"":ls_cod_ofic;
		  		ls_cod_pers=(ls_cod_pers==null)?"":ls_cod_pers;
		  		ls_nom_documento=(ls_nom_documento==null)?"":ls_nom_documento;
		  		ls_nrodoc=(ls_nrodoc==null)?"":ls_nrodoc;
		  		//fin	
		  		
		  		DocumentosInternosService service = new DocumentosInternosServiceImplement();	
		  		userSystem=service.obtenerResponsabilidad(userSystem);
		  		log.info("es reponsable:"+userSystem.getResponsable());
		  		
		  		
			    ArrayList<EstandarBean> tipoDocumentoInternosList=service.getTipoDocumentosInternosLista();	
			    ArrayList<EstandarBean> tipoDocumentoInternosListTemp=null;
			    
			    tipoDocumentoInternosListTemp=service.getTipoDocumentosInternosListaTemp(0);

			    request.getSession().setAttribute("tipoDocumentoInternosList",tipoDocumentoInternosList);
			    request.getSession().setAttribute("tipoDocumentoInternosListTemp",tipoDocumentoInternosListTemp);
			    
			    ArrayList<EstandarBean> estadoDocumentoInternoList=null;
			    ArrayList<EstandarBean> estadoDocumentoInternoListSecretaria=null;
			    
			    estadoDocumentoInternoList=service.getEstadoDocumentoInternoLista();
			    estadoDocumentoInternoListSecretaria=service.getEstadoDocumentoInternoListaSecretarias();

			    request.getSession().setAttribute("estadoDocumentoInternoList",estadoDocumentoInternoList);//PARA LOS JEFES
		    
			    
			    Collection areasAdministrativasList=service.getLista_oficinas();
			    request.getSession().setAttribute("areasAdministrativasList",areasAdministrativasList);
			    names=service.lista_Personas();
//			    log.info("size: " +names.size());
			    request.getSession().setAttribute("names",names);
			    int codigo_oficina=service.of_codigo_oficina(usuario_actual);

			    fformBusquedaDocumentoInterno.setCodigo_oficina_pertenece(codigo_oficina);
			    
			   				
	  			log.info("Entre a setear >");
	  			session.removeAttribute("ls_codigo_documento");
	  			session.removeAttribute("ls_codigo_expediente");
	  			session.removeAttribute("ls_codigo_recepcion");
	  			session.removeAttribute("ls_secuencia");
	  			session.removeAttribute("ls_respuesta");
				  			
				if (ls_inicia.equals("SI")){					  		
					  		if (ls_init.equals("seguimiento")){
					  			log.info("Lista Inicial ->");
					  			
					  			request.getSession().removeAttribute("reportelistaDocumentosInternos");
					  			request.getSession().removeAttribute("verReporteDocumentoInternos");
					  			
					  		}else{
					  			if(ls_init.equals("busqueda")){
					  				
					  			}else{
					  				if(ls_init.equals("seguimiento_archivos")){
					  					pagina="exito_archivos";
						  			}else{
						  			
						  			}
					  			
					  			}
					  		}//bien
				}
				else{
						
					//veremos
						if (ls_inicia.equals("")){
							if (ls_init.equals("seguimiento")){
					  			log.info("Lista Busqueda ->");
					  			
					  			log.info("Recupero valores");
					  			log.info("entre aqui Busqueda");
					  			
					  			
					  			int xtipo = fformBusquedaDocumentoInterno.getCodigo_tipo_documento_interno();
					  			log.info("Tipo : "+xtipo);
					  			int oficina_pertenece = fformBusquedaDocumentoInterno.getCodigo_oficina_pertenece();
					  			log.info("Cod oficina pertenece >"+oficina_pertenece);
					  			
					  			//
					  			int codigo_registro = fformBusquedaDocumentoInterno.getCodigo_documento();
					  			int codigo_de_doc = fformBusquedaDocumentoInterno.getCodigo_documento_interno_busqueda();
					  			String fechainicio_ls = fformBusquedaDocumentoInterno.getFecha_inicio();
					  			String fechafin_ls = fformBusquedaDocumentoInterno.getFecha_fin();
					  			String dirigidoa = fformBusquedaDocumentoInterno.getDirigido_a();
					  			
					  			
					  			DateFormat df=new SimpleDateFormat ("dd/MM/yyyy");
								Date fecha_inicio = null;
								Date fecha_fin = null;
					  			
								try{
									fecha_inicio = df.parse(fechainicio_ls);  //convierto la fecha de texto a date
								}catch(ParseException ex){}
								
							/*	log.info("Ahora > "+  df.format(fecha_inicio)); //convierto el date a string */
								
								try{
									fecha_fin = df.parse(fechafin_ls);
								}catch(ParseException ex){}
									
					  			log.info("codigo_registro >"+codigo_registro);
					  			log.info("codigo_de_doc >"+codigo_de_doc);
					  			log.info("fechainicio "+fechainicio_ls);
					  			log.info("fechafin >"+fechafin_ls);
					  			log.info("dirigidoa >"+dirigidoa);
					  			log.info("fecha_inicio "+fecha_inicio);
					  			log.info("fecha_fin "+fecha_fin);
					  			
					  			if ((fecha_inicio == null && !fechainicio_ls.equals("")) || (fecha_fin == null && !fechafin_ls.equals(""))) {
						  			fformBusquedaDocumentoInterno.setFecha_inicio(null);
						  			fformBusquedaDocumentoInterno.setFecha_fin(null);
						  			request.setAttribute("mensajefechas", "1");
					  			}else{
					  			
					  			ArrayList<DocumentoInternoBean> listaDocumentosInternos=service.getlistaDocumentosInternosFirmados(fformBusquedaDocumentoInterno.getCodigo_tipo_documento_interno(),fformBusquedaDocumentoInterno.getCodigo_documento(),fformBusquedaDocumentoInterno.getCodigo_documento_interno_busqueda(),fecha_inicio,fecha_fin,fformBusquedaDocumentoInterno.getDirigido_a(),fformBusquedaDocumentoInterno.getCodigo_oficina_pertenece());
					  			
					  			//ArrayList<BInfoInv> listaDocumentosInternos=service.getlistaDocumentosInternosFirmados();
					  			
					  			
					  			fformBusquedaDocumentoInterno.setCodigo_documento_interno_busqueda(null);
					  			fformBusquedaDocumentoInterno.setCodigo_documento(null);
					  			
					  			request.getSession().setAttribute("reportelistaDocumentosInternos",listaDocumentosInternos);
					  			//session.setAttribute("reportelistaDocumentosInternos",listaDocumentosInternos);
					  			if(listaDocumentosInternos.isEmpty()){
					  				request.getSession().setAttribute("verReporteDocumentoInternos","2");
					  				if(fecha_inicio==null) {fformBusquedaDocumentoInterno.setFecha_inicio(null);}
					  				if(fecha_fin==null) {fformBusquedaDocumentoInterno.setFecha_fin(null);}
					  			}else{
					  			request.getSession().setAttribute("verReporteDocumentoInternos","1");
					  			session.setAttribute("oficinas_nombre_ls", service.getSiglasOficina(fformBusquedaDocumentoInterno.getCodigo_oficina_pertenece()));
					  			if(fecha_inicio==null) {fformBusquedaDocumentoInterno.setFecha_inicio(null);}
					  			else{ session.setAttribute("fechainicio_ls_ls", fechainicio_ls);}
					  			
				  				if(fecha_fin==null) {fformBusquedaDocumentoInterno.setFecha_fin(null);}
				  				else{ session.setAttribute("fechafin_ls_ls", fechafin_ls);}
					  			
				  				session.setAttribute("tipo_documento_ls", String.valueOf(fformBusquedaDocumentoInterno.getCodigo_tipo_documento_interno()));
					  			}
					  			}
					  		//Fecha
						/*		DateFormat df_pe =DateFormat.getDateInstance(DateFormat.FULL, Locale.getDefault());
								log.info("verrrrrrrrrr.  "+df_pe.format(new Date(System.currentTimeMillis())));


								SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							
								log.info(":San Borja, "+sdf.format(new Date(System.currentTimeMillis())));

								log.info(": San Borja, "+df_pe.format(new Date(System.currentTimeMillis())));	
					  			
								SimpleDateFormat prueba = new SimpleDateFormat("dd/MM/yyyy"); 
								try{
									fecha_inicio = prueba.parse(fechainicio_ls);  
								}catch(ParseException ex){}
					  			
								log.info("Resultado "+fecha_inicio);
								log.info("Ahora > "+  prueba.format(fecha_inicio));*/
					  		}
							
							if (ls_init.equals("seguimiento_archivos")){

					  			log.info("Lista Busqueda ->seguimiento_archivos");
					  			
					  			
					  			int oficina_destino = fformBusquedaDocumentoInterno.getCodigo_oficina();
					  			int oficina_pertenece = fformBusquedaDocumentoInterno.getCodigo_oficina_pertenece();
				  			
					  			
					  			String fechainicio_ls = fformBusquedaDocumentoInterno.getFecha_inicio();
					  			String fechafin_ls = fformBusquedaDocumentoInterno.getFecha_fin();
					  			
					  			String nombre_archivo = fformBusquedaDocumentoInterno.getNombre_arhivo();
					  			nombre_archivo=(nombre_archivo==null)? "":nombre_archivo;
					  			
					  			DateFormat df=new SimpleDateFormat ("dd/MM/yyyy");
								Date fecha_inicio = null;
								Date fecha_fin = null;
					  			
								try{
									fecha_inicio = df.parse(fechainicio_ls);  //convierto la fecha de texto a date
								}catch(ParseException ex){}
								
							/*	log.info("Ahora > "+  df.format(fecha_inicio)); //convierto el date a string */
								
								try{
									fecha_fin = df.parse(fechafin_ls);
								}catch(ParseException ex){}
									
					  			log.info("Codigo oficina destino : "+oficina_destino);
					  			log.info("Cod oficina pertenece >"+oficina_pertenece);
					  			log.info("fechainicio "+fechainicio_ls);
					  			log.info("fechafin >"+fechafin_ls);
					  			log.info("nombre_archivo >"+nombre_archivo);
					  			log.info("fecha_inicio "+fecha_inicio);
					  			log.info("fecha_fin "+fecha_fin);
					  			
					  			if ((fecha_inicio == null && !fechainicio_ls.equals("")) || (fecha_fin == null && !fechafin_ls.equals(""))) {
						  			fformBusquedaDocumentoInterno.setFecha_inicio(null);
						  			fformBusquedaDocumentoInterno.setFecha_fin(null);
						  			request.setAttribute("mensajefechas", "1");
					  			}else{
					  			
						  			ArrayList<DocumentoInternoBean> listaArchivosBusqueda=service.getlistaArchivosBusqueda(fformBusquedaDocumentoInterno.getCodigo_oficina(),fformBusquedaDocumentoInterno.getCodigo_oficina_pertenece(),nombre_archivo,fecha_inicio,fecha_fin);
						  			
						  			
						  			//fformBusquedaDocumentoInterno.setCodigo_documento_interno_busqueda(null);
						  			//fformBusquedaDocumentoInterno.setCodigo_documento(null);
						  			
						  			request.getSession().setAttribute("listaArchivosBusqueda",listaArchivosBusqueda);
						  			//session.setAttribute("reportelistaDocumentosInternos",listaDocumentosInternos);
						  			if(listaArchivosBusqueda.isEmpty()){
						  				request.getSession().setAttribute("verReporteDocumentoInternos","2");
						  				if(fecha_inicio==null) {fformBusquedaDocumentoInterno.setFecha_inicio(null);}
						  				if(fecha_fin==null) {fformBusquedaDocumentoInterno.setFecha_fin(null);}
						  			}else{
							  			request.getSession().setAttribute("verReporteDocumentoInternos","1");
							  			session.setAttribute("oficinas_nombre_ls", service.getSiglasOficina(fformBusquedaDocumentoInterno.getCodigo_oficina_pertenece()));
							  			
							  			if(fecha_inicio==null) {fformBusquedaDocumentoInterno.setFecha_inicio(null);}
							  			else{ session.setAttribute("fechainicio_ls_ls", fechainicio_ls);}
							  			
						  				if(fecha_fin==null) {fformBusquedaDocumentoInterno.setFecha_fin(null);}
						  				else{ session.setAttribute("fechafin_ls_ls", fechafin_ls);}
							  			
						  				session.setAttribute("tipo_documento_ls", String.valueOf(fformBusquedaDocumentoInterno.getCodigo_tipo_documento_interno()));
						  			}												
					  			}
					  			pagina="exito_archivos";
							}
						}
				}
					  	
				  	
				  log.info("userSystem- "+userSystem);
				  request.getSession().setAttribute("esApto","1");
				  
				  return mapping.findForward(pagina);
			  }
		  		catch (Exception e) {
		            e.printStackTrace();
		      }
		        
		  	return mapping.findForward("error");
}
}