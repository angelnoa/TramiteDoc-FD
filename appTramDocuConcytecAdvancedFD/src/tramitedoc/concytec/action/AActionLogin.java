package tramitedoc.concytec.action;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

import tramitedoc.concytec.bean.BDatosFirmante;
import tramitedoc.concytec.bean.BUsuario;
import tramitedoc.concytec.bean.BUsuarioSel;
import tramitedoc.concytec.dao.interfaces.DocumentosInternosService;
import tramitedoc.concytec.dao.interfaces.ILoginDAO;
import tramitedoc.concytec.dao.interfaces.IReiterativoDAO;
import tramitedoc.concytec.dao.interfaces.IUsuarioDAO;
import tramitedoc.concytec.dao.sql.SqlLoginDAO;
import tramitedoc.concytec.dao.sql.SqlReiterativoDAO;
import tramitedoc.concytec.dao.sql.SqlUsuarioDAO;
import tramitedoc.concytec.dao.sql.SqlUsuarioSelDAO;
import tramitedoc.concytec.form.FFormLogin;
import tramitedoc.concytec.service.DocumentosInternosServiceImplement;
import tramitedoc.concytec.util.Funciones;
import tramitedoc.concytec.util.GoogleAuthHelper;
import tramitedoc.concytec.util.UMD5;
import tramitedoc.concytec.util.ValidaSessionAction;
/**
 * AUTOR		: Machuca Ovalle
 * FECHA		: 03-04-2006
 * VERSION		: 1.0
 * DESCRIPCIÓN	: Accion de Logeo  
 */

public class AActionLogin extends ValidaSessionAction{
	
	//creamos una clase para obtener el email del json del logeo de google 
	private class JsonGoogle{
        private String email;
        private String picture;
    }
	
