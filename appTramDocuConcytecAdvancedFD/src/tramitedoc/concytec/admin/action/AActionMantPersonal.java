package tramitedoc.concytec.admin.action;
import java.io.File;
import java.io.FileOutputStream;
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
import org.apache.struts.upload.FormFile;

import tramitedoc.concytec.dao.*;
import tramitedoc.concytec.admin.form.*;
import tramitedoc.concytec.bean.*;
import tramitedoc.concytec.dao.interfaces.*;
import tramitedoc.concytec.util.*;
import tramitedoc.concytec.dao.sql.*;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
/**
 * AUTOR		: Machuca Ovalle
 * FECHA		: 03-04-2006
 * VERSION		: 1.0
 * DESCRIPCIÓN	: Accion Mantenimiento de Usuarios  
 */

public class AActionMantPersonal extends ValidaSessionAction{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession(true);
		Connection cnx = getConnection(request, "principal");
		System.out.println("El cnx............" + cnx);	    
	  	
		IAdministradorDAO daoIAdministradorDAO = new SqlAdministradorDAO();
		BPersonal detallePersonal=new BPersonal();
		BUsuario detalleUsuario=new BUsuario();
		
		FFormMantPersonal oForm = (FFormMantPersonal) form;
		String tipo = oForm.getTipo() == null ? "init" : oForm.getTipo().trim();
		System.out.println("El tipo 1 "+ tipo);
		tipo = tipo.equals("") ? "init" : tipo;
		System.out.println("El tipo 2 "+ tipo);
		
		String codigo_personal;
		String nombreapellido_usuario;
		String c_usuario;
		String cargo_personal;
		String oficina;
		String usuarioper;
		String es_responsable;
		int tipo_firma;
		String nombre_imagen="";
		String nombre_pfx="";
		String ruta_pfx="";
		String ruta_imagen="";
		
		String validacion="";
		
		String codigoUsuarioBuscar;
		
		session.removeAttribute("c_usuario_p");
		
		
		BUsuario usuario_sistema = (BUsuario)session.getAttribute("usuario_sistema");
		codigo_personal = ((FFormMantPersonal)form).getCodigo_personal();
		nombreapellido_usuario = ((FFormMantPersonal)form).getNombreapellido_usuario();
		c_usuario = ((FFormMantPersonal)form).getC_usuario();
		cargo_personal = ((FFormMantPersonal)form).getCargo_personal();
		oficina = ((FFormMantPersonal)form).getOficina();
		es_responsable = ((FFormMantPersonal)form).getEs_responsable();
		tipo_firma = Integer.valueOf(((FFormMantPersonal)form).getTipo_firma());
		
		validacion=((FFormMantPersonal)form).getValidacion();
		//usuarioper = ((FFormMantPersonal)form).getUsuarioper();
		codigoUsuarioBuscar = ((FFormMantPersonal)form).getCodigoUsuarioBuscar();
		
	
		System.out.println("codigo_personal... "+ codigo_personal);
		System.out.println("nombreapellido_usuario... "+ nombreapellido_usuario);
		System.out.println("c_usuario... "+ c_usuario);
		System.out.println("cargo_personal... "+ cargo_personal);
		System.out.println("oficina... "+ oficina);
		System.out.println("es_responsable... "+ es_responsable);
		System.out.println("tipo firma ...."+ tipo_firma);
		
		System.out.println("validacion ...."+ validacion);
		System.out.println("codigoUsuarioBuscar "+codigoUsuarioBuscar);
		
		Collection tipo_sede =daoIAdministradorDAO.of_lista_tipo_sede(cnx, Integer.parseInt(usuario_sistema.getSede()));
		session.setAttribute("tipo_sede", tipo_sede);
		
