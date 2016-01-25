package tramitedoc.concytec.admin.action;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import tramitedoc.concytec.admin.form.FFormMantUsuario;
import tramitedoc.concytec.bean.BUsuario;
import tramitedoc.concytec.dao.interfaces.IAdministradorDAO;
import tramitedoc.concytec.dao.sql.SqlAdministradorDAO;
import tramitedoc.concytec.util.Constantes;
import tramitedoc.concytec.util.UMD5;
import tramitedoc.concytec.util.ValidaSessionAction;
/**
 * AUTOR		: Machuca Ovalle
 * FECHA		: 03-04-2006
 * VERSION		: 1.0
 * DESCRIPCIÓN	: Accion Mantenimiento de Usuarios  
 */

public class AActionMantUsuarios extends ValidaSessionAction{
	protected  Log log = LogFactory.getLog(ValidaSessionAction.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession(true);
		Connection cnx = getConnection(request, "principal");
		System.out.println("El cnx............" + cnx);	    
	  	
		IAdministradorDAO daoIUsuarioDAO = new SqlAdministradorDAO();
		BUsuario detalleUsuarios=new BUsuario();
		String nombre_final = "";
		
		FFormMantUsuario oForm = (FFormMantUsuario) form;
		String tipo = oForm.getTipo() == null ? "init" : oForm.getTipo().trim();
		System.out.println("El tipo 1 "+ tipo);
		tipo = tipo.equals("") ? "init" : tipo;
		System.out.println("El tipo 2 "+ tipo);
		
		
		String usuario;
		String clave;
		String estado;
		String flag;
		String nombres;
		String apellidos;
		String email;
		String claveenc;
		String codigoUsuarioBuscar;
		String dni;
		String abrev;
		String cadenaclave = "nohaynada";
		String sede;
				
		String path = Constantes.CarpetaArchivosDesarrolloSellos.getNombre();
		boolean existeFirma = false;
		//Cambio
		BUsuario usuario_sistema = (BUsuario)session.getAttribute("usuario_sistema");
		//Fin Cambio
		usuario = ((FFormMantUsuario)form).getUsuario();
		clave = ((FFormMantUsuario)form).getClave();
		UMD5 mo = UMD5.getInstance();
		
		estado = ((FFormMantUsuario)form).getEstado();
		flag = ((FFormMantUsuario)form).getFlag();
		nombres = ((FFormMantUsuario)form).getNombres();
		apellidos = ((FFormMantUsuario)form).getApellidos();
		email = ((FFormMantUsuario)form).getEmail();
		codigoUsuarioBuscar = ((FFormMantUsuario)form).getCodigoUsuarioBuscar();
		dni = ((FFormMantUsuario)form).getDni();
		abrev = ((FFormMantUsuario)form).getAbrev();
		
		sede = ((FFormMantUsuario)form).getSede();
		
		System.out.println("usuario... "+ usuario);
		System.out.println("clave... "+ clave);
		System.out.println("estado... "+ estado);
		System.out.println("flag... "+ flag);
		System.out.println("nombres... "+ nombres);
		System.out.println("apellidos... "+ apellidos);
		System.out.println("email... "+ email);
		
		
		Collection tipo_usuarios =daoIUsuarioDAO.of_lista_tipo_usuarios(cnx);
		session.setAttribute("tipo_usuarios", tipo_usuarios);
		//Cambio
		Collection tipo_sede =daoIUsuarioDAO.of_lista_tipo_sede(cnx, Integer.parseInt(usuario_sistema.getSede()));
		session.setAttribute("tipo_sede", tipo_sede);
		//Fin Cambio
		try {
			
			//Cambio
			if(tipo.equals("buscar")){
				System.out.println("buscar");
				Collection listausuarios = daoIUsuarioDAO.of_lista_usuarios(cnx,codigoUsuarioBuscar,Integer.parseInt(usuario_sistema.getSede()));
				session.setAttribute("listausuarios",listausuarios);
				
			//Fin Cambio	
			}else 
				
				if (tipo.equals("usuario") ){
				
				System.out.println("Si tipo es init" + tipo);
				System.out.println("Listar Usuarios....." );
				
				Collection listausuarios =  daoIUsuarioDAO.of_lista_usuarios(cnx,Integer.parseInt(usuario_sistema.getSede()));
					
					System.out.println("listausuarios" + listausuarios);
					
					session.setAttribute("operacion",tipo);
					session.setAttribute("listausuarios",listausuarios);
					
					
			}else if (tipo.equalsIgnoreCase("nuevo")){
			//li_operacion = 2;
				try {
		
					System.out.println("Ingresando Usuarios .........." );
					
					nombres=nombres.toUpperCase();
					apellidos=apellidos.toUpperCase();
					//clave=mo.hashData(clave.getBytes()).toLowerCase();
					
					//email=email.toUpperCase();
					System.out.println("usuario.UPERCASE.. "+ usuario);
					System.out.println("clave.UPERCASE.. "+ clave);
					//System.out.println("email.UPERCASE.. "+ email);
					System.out.println("clave encriptada.. "+ clave);
					//Cambio
					if(!flag.equals("A")){
						sede="0";
					}
					
					daoIUsuarioDAO.UsuarioIng(cnx,usuario,clave,estado,flag,nombres,apellidos,email,dni,abrev,sede);
					
					Collection listausuarios =  daoIUsuarioDAO.of_lista_usuarios(cnx,Integer.parseInt(usuario_sistema.getSede()));
					session.setAttribute("listausuarios",listausuarios);
					
								
				}catch (SQLException e) {
					e.printStackTrace();
					closeConnection(cnx);
					//return mapping.getInputForward();
		
				}
					
					//session.removeAttribute("operacioncs");
			      			
		}else if(tipo.equalsIgnoreCase("enviar")){
			try {
			
				
			log.info("Enviando usuarios .............");
			String tipo_update = request.getParameter("tipo_update");
			tipo_update=tipo_update==null? "":tipo_update;
			int resultado=0;
			
			String claveold = clave;
			clave = encriptarClave(clave, mo);//modificado por mpelaez 28-02-2012
				
			//if(!flag.equals("A")){
				//sede="0";
			//}
			
	
			if(daoIUsuarioDAO.esUsuarioNuevo(usuario)){
				
				nombres=nombres.toUpperCase();
				apellidos=apellidos.toUpperCase();				
				daoIUsuarioDAO.UsuarioIng(cnx,usuario,clave,estado,flag,nombres,apellidos,email,dni,abrev,sede);
				
				
				
				request.setAttribute("mensaje_usuario", "Se ha Registrado Correctamente");
			}else{
				
				if(claveold.equals(cadenaclave)){
					clave = "";
				}
				
if(tipo_update.equals("editar")){
					resultado=daoIUsuarioDAO.UsuarioMod(cnx,usuario,clave,estado,flag,nombres,apellidos,email,dni,abrev,sede);
				}else{
					System.out.println("no modifico");
				}
				
				System.out.println("-->"+resultado);
				if(resultado!=0){
					request.setAttribute("mensaje_usuario", "Se ha Editado Correctamente");
				}else{
					request.setAttribute("mensaje_usuario", "No se modifico el usuario. Verifique si el usuario ya existe.");
				}
			}
			session.setAttribute("operacion",tipo);
			Collection listausuarios =  daoIUsuarioDAO.of_lista_usuarios(cnx,Integer.parseInt(usuario_sistema.getSede()));
			
			session.setAttribute("listausuarios",listausuarios);
			session.setAttribute("detalleUsuarios",null);
			request.setAttribute("FFormMantUsuario", form);

			}catch (SQLException e) {
				//rollbackConnection(cnx);
				e.printStackTrace();
				session.setAttribute("mensaje_usuario", "No se creo/modifico el usuario. Verifique si el usuario ya existe.");
				
			}
			
		}else if (tipo.equalsIgnoreCase("editar")){
			try {
	
	
				System.out.println("Modificacion de... Usuarios .........." );
				String tipo_update = request.getParameter("tipo_update");
				System.out.println(tipo_update);
				
				nombres=nombres.toUpperCase();
				apellidos=apellidos.toUpperCase();
				email=email.toUpperCase();
				//clave=mo.hashData(clave.getBytes()).toLowerCase();
				
				System.out.println("usuario.UPERCASE.. "+ usuario);
				System.out.println("clave.UPERCASE.. "+ clave);
				System.out.println("email.UPERCASE.. "+ email);
				System.out.println("clave encriptada.. "+ clave);
				
				//daoIUsuarioDAO.UsuarioMod(cnx,usuario,clave,estado,flag,nombres,apellidos,email);
				detalleUsuarios= daoIUsuarioDAO.UsuarioCons(cnx,usuario);
				FFormMantUsuario fUsuario = new FFormMantUsuario();
				fUsuario.setUsuario(detalleUsuarios.getUsuario());
				fUsuario.setClave(cadenaclave);
				fUsuario.setNombres(detalleUsuarios.getNombres());
				fUsuario.setApellidos(detalleUsuarios.getApellidos());
				fUsuario.setEmail(detalleUsuarios.getEmail());
				fUsuario.setEstado(detalleUsuarios.getEstado());
				fUsuario.setFlag(detalleUsuarios.getFlag());
				fUsuario.setDni(detalleUsuarios.getDni());
				fUsuario.setAbrev(detalleUsuarios.getAbreviatura());
				System.out.println("---> "+detalleUsuarios.getSede());
				fUsuario.setSede(detalleUsuarios.getSede().equals("0")? "": detalleUsuarios.getSede());
				
				fUsuario.setTipo(tipo);
						
				
				Collection listausuarios =  daoIUsuarioDAO.of_lista_usuarios(cnx,Integer.parseInt(usuario_sistema.getSede()));
				
				//System.out.println("listausuarios" + listausuarios);
				//System.out.println("detalleUsuarios" + detalleUsuarios);
				
				request.setAttribute("FFormMantUsuario", fUsuario);
				session.setAttribute("operacion",tipo);
				//session.setAttribute("listausuarios",listausuarios);
				session.setAttribute("detalleUsuarios",detalleUsuarios);
				
		
			}catch (SQLException e) {
				rollbackConnection(cnx);
				e.printStackTrace();
				session.setAttribute("MsjeError", "Error al actualizar al empleado: " + e.getMessage());
				//closeConnection(cnx);
				//return mapping.getInputForward();
	
			}
					
			return mapping.getInputForward();
	
		} else if (tipo.equalsIgnoreCase("eliminar")){

			try {
	
				System.out.println("Eliminar  Usuario.........." );
	
				daoIUsuarioDAO.UsuarioElim(cnx,usuario);
				
				Collection listausuarios =  daoIUsuarioDAO.of_lista_usuarios(cnx,Integer.parseInt(usuario_sistema.getSede()));
				
				System.out.println("listausuarios" + listausuarios);
				session.setAttribute("operacion",tipo);
				session.setAttribute("listausuarios",listausuarios);
				
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
	private String encriptarClave(String clave, UMD5 mo) {
		//aqui se tiene que encriptar la clave:
		/*
		 * agregado por mpelaezs:05_01_2012
		 * */
		try{
			//UMD5 md = UMD5.getInstance();
			
			log.info("clave:"+clave);
			clave=mo.hashData(clave.getBytes()).toLowerCase();
			log.info("clave:"+clave);
			}catch(Exception e){
				e.printStackTrace();
			}
		/*fin
		 * */
		return clave;
	}
	
	private File parent(File f) {
		String dirname = f.getParent();
		if (dirname == null) {
			return new File(File.separator);
		}
		return new File(dirname);

	}
	
	private boolean makeSureDirectoryExists(File dir) {

		if (!dir.exists()) {
			if (makeSureDirectoryExists(parent(dir)))
				dir.mkdir();
			else
				return false;
		}
		return true;
	}
	
	
}