	protected  Log log = LogFactory.getLog(AActionLogin.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// get parameters							// *
		String usu = request.getParameter("usu");
		System.out.println("parámetro usu: " + usu);

		// Verifica si el acceso de google devolvió un code
		// de ser así evitamos el login tracional
		String code = request.getParameter("code");

		UMD5 md5 = UMD5.getInstance();
		IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
		Connection cnx=null;
		String sLogin = "";
		String sClave = "";
		String ls_codigo_persona="";	
		String ls_persona_oficina="";
		String picture_google = null;

		// Si code es un parámetro es logeo por google si es null es logeo
		// tradicional
		/*
		if (code == null && usu == null) {
			sLogin = ((FFormLogin) form).getUsuario();
			sClave = ((FFormLogin) form).getPassword();
		}
		*/
		
		Funciones funciones=new Funciones();
		log.info("sLogin... "+sLogin);
		log.info("sClave... "+sClave);
		
		String page="error";
		Collection listaFrameBusquedaTemp = null;
		boolean flagBandejaRecepcion = false;
		boolean flagBandejaDerivacion = false;
		boolean flagBandejaSeguimiento = false;
		int[] cantidades =new int[6];
		
		
		try {
			
			//------------------------------------------------------------------------------
			List<BUsuarioSel> listUsuarioSel = new ArrayList<BUsuarioSel>();
			BUsuario userSystem = new BUsuario();
			String bandejaxfirmar = null;

			cnx = getConnection(request, "principal");
			log.info("El cnx  ==> " + cnx);
			
			if (usu != null) {
				System.out.println("1");
				SqlUsuarioDAO daoSqlUsuario = new SqlUsuarioDAO();
				daoSqlUsuario.registrar_conexion(usu);
				if (usu != null) {
					System.out.println("usu != null");
					for (BUsuario u : daoSqlUsuario.listar()) {
						if (u.getUsuario()
								.toLowerCase()
								.trim()
								.equalsIgnoreCase(
										usu.trim())) {
							System.out.println("Encontre una coincidencia de usuario");
							userSystem = u;
							break;
						}
					}
				}

			} else if (code == null) {
				System.out.println("2");
				System.out.println("code == null && usu == null");
				sLogin = ((FFormLogin) form).getUsuario();
				sClave = ((FFormLogin) form).getPassword();
				ILoginDAO daoLoginDAO = new SqlLoginDAO();
				log.info("usuario:" + sLogin);
				log.info("clave:" + sClave);
			String sClaveEncryptada = md5.hashData(sClave.getBytes()).toLowerCase();
				log.info("clave encryptada:" + sClaveEncryptada);
				
				userSystem = daoLoginDAO.fjauthenticate(cnx, sLogin, sClaveEncryptada);
				SqlUsuarioDAO daoSqlUsuario = new SqlUsuarioDAO();
				daoSqlUsuario.registrar_conexion(sLogin);
				if (userSystem != null) {
					log.info("userSystem");
					log.info("usuario -> " + userSystem.getUsuario());
					log.info("clave -> " + userSystem.getClave());
				}
				
				
			} else {
				System.out.println("3");
				final GoogleAuthHelper helper = new GoogleAuthHelper();
				Gson gson = new Gson();
				userSystem.setEmail("");
				JsonGoogle jsonGoogle = gson.fromJson(helper.getUserInfoJson(code), JsonGoogle.class);

				// *
				SqlUsuarioDAO daoSqlUsuario = new SqlUsuarioDAO();
				SqlUsuarioSelDAO daoSqlUsuarioSel = new SqlUsuarioSelDAO();
				listUsuarioSel = daoSqlUsuarioSel.getUsersByEmail(jsonGoogle.email.toLowerCase().trim());
				picture_google =jsonGoogle.picture.trim();
				System.out.println("foto: " + picture_google);

				if (listUsuarioSel.size() == 0) {
					userSystem = null;
					
				} else if(listUsuarioSel.size() == 1){
					
					for (BUsuario u : daoSqlUsuario.listar()) {
						if (u.getUsuario()
								.toLowerCase()
								.trim()
								.equalsIgnoreCase(
										listUsuarioSel.get(0).getUsuario())) {
							userSystem = u;
							break;
						}
					}
				}

				//System.out.println("--> "+userSystem.getEmail());
				/*if (userSystem.getEmail()==null || userSystem.getEmail().length() == 0) {
					userSystem = null;
				}*/
			}

			//------------------------------------------------------------------------------
			
        	log.info("El userSystem  ==> "+userSystem);
        	HttpSession session = request.getSession(true);
        	/*HttpSession sesion = request.getSession();
        	sesion.setMaxInactiveInterval(60); //se le da a un usuario un tiempo de 60 minutos
        	si pongo -1 es infinito
        	*/
        	IReiterativoDAO ire= new SqlReiterativoDAO();
        	Collection estados = ire.listarEstados();
        		
        	
        	session.setAttribute("estados", estados);
        	
        	session.removeAttribute("vaRmyFile");
        	session.removeAttribute("nuevo_nombre_sello_oficina");
        	session.removeAttribute("vaRmyFilePx");
        	session.removeAttribute("rs_upload_doc_internos");
        	session.removeAttribute("cantidad_lista");
        	

			if (listUsuarioSel.size() > 1 && usu == null) {

				System.out.println("listUsuarioSel.size() > 1");
				System.out.println("Usuarios: ");
				for (int i = 0; i < listUsuarioSel.size(); i++) {
					System.out.println("Usuario: "
							+ listUsuarioSel.get(i).getUsuario());
					System.out.println("Oficina: "
							+ listUsuarioSel.get(i).getNombre_corto());
				}

				request.setAttribute("ListUsuarios", listUsuarioSel);
				session.setAttribute("picture_google", picture_google);
				page = "switchUsuario";

			} else if (userSystem == null) {

        	    log.info("El usuario es............"+sLogin);
        		ActionErrors errores = new ActionErrors();
        		ActionError error1 = new ActionError("user.invalid");
    			errores.add("login", error1);	
    			this.saveErrors(request,errores);
    		}
    		else{
    			if(cnx != null) {
    				session.setAttribute("conexion", cnx);
    			}
    			
    			bandejaxfirmar="init";
    			
    			session.setAttribute("conexion", cnx);
    			session.setAttribute("listaFrameBusquedaTemp", listaFrameBusquedaTemp);
    			session.setAttribute("flagBandejaRecepcion", flagBandejaRecepcion);
    			session.setAttribute("flagBandejaDerivacion", flagBandejaDerivacion);
    			session.setAttribute("flagBandejaSeguimiento", flagBandejaSeguimiento);
		
				
    			session.setAttribute("nombre_usuario",userSystem);
    			session.setAttribute("usuario_sistema",userSystem);
    			log.info("nombre de usuario ===> "+userSystem);
    			
    	      	String ls_usuario = userSystem.getUsuario();
    	      	String ls_flag = userSystem.getFlag();
    	      	String ls_sede= userSystem.getSede();
    	      	String codigo_oficina=funciones.of_codigo_oficina(cnx, ls_usuario);
    	      	String grupo_oficina=null;

    	      	log.info("El ls_oficina... "+codigo_oficina);
    	      	if(!codigo_oficina.equals("") && codigo_oficina.length()>0){
    	      		grupo_oficina=funciones.of_codigo_grupo(cnx, codigo_oficina);
    	      	}
    	      	
    	      	String ls_codigo_institucion=funciones.of_codigo_oficina_instituciones(cnx, ls_usuario);
    	      	String cargo_personal=null;
    	      
    	      	DocumentosInternosService service = new DocumentosInternosServiceImplement();
    	      	BDatosFirmante beanDatosFirmantexUsuario= new BDatosFirmante();
    	      	beanDatosFirmantexUsuario=service.getDatosFirmante(ls_usuario);
    	      	
    			log.info("El ls_usuario... "+ls_usuario);
    			log.info("El ls_flag... "+ls_flag);
    			log.info("El ls_oficina... "+codigo_oficina);
    			log.info("El grupo_oficina es... "+grupo_oficina);
    			
    			session.setAttribute("flag", ls_flag);
    			Collection rs_oficina=null;
    			
    			session.setAttribute("tipoDeFirmaUsuario",beanDatosFirmantexUsuario.getTipo_firma());
    			session.setAttribute("oficinaPermisoFirma", beanDatosFirmantexUsuario.getOficinaPermisoFirma());
    			
    			String codigoOficinaDelUSuario = funciones.of_codigo_oficina(cnx, ls_usuario);
    			String ls_cargo_personal= funciones.of_cargo_persona(cnx, ls_usuario);
    			codigoOficinaDelUSuario = (codigoOficinaDelUSuario ==null ||codigoOficinaDelUSuario.equals(""))?"0":codigoOficinaDelUSuario;
    			
    			if(!codigoOficinaDelUSuario.equals("0")){
    				if(ls_cargo_personal.equals("3") || ls_cargo_personal.equals("10")){
    					cantidades=daoIUsuarioDAO.total_control_recepcion_documentos(codigo_oficina,funciones.of_codigo_persona(cnx,ls_usuario),"");
    				}else{
    					cantidades=daoIUsuarioDAO.total_control_recepcion_documentos(codigo_oficina,funciones.of_codigo_persona(cnx,ls_usuario),"");
    				}
    				
    				session.setAttribute("cantidadBandejaRecepcion", cantidades[0]);
    				session.setAttribute("cantidadBandejaTramite", cantidades[1]);
    				session.setAttribute("cantidadBandejaAtencionUrgente", cantidades[2]);
    				session.setAttribute("cantidadBandejaFechaRptaPlazo", cantidades[3]);
    				session.setAttribute("cantidadBandejaFechaRptaLimite", cantidades[4]);
    				session.setAttribute("cantidadBandejaFechaRptaFuera", cantidades[5]);
    			}
    			
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
				session.setAttribute("rs_oficina", daoIUsuarioDAO.of_lista_oficinas(cnx));
				Collection rs_motivos = daoIUsuarioDAO.of_lista_motivos(cnx);
				session.setAttribute("rs_motivos", rs_motivos);
				session.setAttribute("_bandejaxfirmar", bandejaxfirmar);
				
    			if(ls_flag.equals("A")){
                    log.info("Si es Administrador");
                    
    				session.setAttribute("nombreusuario",ls_usuario);
                    session.setAttribute("ls_flag",ls_flag);
                    session.setAttribute("ls_sede",ls_sede);
    				//session.setAttribute("nombrecortooficina", userSystem.getNombre_corto());
    				page="administrador";
					
    			}else if (ls_flag.equals("S")) {
    				log.info("Si no es Administrador S");
   				 
    				Collection listaOficinasUsuario = daoIUsuarioDAO.of_lista_oficinas_usuario(cnx,ls_usuario);
    				
    				log.info("Si listaOficinasUsuario"+ listaOficinasUsuario);
    				
    				session.setAttribute("OficinasUsuario",listaOficinasUsuario);
    				
    				log.info("Despues de la Session");
    				
    				cargo_personal= funciones.of_cargo_persona(cnx, ls_usuario);
    				ls_codigo_persona=funciones.of_codigo_persona(cnx,ls_usuario);
    				
            		log.info("El codigo de Oficina es.." + codigo_oficina);
            		log.info("El codigo de cargo_personal es.." + cargo_personal);
            		log.info("El codigo de ls_codigo_persona es.." + ls_codigo_persona);
            		
    				session.setAttribute("nombreusuario",ls_usuario);
    				session.setAttribute("codigo_oficina",codigo_oficina.trim());
    				session.setAttribute("codigo_oficina_user",codigo_oficina.trim());
    				session.setAttribute("nombreusers",ls_usuario);
    				session.setAttribute("cargo_personal",cargo_personal.trim());
    				session.setAttribute("codigo_persona",ls_codigo_persona.trim());
    				session.setAttribute("codigo_persona_noborrar",ls_codigo_persona.trim());
    				session.setAttribute("grupo_oficina", grupo_oficina);
    				/**
    				 * JAZANERO
    				 */
    				session.setAttribute("nombrecortooficina", beanDatosFirmantexUsuario.getNombre_corto());
    				session.setAttribute("ls_codigo_institucion", ls_codigo_institucion);
    				session.setAttribute("pertenece_a", ls_codigo_institucion);
    				log.info("pertenece a ->"+ls_codigo_institucion);
    				session.setAttribute("nombre_institucion", funciones.getNombreInstitucion(ls_codigo_institucion));
    				
    				page="usuario";
    				
    			}
    			else {
    				
    				log.info("Si no es Administrador N o P");
    				Collection listaOficinasUsuario = daoIUsuarioDAO.of_lista_oficinas_usuario(cnx,ls_usuario);
    				log.info("Si listaOficinasUsuario"+ listaOficinasUsuario);
    				session.setAttribute("OficinasUsuario",listaOficinasUsuario);
    				log.info("Despues de la Session");
    				
    				cargo_personal= funciones.of_cargo_persona(cnx, ls_usuario);
    				ls_codigo_persona=funciones.of_codigo_persona(cnx,ls_usuario);
    				
            		log.info("El codigo de Oficina es.." + codigo_oficina);
            		log.info("El codigo de cargo_personal es.." + cargo_personal);
            		log.info("El codigo de ls_codigo_persona es.." + ls_codigo_persona);
            		
    				session.setAttribute("nombreusuario",ls_usuario);
    				session.setAttribute("codigo_oficina",codigo_oficina.trim());
    				session.setAttribute("codigo_oficina_user",codigo_oficina.trim());
    				session.setAttribute("nombreusers",ls_usuario);
    				session.setAttribute("cargo_personal",cargo_personal.trim());
    				session.setAttribute("codigo_persona",ls_codigo_persona.trim());
    				session.setAttribute("codigo_persona_noborrar",ls_codigo_persona.trim());
    				session.setAttribute("grupo_oficina", grupo_oficina);
    				
    				/**
    				 * JAZANERO
    				 */
    				session.setAttribute("nombrecortooficina", beanDatosFirmantexUsuario.getNombre_corto());
    				session.setAttribute("ls_codigo_institucion", ls_codigo_institucion);
    				session.setAttribute("pertenece_a", ls_codigo_institucion);
    				log.info("pertenece a ->"+ls_codigo_institucion);
    				session.setAttribute("nombre_institucion", funciones.getNombreInstitucion(ls_codigo_institucion));
    				
    				page="usuario";
    					
    				
    			}
       			
    	    }
        	session.setAttribute("modo_tramite", "0");
        } 
        catch (Exception e) {
            e.printStackTrace();
        } finally{
        	closeConnection(cnx);
        }
       	return (mapping.findForward(page));
	}
}
