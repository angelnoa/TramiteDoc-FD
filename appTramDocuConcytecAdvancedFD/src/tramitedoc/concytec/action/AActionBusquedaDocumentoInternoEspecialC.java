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

	 import tramitedoc.concytec.bean.BUsuario;
	 import tramitedoc.concytec.bean.DocumentoInternoBean;
	 import tramitedoc.concytec.bean.EstandarBean;
	 import tramitedoc.concytec.dao.interfaces.DocumentosInternosService;
	 import tramitedoc.concytec.form.FFormBusquedaDocumentoInterno;
	 import tramitedoc.concytec.service.DocumentosInternosServiceImplement;

	 /**
	  * AUTOR		: Moises Pelaez S
	  * FECHA		: 03-04-2012
	  * VERSION		: 1.0
	  * DESCRIPCIÓN	: Carga las listas necesarias para crear un documento interno  
	  */

	 public class AActionBusquedaDocumentoInternoEspecialC extends Action {
	 	protected  Log log = LogFactory.getLog(AActionBusquedaDocumentoInternoEspecialC.class);
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
	 		  		log.info("Entry AActionBusquedaDocumentoInterno... ");
	 		  		/*****REMUEVO LA LISTA DE DOC FIRMADOS INTERNOS y OTROS****/
	 		  		session.removeAttribute("rs_upload_doc_internos");
	 		  		session.removeAttribute("cantidad_lista");
	 		  		session.removeAttribute("docInterno_d");
	 		  		/**************************************************/
	 		  		FFormBusquedaDocumentoInterno fformBusquedaDocumentoInternoCcp =(FFormBusquedaDocumentoInterno) form;  
	 		  		fformBusquedaDocumentoInternoCcp.setDocumentoInterno(new DocumentoInternoBean());
	 		  		fformBusquedaDocumentoInternoCcp.setArchivos(null);
	 		  		
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
	 		  		log.info("ls_init-> "+ls_inicia);
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
	 			    //if(cargo_personal.equals("11") || cargo_personal.equals("13")  || cargo_personal.equals("5") || cargo_personal.equals("3") ){
	 			    		//no es secretaria
	 			    request.getSession().setAttribute("esApto","1");
	 			    //}

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
//	 			    log.info("size: " +names.size());
	 			    request.getSession().setAttribute("names",names);
	 			    int codigo_oficina=service.of_codigo_oficina(usuario_actual);
	 			    /*   String firmado_por=service.of_nombre_director_oficina(codigo_oficina);			    
	 			    
	 			    fformBusquedaDocumentoInternoCcp.setFirmado_por(firmado_por);
	 			    fformBusquedaDocumentoInternoCcp.setCodigo_documento(BORRADOR);
	 			    fformBusquedaDocumentoInternoCcp.setCodigo_estado_documento(1);
	 			    fformBusquedaDocumentoInternoCcp.setCodigo_tipo_documento_interno(1);//default 1 MEMO
	 			    fformBusquedaDocumentoInternoCcp.setChecked(true);*/
	 			    fformBusquedaDocumentoInternoCcp.setCodigo_oficina_pertenece(codigo_oficina);
	 			    
	 			    if(ls_tipo_envio.equals("R")){
	 			    	fformBusquedaDocumentoInternoCcp.setTipo_envio(ls_tipo_envio);
	 			    }
	 				
	 				
	 				  			log.info("Entre a setear >");
	 				  			session.removeAttribute("ls_codigo_documento");
	 				  			session.removeAttribute("ls_codigo_expediente");
	 				  			session.removeAttribute("ls_codigo_recepcion");
	 				  			session.removeAttribute("ls_secuencia");
	 				  			session.removeAttribute("ls_respuesta");
	 				  			
	 				if (ls_inicia.equals("SI")){					  		
	 					  		if (ls_init.equals("firma")){
	 					  			es_admitido=1;
	 					  			log.info("Lista > para Firma");
	 					  			
	 					  			ArrayList<DocumentoInternoBean> listaDocumentosInternosN=service.getlistaDocumentosInternos(5,fformBusquedaDocumentoInternoCcp.getCodigo_oficina_pertenece(),18);
						  			request.getSession().setAttribute("listaDocumentosInternosN",listaDocumentosInternosN);
						  			ArrayList<DocumentoInternoBean> listaDocumentosInternosD=service.getlistaDocumentosInternos(5,fformBusquedaDocumentoInternoCcp.getCodigo_oficina_pertenece(),19);
						  			request.getSession().setAttribute("listaDocumentosInternosD",listaDocumentosInternosD);
						  			ArrayList<DocumentoInternoBean> listaDocumentosInternosR=service.getlistaDocumentosInternos(5,fformBusquedaDocumentoInternoCcp.getCodigo_oficina_pertenece(),20);
						  			request.getSession().setAttribute("listaDocumentosInternosR",listaDocumentosInternosR);
	 					  			request.getSession().setAttribute("para_firma",ls_nrodoc);
	 					  			request.getSession().setAttribute("confirma",ls_init);
	 					  			pagina = "exito";
	 					  			
	 					  		}else{
	 					  			if(ls_init.equals("busqueda")){
	 					  			es_admitido=1;
	 					  			log.info("entre aqui Busqueda");
	 					  			//int xtipo = Integer.parseInt(ls_tipo);
	 					  			//System.out.println("Nom Doc "+ls_nom_documento);
//	 					  			log.info("Cod oficina pertenece >"+fformBusquedaDocumentoInternoCcp.getCodigo_oficina_pertenece());
//	 					  			log.info("Cod de estado >"+fformBusquedaDocumentoInternoCcp.getCodigo_estado_documento());
//	 					  			log.info("Tipo Doc interno >"+codigo_tipo_documento_interno);
//	 					  			log.info("NroDoc "+ls_nrodoc);
//	 					  			log.info("Codigo oficina >"+ls_cod_ofic);
//	 					  			log.info("Nombre documento >"+ls_nom_documento);
	 					  			 				  			
	 					  			//ArrayList<DocumentoInternoBean> listaDocumentosInternos=service.getlistaDocumentosInternos(fformBusquedaDocumentoInternoCcp.getCodigo_oficina_pertenece(),fformBusquedaDocumentoInternoCcp.getCodigo_estado_documento(),Integer.parseInt(codigo_tipo_documento_interno),Integer.parseInt(ls_cod_ofic),ls_nom_documento,ls_ast);
	 					  			ArrayList<DocumentoInternoBean> listaDocumentosInternos=service.getlistaDocumentosInternos(bandeja,fformBusquedaDocumentoInternoCcp.getCodigo_oficina_pertenece(),5,0,0,ls_nom_documento,Integer.parseInt(ls_nrodoc));
	 					  			request.getSession().setAttribute("listaDocumentosInternosBusqueda",listaDocumentosInternos);
	 					  			request.setAttribute("verListaDocumentosInternosBusqueda","1");
	 					  		//pagina = "exito_bandeja_firma";
	 						  		}else{
	 						  			if(ls_init.equals("vista")){
	 						  			es_admitido=1;
	 						  			log.info("entre aqui Vista");
	 						  			ArrayList<DocumentoInternoBean> listaDocumentosInternosNV=service.getlistaDocumentosInternos(6,fformBusquedaDocumentoInternoCcp.getCodigo_oficina_pertenece(),18);
							  			request.getSession().setAttribute("listaDocumentosInternosN",listaDocumentosInternosNV);
							  			ArrayList<DocumentoInternoBean> listaDocumentosInternosDV=service.getlistaDocumentosInternos(6,fformBusquedaDocumentoInternoCcp.getCodigo_oficina_pertenece(),19);
							  			request.getSession().setAttribute("listaDocumentosInternosD",listaDocumentosInternosDV);
							  			ArrayList<DocumentoInternoBean> listaDocumentosInternosRV=service.getlistaDocumentosInternos(6,fformBusquedaDocumentoInternoCcp.getCodigo_oficina_pertenece(),20);
							  			request.getSession().setAttribute("listaDocumentosInternosR",listaDocumentosInternosRV);
	 						  			request.getSession().setAttribute("para_firma",ls_nrodoc);
	 						  			request.getSession().setAttribute("confirma",ls_init);
	 						  			request.setAttribute("vista", "1");
	 						  			//pagina = "exito_bandeja_firma";
	 						  			}
	 						  		}
	 						  		}//bien
	 					}
	 						else{
	 						//veremos
	 				}
	 					  	
	 				  		session.setAttribute("es_admiitido", es_admitido);
	 				  		session.setAttribute("nombre_usuario",userSystem);
	 				  		session.setAttribute("li_operacion", li_operacion);
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
