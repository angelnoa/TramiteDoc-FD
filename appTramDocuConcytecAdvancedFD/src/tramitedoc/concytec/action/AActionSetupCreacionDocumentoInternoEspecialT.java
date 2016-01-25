package tramitedoc.concytec.action;

import java.util.ArrayList;
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

import tramitedoc.concytec.bean.BInfoDocumento;
import tramitedoc.concytec.bean.BUsuario;
import tramitedoc.concytec.bean.DocumentoInternoBean;
import tramitedoc.concytec.bean.EstandarBean;
import tramitedoc.concytec.dao.interfaces.DocumentosInternosService;
import tramitedoc.concytec.dao.interfaces.IUsuarioDAO;
import tramitedoc.concytec.dao.sql.SqlUsuarioDAO;
import tramitedoc.concytec.form.FFormDocumentoInternoEspecialT;
import tramitedoc.concytec.service.DocumentosInternosServiceImplement;

/**
 * AUTOR		: Jesus Azañero
 * FECHA		: 03-04-2012
 * VERSION		: 1.0
 * DESCRIPCIÓN	: Carga las listas necesarias para crear un documento interno especial
 */

public class AActionSetupCreacionDocumentoInternoEspecialT extends Action {
	protected  Log log = LogFactory.getLog(AActionSetupCreacionDocumentoInternoEspecialT.class);
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
		  		
		  		int BORRADOR=0;
		  		int es_admitido=0;
		  		int bandeja=0;
		  		int es_observado=0;
		  		//nuevo
		  		String  codigo_tipo_documento_interno="";
		  		String ls_cod_ofic = "";
		  		String ls_cod_pers = "";
		  		String ls_init ="";
		  		String ls_nom_documento = "";
		  		String ls_nrodoc ="";
		  		int li_operacion=2;
		  		String pagina = "exito";
		  		//fin
		  		
		  		log.info("Entry AActionSetupCreacionDocumentoInternoEspecialT... ");
		  		/*****REMUEVO LA LISTA DE DOC FIRMADOS INTERNOS y OTROS****/
		  		session.removeAttribute("rs_upload_doc_internos");
		  		session.removeAttribute("cantidad_lista");
		  		session.removeAttribute("docInterno_d");
		  		/**************************************************/
		  		FFormDocumentoInternoEspecialT fformDocumentoInterno =(FFormDocumentoInternoEspecialT) form;  
		  		fformDocumentoInterno.setDocumentoInterno(new DocumentoInternoBean());
		  		fformDocumentoInterno.setArchivos(null);
		  		