		try {
			
			if(tipo.equals("buscar")){
				System.out.println("buscar");
				Collection listapersonal = daoIAdministradorDAO.of_lista_busca_personal(cnx,codigoUsuarioBuscar,Integer.parseInt(usuario_sistema.getSede()));
				session.setAttribute("listapersonal",listapersonal);
				Collection listaoficinas = daoIAdministradorDAO.of_lista_oficinas(cnx,Integer.parseInt(usuario_sistema.getSede()));
				session.setAttribute("listaoficinas",listaoficinas);
				session.setAttribute("operacion",tipo);
			}else 
			if (tipo.equals("personal") ){
				
				System.out.println("Si tipo es init" + tipo);
				System.out.println("Listar personal....." );
				
					Collection listapersonal = daoIAdministradorDAO.of_lista_personal(cnx,Integer.parseInt(usuario_sistema.getSede()));
					Collection listaoficinas = daoIAdministradorDAO.of_lista_oficinas(cnx,Integer.parseInt(usuario_sistema.getSede()));
					Collection listausuarios = daoIAdministradorDAO.of_lista_usuarios(cnx,Integer.parseInt(usuario_sistema.getSede()));
					Collection listacargos = daoIAdministradorDAO. of_lista_cargos(cnx);
					
					
					session.setAttribute("operacion",tipo);
					session.setAttribute("listapersonal",listapersonal);
					session.setAttribute("listaoficinas",listaoficinas);
					session.setAttribute("listausuarios",listausuarios);
					session.setAttribute("listacargos",listacargos);
										
					
			}else if (tipo.equalsIgnoreCase("nuevo")){
			//li_operacion = 2;
				try {
		
					System.out.println("Ingresando Personal .........." );
					
					cargo_personal=cargo_personal.toUpperCase();
					
					System.out.println("cargo_personal.UPERCASE.. "+ cargo_personal);
					
					
					daoIAdministradorDAO.PersonalIng(cnx,nombreapellido_usuario,c_usuario,cargo_personal,oficina,es_responsable,validacion);
									

					Collection listaoficinas = daoIAdministradorDAO.of_lista_oficinas(cnx,Integer.parseInt(usuario_sistema.getSede()));
					Collection listausuarios = daoIAdministradorDAO.of_lista_usuarios(cnx,Integer.parseInt(usuario_sistema.getSede()));
					Collection listapersonal = daoIAdministradorDAO.of_lista_personal(cnx,Integer.parseInt(usuario_sistema.getSede()));
					Collection listacargos = daoIAdministradorDAO.of_lista_cargos(cnx);
					
									
					session.setAttribute("operacion",tipo);
					session.setAttribute("listapersonal",listapersonal);
					session.setAttribute("listaoficinas",listaoficinas);
					session.setAttribute("listausuarios",listausuarios);
					session.setAttribute("listacargos",listacargos);
								
				}catch (SQLException e) {
					e.printStackTrace();
					closeConnection(cnx);
					//return mapping.getInputForward();
				}
					
					//session.removeAttribute("operacioncs");
			      			
		}else if (tipo.equalsIgnoreCase("editar")){
			try {
	
	
				System.out.println("Modificacion de... Personal .........." );
				cargo_personal=cargo_personal.toUpperCase();
				System.out.println("cargo_personal.UPERCASE.. "+ cargo_personal);
				
				detalleUsuario= daoIAdministradorDAO.UsuarioCons(cnx,c_usuario);
				
				String nombres=detalleUsuario.getNombres();
				String apellidos=detalleUsuario.getApellidos();
				boolean existeFirma = false;
				boolean existePfx = false;
				
				nombreapellido_usuario= nombres+" "+apellidos;
				
				FormFile myFile = (FormFile) session.getAttribute("vaRmyFile");
				FormFile myFilePfx = (FormFile) session.getAttribute("vaRmyFilePx");
				
				if(myFile != null && myFile.getFileName().length()>0){
				log.info("el contentType FILE UPLOAd es..");
				log.info("el fileName es..");
				String nombre_final_imagen = c_usuario+".jpg";
				String rutaDestino_imagen = Constantes.CarpetaArchivosDesarrolloSellos.getNombre()+nombre_final_imagen;
	            	            
 
	            File f = new File(rutaDestino_imagen);

					if (makeSureDirectoryExists(parent(f))) {
						// Se graba en la ruta del archivo;
						FileOutputStream out = new FileOutputStream(f);
						System.out.println("El out es :" + out);
						out.write(myFile.getFileData());
						System.out.println("El myFile.getFileDat es :");
						out.flush();
						out.close();
					}
					existeFirma=true;
					nombre_imagen=nombre_final_imagen;
				}
				if(myFilePfx != null && myFilePfx.getFileName().length()>0){
					log.info("el contentType FILE UPLOAd es..");
					log.info("el fileName es..");
					
					
					String nombre_temporal = myFilePfx.getFileName();

					int inicio = nombre_temporal.indexOf(".");
					nombre_temporal = nombre_temporal.substring(inicio, nombre_temporal.length());
					String nombre_final_pfx = c_usuario+nombre_temporal;
					
					log.info("Nombre-certificado "+nombre_final_pfx);
					String rutaDestino_pfx = Constantes.CarpetaArchivosDesarrolloPfx.getNombre()+nombre_final_pfx;
		            	            
	 
		            File f = new File(rutaDestino_pfx);

						if (makeSureDirectoryExists(parent(f))) {
							// Se graba en la ruta del archivo;
							FileOutputStream out = new FileOutputStream(f);
							System.out.println("El out es :" + out);
							out.write(myFilePfx.getFileData());
							System.out.println("El myFile.getFileDat es :");
							out.flush();
							out.close();
						}
						existePfx=true;
						nombre_pfx=nombre_final_pfx;
					}
				log.info("1->"+existeFirma);
				log.info("2-> "+existePfx);
				log.info("daoIAdministradorDAO.PersonalMod");
				
				log.info("daoIAdministradorDAO.PersonalMod(cnx,"+codigo_personal+","+nombreapellido_usuario+","+c_usuario+","+cargo_personal+","+oficina+","+es_responsable+","+existeFirma+","+existePfx+","+nombre_imagen+","+Constantes.CarpetaArchivosDesarrolloSellos.getNombre()+","+nombre_pfx+","+Constantes.CarpetaArchivosDesarrolloPfx.getNombre()+","+tipo_firma+");");
				daoIAdministradorDAO.PersonalMod(cnx,codigo_personal,nombreapellido_usuario,c_usuario,cargo_personal,oficina,es_responsable,existeFirma,existePfx,nombre_imagen,Constantes.CarpetaArchivosDesarrolloSellos.getNombre(),nombre_pfx,Constantes.CarpetaArchivosDesarrolloPfx.getNombre(),tipo_firma,validacion);
				
				detallePersonal= daoIAdministradorDAO.PersonalCons(cnx,codigo_personal);
				log.info("---->sede "+detallePersonal.getSede());
				
				
				Collection listaoficinas = daoIAdministradorDAO.of_lista_oficinas_x_sede(usuario_sistema.getSede());
				//Collection listaoficinas = daoIAdministradorDAO.of_lista_oficinas_x_sede(detallePersonal.getSede());
				
				
				Collection listausuarios = daoIAdministradorDAO.of_lista_usuarios(cnx,Integer.parseInt(usuario_sistema.getSede()));
				Collection listapersonal = daoIAdministradorDAO.of_lista_personal(cnx,Integer.parseInt(usuario_sistema.getSede()));
				
				
				Collection listanombresapell = daoIAdministradorDAO.of_lista_usuarios(c_usuario);
				Collection listacargos = daoIAdministradorDAO.of_lista_cargos(cnx);
				
				
				session.setAttribute("operacion",tipo);
				session.setAttribute("listapersonal",listapersonal);
				session.setAttribute("listaoficinas",listaoficinas);
				
				session.setAttribute("listausuarios",listausuarios);
				session.setAttribute("listanombresapell",listanombresapell);
				session.setAttribute("listacargos",listacargos);
				session.setAttribute("detallePersonal",detallePersonal);
				
				session.removeAttribute("vaRmyFile");
				session.removeAttribute("vaRmyFilePfx");
				session.setAttribute("c_usuario_p",c_usuario);
				/*session.setAttribute("oficina",oficina);
				*/
				
		
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
	
				daoIAdministradorDAO.PersonalElim(cnx,codigo_personal);
			
				Collection listaoficinas = daoIAdministradorDAO.of_lista_oficinas(cnx,Integer.parseInt(usuario_sistema.getSede()));
				Collection listausuarios = daoIAdministradorDAO.of_lista_usuarios(cnx,Integer.parseInt(usuario_sistema.getSede()));
				Collection listapersonal = daoIAdministradorDAO.of_lista_personal(cnx,Integer.parseInt(usuario_sistema.getSede()));
				
				Collection listacargos = daoIAdministradorDAO.of_lista_cargos(cnx);
				
				/*System.out.println("listapersonal" + listapersonal);
				System.out.println("listaoficinas" + listaoficinas);
				System.out.println("listausuarios" + listausuarios);
				System.out.println("listacargos" + listacargos);
				
				System.out.println("listapersonal" + listapersonal);*/
				session.setAttribute("operacion",tipo);
				session.setAttribute("listapersonal",listapersonal);
				session.setAttribute("listausuarios",listausuarios);
				session.setAttribute("listaoficinas",listaoficinas);
				session.setAttribute("listacargos",listacargos);
				
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