		  		try{
		  		
		  		/*********AQUI POR ALGUNA RAZON SALTAN ERRORES*********/
		  		BUsuario userSystem =(BUsuario) session.getAttribute("nombre_usuario");
		  		log.info("Sale algo? >>"+userSystem.getC_usuario());			  		
		  		String usuario_actual   =   String.valueOf(session.getAttribute("nombreusuario"));
		  		userSystem.setC_usuario(usuario_actual);
		  				  		
		  		String cargo_personal   =   String.valueOf(session.getAttribute("cargo_personal"));
		  		log.info("cargo_personal >"+cargo_personal);
		  		IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
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
       	    	
		  		ls_respuesta=(ls_respuesta==null)?"":ls_respuesta;
		  		log.info("ls_respuesta > "+ls_respuesta);
		  		
		  		

		  		ls_init = request.getParameter("tipo");
		  		String ls_inicia = request.getParameter("inicia");
		  		String temp=(ls_init==null)?"":ls_init;
		  		log.info("temp - >"+temp);
		  		
		  		ls_inicia=(ls_inicia==null)? "":ls_inicia;
		  		ls_tipo_envio=(ls_tipo_envio==null)? "":ls_tipo_envio;
		  		
		  		codigo_tipo_documento_interno=request.getParameter("tipodoc");
		  		ls_init = (String) request.getSession().getAttribute("confirma");
		  		ls_cod_ofic=request.getParameter("cod_ofic");
		  		ls_cod_pers=request.getParameter("cod_pers");
		  		ls_nom_documento=request.getParameter("nomdoc");
		  		ls_nrodoc=request.getParameter("nrodoc");
		  		String ls_operacion=request.getParameter("operacion"); //para el tipo de documento NORMALES, CCP, TDR
		  				  		
		  		ls_operacion=(ls_operacion==null)?"":ls_operacion;
		  		
		  		String confirma=(ls_init==null)?"":ls_init;
		  		
		  		ls_para_firma=(ls_para_firma==null)?"":ls_para_firma;
		  		ls_init=(ls_init==null)?ls_para_firma:ls_init;
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
			    request.getSession().setAttribute("esApto","1");
			    
			    request.getSession().setAttribute("tipoDocumentoInternosList",tipoDocumentoInternosList);
			    request.getSession().setAttribute("tipoDocumentoInternosListTemp",tipoDocumentoInternosListTemp);
			    
			    ArrayList<EstandarBean> estadoDocumentoInternoList=null;
			    ArrayList<EstandarBean> estadoDocumentoInternoListSecretaria=null;
			    
			    estadoDocumentoInternoList=service.getEstadoDocumentoInternoLista();
		    	estadoDocumentoInternoListSecretaria=service.getEstadoDocumentoInternoListaSecretarias();

			    request.getSession().setAttribute("estadoDocumentoInternoList",estadoDocumentoInternoList);//PARA LOS JEFES
			    request.getSession().setAttribute("estadoDocumentoInternoListSecretaria",estadoDocumentoInternoListSecretaria);//PARA LOS JEFES
			    
	    
			    Collection areasAdministrativasList=service.getLista_oficinas();
			    request.getSession().setAttribute("areasAdministrativasList",areasAdministrativasList);
			    names=service.lista_Personas();
//			    log.info("size: " +names.size());
			    request.getSession().setAttribute("names",names);
			    int codigo_oficina=service.of_codigo_oficina(usuario_actual);
			    String firmado_por=service.of_nombre_director_oficina(codigo_oficina);			    
			    
			   
			    fformDocumentoInterno.setCodigo_oficina(7);
			    Collection rs_destinatario = null;
			    rs_destinatario=service.getPersonal(7);
			    request.getSession().setAttribute("rs_destinatario",rs_destinatario);
			    
			    
			    fformDocumentoInterno.setAsunto("CONTRATACION DE SERVICIO PARA ");
			    fformDocumentoInterno.setJustificacion("El Consejo Nacional de Ciencia, Tecnología e Innovación Tecnológica, como ente rector del  SINACYT, ");
			    
			    fformDocumentoInterno.setPlazo("El plazo del servicio será como máximo 15 días a partir de la firma de la orden de servicio.");
			    fformDocumentoInterno.setValor("S/. ");
			    fformDocumentoInterno.setFinanciamiento("Recursos propios.");
			    fformDocumentoInterno.setModalidad("De acuerdo con lo que establezca el Órgano encargado de contrataciones.");
			    fformDocumentoInterno.setResponsable_coordinacion("Personal del Órgano encargado de la contratación.");
			    fformDocumentoInterno.setResponsable_conformidad("La supervisión del servicio estará a cargo del Director de la ");
			    fformDocumentoInterno.setFirmado_por(firmado_por);
			    
			    fformDocumentoInterno.setCodigo_documento(BORRADOR);
			    fformDocumentoInterno.setCodigo_estado_documento(1);
			    fformDocumentoInterno.setCodigo_tipo_documento_interno(7);//default 1 CCP
			    fformDocumentoInterno.setChecked(false);
			    fformDocumentoInterno.setCodigo_oficina_pertenece(codigo_oficina);

			    if(ls_tipo_envio.equals("R")){
			    	fformDocumentoInterno.setTipo_envio(ls_tipo_envio);
			    }
			    
			    
			    if(ls_init.equals("") && ls_respuesta.equals("firmar")){
					 ls_init = "firma";
				 }
			    
			    if(ls_inicia.equals("SI")){
		  			log.info("Entre a setear >");
		  			session.removeAttribute("ls_codigo_documento");
		  			session.removeAttribute("ls_codigo_expediente");
		  			session.removeAttribute("ls_codigo_recepcion");
		  			session.removeAttribute("ls_secuencia");
		  			session.removeAttribute("ls_respuesta");
		  			
		  		}
			    
			    log.info("bandejaxfirmar-> "+bandejaxfirmar);
		  		log.info("ls_init-> "+ls_init); 
				 
				  		if(ls_init.equals("")){
				  		//cargar todos los documentos creados
				  			log.info("Lista > Todo");
				  			/**
				  			 * AGREGADO PARA PROYECTO
				  			 */
				  			request.setAttribute("valorchecked", "1");
				  			
				  			/***
				  			 * FIN
				  			 **/
				  			String cargarDocumento = (String)request.getAttribute("cargarDocumento");
				  			cargarDocumento=(cargarDocumento==null)?"":cargarDocumento;
				  			
				  			if(cargarDocumento.equals("1")){
				  				BInfoDocumento BInfoDocumentoVO = (BInfoDocumento)request.getAttribute("BInfoDocumentoVO_");
				  				rs_destinatario = new ArrayList();
				  				Collection rs_upload_doc_internos = new ArrayList();
				  				
				  				if(BInfoDocumentoVO != null){
				  				log.info("codigo_documento_interno: "+BInfoDocumentoVO.getCodigo_documento_interno());
				  				log.info("/*****RESCATAR VARIABLES....1 ****/");
				  				DocumentoInternoBean documentoInterno = new DocumentoInternoBean();
						  		documentoInterno = service.getDocumentoInterno(BInfoDocumentoVO.getCodigo_documento_interno(), "tdr");
						  	
						  		if(documentoInterno.getCodigos_oficinas_destinos_copias()!=null && documentoInterno.getCodigos_oficinas_destinos_copias().length()>0){
						  			documentoInterno.setCopia_oficinas_destino(documentoInterno.getCodigos_oficinas_destinos_copias().split(","));
						  		}
						  		  				  		
						  		fformDocumentoInterno.setDocumentoInterno(documentoInterno);
						  		rs_destinatario = daoIUsuarioDAO.of_lista_personas_oficinas(fformDocumentoInterno.getCodigo_oficina().toString());
						  		
						  		request.getSession().setAttribute("rs_destinatario", rs_destinatario);
						  		
						  		log.info("/****************************OBTENGO LA LISTA DE DOC ADJUNTOS***************/");
						  		rs_upload_doc_internos = service.getListaArchivosAdjuntos(BInfoDocumentoVO.getCodigo_documento_interno());
						  		
						  		if(rs_upload_doc_internos!=null && rs_upload_doc_internos.size()>0){
						  					session.setAttribute("backup_rs_upload_doc_internos", rs_upload_doc_internos);
						  			  	    session.setAttribute("rs_upload_doc_internos",rs_upload_doc_internos);
						  			  	    session.setAttribute("cantidad_lista",rs_upload_doc_internos.size());
						  		}
						  		else
						  			session.setAttribute("cantidad_lista",0);

						  		
						  		log.info("Cheeck es > "+fformDocumentoInterno.getChecked());
						  	   /****************************************************************************/
						  		request.setAttribute("verbotonescreacion", "1");//para la vista pag_creaciondocinterno
						  		request.setAttribute("verbotonesbandeja", "1"); //para el boton de regreso a la bandeja de derivacion
						  		session.setAttribute("ls_respuesta", "estatico"); //estatico
						  		
						  		/***
						  		 * AGREGADO PARA MANEJAR LAS OBSERVACIONES
						  		 */
						  		
						  		if(fformDocumentoInterno.getCodigo_estado_documento()==9){
						  			es_observado=1;
						  			fformDocumentoInterno.setCodigo_estado_documento(1); //BORRADOR
						  		}
						  		
						  		/***
						  		 * FIN
						  		 */
						  		
						  		log.info("Sali del AActionModificarDocumentoInternoEspecialT operacion");
						  		
						  		//return mapping.findForward("exito");
				  				}
				  			}else{
				  				String cargarproyecto = (String)request.getAttribute("cargarproyecto");
				  				cargarproyecto=(cargarproyecto==null)?"":cargarproyecto;
				  				String cod_proy_ls = (String)request.getAttribute("cod_proy_ls");
				  				cod_proy_ls=(cod_proy_ls==null)?"0":cod_proy_ls;
				  				int codigo_documento_proyecto = Integer.parseInt(cod_proy_ls.trim());
				  				
				  				if(cargarproyecto.equals("1")){

				  					log.info("codigo_documento_proyecto: "+codigo_documento_proyecto);
					  				log.info("/*****RESCATAR VARIABLES PARA PROYECTO ****/");
					  				
					  				DocumentoInternoBean documentoInterno = new DocumentoInternoBean();
							  		documentoInterno = service.getDocumentoInterno(codigo_documento_proyecto, "tdr");
							  	
							  		if(documentoInterno.getCodigos_oficinas_destinos_copias()!=null && documentoInterno.getCodigos_oficinas_destinos_copias().length()>0){
							  			documentoInterno.setCopia_oficinas_destino(documentoInterno.getCodigos_oficinas_destinos_copias().split(","));
							  		}
							  		  				  		
							  		fformDocumentoInterno.setDocumentoInterno(documentoInterno);
							  		rs_destinatario = daoIUsuarioDAO.of_lista_personas_oficinas(fformDocumentoInterno.getCodigo_oficina().toString());
							  		
							  		request.getSession().setAttribute("rs_destinatario", rs_destinatario);
						  		
							  		log.info("Cheeck es > "+fformDocumentoInterno.getChecked());
					  				
					  				/***SETEO PARA QUE CARGUE COMO PROPIO
							  		 * 
							  		 */
							  		fformDocumentoInterno.setArchivos(null);
							  		fformDocumentoInterno.setFirmado_por(firmado_por);
								    
								    fformDocumentoInterno.setCodigo_documento(BORRADOR);
								    fformDocumentoInterno.setCodigo_estado_documento(1);
								    fformDocumentoInterno.setCodigo_tipo_documento_interno(7);//default TDR
								    fformDocumentoInterno.setChecked(false);
								    fformDocumentoInterno.setCodigo_oficina_pertenece(codigo_oficina);
								    
								    if(ls_tipo_envio.equals("R")){
								    	fformDocumentoInterno.setTipo_envio(ls_tipo_envio);
								    }
							  		/**
							  		 * FIN
							  		 */
								    request.setAttribute("es_proyecto", cargarproyecto);
							  		request.getSession().setAttribute("rs_destinatario", rs_destinatario);
				  					
				  					
				  				
				  				}else{
				  				ArrayList<DocumentoInternoBean> listaDocumentosInternosN=service.getlistaDocumentosInternos(bandeja,fformDocumentoInterno.getCodigo_oficina_pertenece(),21);
					  			request.getSession().setAttribute("listaDocumentosInternosN",listaDocumentosInternosN);
					  			ArrayList<DocumentoInternoBean> listaDocumentosInternosD=service.getlistaDocumentosInternos(bandeja,fformDocumentoInterno.getCodigo_oficina_pertenece(),22);
					  			request.getSession().setAttribute("listaDocumentosInternosD",listaDocumentosInternosD);
					  			ArrayList<DocumentoInternoBean> listaDocumentosInternosR=service.getlistaDocumentosInternos(bandeja,fformDocumentoInterno.getCodigo_oficina_pertenece(),23);
					  			request.getSession().setAttribute("listaDocumentosInternosR",listaDocumentosInternosR);
					  			ArrayList<DocumentoInternoBean> listaDocumentosInternosMisProyectos=service.getlistaDocumentosInternos(bandeja,fformDocumentoInterno.getCodigo_oficina_pertenece(),29);
					  			request.getSession().setAttribute("listaDocumentosInternosProyectos",listaDocumentosInternosMisProyectos);
					  			ArrayList<DocumentoInternoBean> listaDocumentosInternosProyectos=service.getlistaDocumentosInternos(7,fformDocumentoInterno.getCodigo_oficina_pertenece(),33);
					  			request.getSession().setAttribute("listaDocumentosInternosProyectosRecibidos",listaDocumentosInternosProyectos);
					  			request.getSession().setAttribute("confirma",ls_nrodoc);
				  				}
				  		}
					  	}else{
				  		
				  		if (bandejaxfirmar.equals("init")&& ls_init.equals("firma")){
				  			es_admitido=1;
				  			log.info("Lista > Firma");
				  			
				  			String cargarDocumento = (String)request.getAttribute("cargarDocumento");
				  			cargarDocumento=(cargarDocumento==null)?"":cargarDocumento;
				  			
				  			if(cargarDocumento.equals("1")){
				  				BInfoDocumento BInfoDocumentoVO = (BInfoDocumento)request.getAttribute("BInfoDocumentoVO_");
				  				rs_destinatario = new ArrayList();
				  				Collection rs_upload_doc_internos = new ArrayList();
				  				
				  				if(BInfoDocumentoVO != null){
				  				log.info("codigo_documento_interno: "+BInfoDocumentoVO.getCodigo_documento_interno());
				  				log.info("/*****RESCATAR VARIABLES....2 ****/");
				  				DocumentoInternoBean documentoInterno = new DocumentoInternoBean();
						  		documentoInterno = service.getDocumentoInterno(BInfoDocumentoVO.getCodigo_documento_interno(), "tdr");
						  	
						  		if(documentoInterno.getCodigos_oficinas_destinos_copias()!=null && documentoInterno.getCodigos_oficinas_destinos_copias().length()>0){
						  			documentoInterno.setCopia_oficinas_destino(documentoInterno.getCodigos_oficinas_destinos_copias().split(","));
						  		}
						  		  				  		
						  		fformDocumentoInterno.setDocumentoInterno(documentoInterno);
						  		rs_destinatario = daoIUsuarioDAO.of_lista_personas_oficinas(fformDocumentoInterno.getCodigo_oficina().toString());
						  		
						  		request.getSession().setAttribute("rs_destinatario", rs_destinatario);
						  		
						  		log.info("/****************************OBTENGO LA LISTA DE DOC ADJUNTOS***************/");
						  		//rs_upload_doc_internos = service.getListaArchivosAdjuntos(BInfoDocumentoVO.getCodigo_documento_interno());
						  		rs_upload_doc_internos=null;
						  		
						  		/*if(rs_upload_doc_internos!=null && rs_upload_doc_internos.size()>0){
						  					session.setAttribute("backup_rs_upload_doc_internos", rs_upload_doc_internos);
						  			  	    session.setAttribute("rs_upload_doc_internos",rs_upload_doc_internos);
						  			  	    session.setAttribute("cantidad_lista",rs_upload_doc_internos.size());
						  		}
						  		else*/
						  			session.setAttribute("cantidad_lista",0);

						  		
						  		log.info("Cheeck es > "+fformDocumentoInterno.getChecked());
						  	   /****************************************************************************/

						  		request.setAttribute("verbotonescreacion", "1");//para la vista pag_creaciondocinterno
						  		request.setAttribute("verbotonesbandeja", "1"); //para el boton de regreso a la bandeja 
						  		
						  		if(BInfoDocumentoVO.getTipo_envio().equals("R")){request.setAttribute("pagina", "haciabreitera");    }  //para segurarme que si firma regrese a la bandeja de reiterativo
				  				if(BInfoDocumentoVO.getTipo_envio().equals("D")){request.setAttribute("pagina", "haciabderiva");	} //para segurarme que si firma regrese a la bandeja de derivacion
				  				
				  				/***
						  		 * AGREGADO PARA MANEJAR LAS OBSERVACIONES
						  		 */
						  		
						  		if(fformDocumentoInterno.getCodigo_estado_documento()==10){
						  			log.info("entre eee ");
						  			es_observado=1;
						  			fformDocumentoInterno.setCodigo_estado_documento(5); //POR FIRMAR
						  			
						  			BInfoDocumento bInfoAdicional = new BInfoDocumento();
					  				bInfoAdicional = (BInfoDocumento) request.getAttribute("bInfoAdicional");
					  				String ls_observacion = bInfoAdicional.getObservacion();
					  				int codigo_motivo = bInfoAdicional.getCodigo_motivo();
					  				log.info("codigo_motivo ->"+codigo_motivo);
					  				log.info("observacion ->"+ls_observacion);						  				
					  				request.setAttribute("ls_observacion", ls_observacion);
					  				session.setAttribute("codigo_motivo_observacion", String.valueOf(codigo_motivo));
						  		}
						  		
						  		/***
						  		 * FIN
						  		 */
						  		 
						  		session.setAttribute("ls_respuesta", "estatico"); //estatico
						  		log.info("Sali del AActionModificarDocumentoInternoEspecialT operacion");
						  		
						  		//return mapping.findForward("exito");
				  				}
				  			}else{
				  				ArrayList<DocumentoInternoBean> listaDocumentosInternosN=service.getlistaDocumentosInternos(5,fformDocumentoInterno.getCodigo_oficina_pertenece(),24);
					  			request.getSession().setAttribute("listaDocumentosInternosN",listaDocumentosInternosN);
					  			ArrayList<DocumentoInternoBean> listaDocumentosInternosD=service.getlistaDocumentosInternos(5,fformDocumentoInterno.getCodigo_oficina_pertenece(),25);
					  			request.getSession().setAttribute("listaDocumentosInternosD",listaDocumentosInternosD);
					  			ArrayList<DocumentoInternoBean> listaDocumentosInternosR=service.getlistaDocumentosInternos(5,fformDocumentoInterno.getCodigo_oficina_pertenece(),26);
					  			request.getSession().setAttribute("listaDocumentosInternosR",listaDocumentosInternosR);
				  				request.getSession().setAttribute("para_firma",ls_nrodoc);
				  				request.getSession().setAttribute("confirma",ls_init);
				  				pagina = "exito_bandeja_firma";
				  		}		  			 
				  		}else{
				  			if(ls_init.equals("busqueda")){
				  			es_admitido=1;
				  			log.info("entre aqui Busqueda");
				  							  			 				  			
				  			ArrayList<DocumentoInternoBean> listaDocumentosInternos=service.getlistaDocumentosInternos(bandeja,fformDocumentoInterno.getCodigo_oficina_pertenece(),5,Integer.parseInt(codigo_tipo_documento_interno),0,ls_nom_documento,Integer.parseInt(ls_nrodoc));
				  			request.getSession().setAttribute("listaDocumentosInternos",listaDocumentosInternos);
				  			request.setAttribute("verListaDocumentosInternosBusqueda","1");
				  			pagina = "exito_bandeja_firma";
				  			
				  		}
				  		}
					  	}
				  		session.setAttribute("es_admiitido", es_admitido);
				  		session.setAttribute("nombre_usuario",userSystem);
				  		request.setAttribute("li_operacion", li_operacion);
				  		session.setAttribute("es_observado", es_observado);
				  		session.setAttribute("es_proyecto", "3");
				  		log.info("es_admitido- "+es_admitido);
				  		log.info("ls_respuesta- "+ls_respuesta);
				  		log.info("userSystem- "+userSystem);
				  		log.info("li_operacion- "+li_operacion);
					    return mapping.findForward(pagina);
		  		}
		  		catch (Exception e) {
		            e.printStackTrace();
		      }
		        
		  	return mapping.findForward("error");			  		
			}
			  
			  			
	}