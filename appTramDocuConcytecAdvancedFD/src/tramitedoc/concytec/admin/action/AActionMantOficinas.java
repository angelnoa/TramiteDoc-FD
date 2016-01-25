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

//import com.sun.jmx.remote.util.Service;





import tramitedoc.concytec.dao.*;
import tramitedoc.concytec.admin.form.*;
import tramitedoc.concytec.bean.*;
import tramitedoc.concytec.dao.interfaces.*;
import tramitedoc.concytec.service.DocumentosInternosServiceImplement;
import tramitedoc.concytec.util.*;
import tramitedoc.concytec.dao.sql.*;

import java.sql.Connection;
import java.sql.SQLException;
/**
 * AUTOR		: Machuca Ovalle
 * FECHA		: 03-04-2006
 * VERSION		: 1.0
 * DESCRIPCIÓN	: Accion de Logeo  
 */

public class AActionMantOficinas extends ValidaSessionAction{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession(true);
		Connection cnx = getConnection(request, "principal");
		System.out.println("El cnx............" + cnx);	    
	  	
		IAdministradorDAO daoIAdministradorDAO = new SqlAdministradorDAO();
		DocumentosInternosService service = new DocumentosInternosServiceImplement();	
		BOficinas detalleOficinas=new BOficinas();
		
		FFormMantOficinas oForm = (FFormMantOficinas) form;
		String tipo = oForm.getTipo() == null ? "init" : oForm.getTipo().trim();
		System.out.println("El tipo 1 "+ tipo);
		tipo = tipo.equals("") ? "init" : tipo;
		System.out.println("El tipo 2 "+ tipo);
		
		String codigo_oficina;
		String descripcion_oficina;
		String nombre_corto;
		String siglas_oficina;
		String es_activo;
		String path = Constantes.CarpetaArchivosDesarrolloSellos.getNombre();
		boolean existeFirma = false;
		
		BUsuario usuario_sistema = (BUsuario)session.getAttribute("usuario_sistema");
		codigo_oficina = ((FFormMantOficinas)form).getCodigo_oficina();
		descripcion_oficina = ((FFormMantOficinas)form).getDescripcion_oficina();
		nombre_corto = ((FFormMantOficinas)form).getNombre_corto();
		siglas_oficina = ((FFormMantOficinas)form).getSiglas_oficina();
		es_activo = ((FFormMantOficinas)form).getEs_activo();
		
		String sede = ((FFormMantOficinas)form).getSede();
		String oficina = ((FFormMantOficinas)form).getOficina();
		
		
		
		System.out.println("codigo_oficina... "+ codigo_oficina);
		System.out.println("descripcion_oficina... "+ descripcion_oficina);
		System.out.println("nombre_corto... "+ nombre_corto);
		System.out.println("siglas_oficina... "+ siglas_oficina);
		System.out.println("es_activo... "+ es_activo);
		
		System.out.println("sede... "+ sede);
		System.out.println("oficina... "+ oficina);
		
		Collection tipo_sede =daoIAdministradorDAO.of_lista_tipo_sede(cnx, Integer.parseInt(usuario_sistema.getSede()));
		session.setAttribute("tipo_sede", tipo_sede);
		
		try {
			
			
			if (tipo.equals("oficina") ){
				
				System.out.println("Si tipo es init " + tipo);
				System.out.println("Listar Oficinas....." );
				
					Collection listaoficinas = daoIAdministradorDAO.of_lista_oficinas(cnx,Integer.parseInt(usuario_sistema.getSede()));
					
					System.out.println("listaoficinas" + listaoficinas);
					
					session.setAttribute("operacion",tipo);
					session.setAttribute("listaoficinas",listaoficinas);
					
					
			}else if (tipo.equalsIgnoreCase("nuevo")){
			//li_operacion = 2;
				try {
		
					System.out.println("Ingresando oficinas .........." );
					
					descripcion_oficina=descripcion_oficina.toUpperCase();
					siglas_oficina=siglas_oficina.toUpperCase();
					nombre_corto=nombre_corto.toUpperCase();
					
					int grupooficina=0;
					//VERIFICO SI SE CREA NUEVO GRUPO
					if(sede.equals("3")){//FONDECYT
						//NO SE CREA REGISTRO EN GRUPO OFICINA
						grupooficina=3;	
					}else{//CUALQUIER OTRO
						if(oficina.equals("0")){
							// CREO REGISTRO EN GRUPO OFICINA
							grupooficina = daoIAdministradorDAO.GrupoOficinaIng(descripcion_oficina, siglas_oficina, Integer.parseInt(sede));
							
						}else{
							grupooficina=service.getGrupoOficina(Integer.parseInt(oficina));
						}
						
					}
					
					
					int codigo_oficina_nuevo = daoIAdministradorDAO.OficinaIng(cnx,nombre_corto,descripcion_oficina,siglas_oficina,es_activo,grupooficina,sede,oficina);
					daoIAdministradorDAO.IngresaPersona(codigo_oficina_nuevo,nombre_corto,siglas_oficina,Integer.parseInt(oficina),Integer.parseInt(sede));
					
					
					if(sede.equals("3")){
						daoIAdministradorDAO.setOrdenOficina(codigo_oficina_nuevo,Integer.parseInt(sede));	
					}else{
						daoIAdministradorDAO.setOrdenOficina(codigo_oficina_nuevo,Integer.parseInt(sede),service.getGrupoOficina(codigo_oficina_nuevo),Integer.parseInt(oficina));	

					}
					
										
					Collection listaoficinas = daoIAdministradorDAO.of_lista_oficinas(cnx,Integer.parseInt(usuario_sistema.getSede()));
					
					session.setAttribute("listaoficinas",listaoficinas);
					
								
				}catch (SQLException e) {
					e.printStackTrace();
					closeConnection(cnx);
					//return mapping.getInputForward();
		
				}
					
					//session.removeAttribute("operacioncs");
			      			
		}else if (tipo.equalsIgnoreCase("editar")){
			try {
				String nombreArchivoSelloOficina = service.of_nombre_archivo_sello_oficina(codigo_oficina);
				System.out.println("Modificacion de... Oficinas .........."+nombreArchivoSelloOficina);
				
				String nombre_final="";
				descripcion_oficina=descripcion_oficina.toUpperCase();
				siglas_oficina=siglas_oficina.toUpperCase();
				nombre_corto=nombre_corto.toUpperCase();
				
				FormFile myFile = (FormFile) session.getAttribute("vaRmyFile");
				String locked = (String) session.getAttribute("locked");
				locked = (locked==null)? "":locked.trim();
				
				
				if(myFile != null && myFile.getFileName().length()>0 && locked.equals(codigo_oficina.trim())){
				String contentType = myFile.getContentType();
				log.info("el contentType FILE UPLOAd es.."
						+ contentType);
				log.info("el fileName es.."+ myFile.getFileName());

				nombre_final = nombre_corto+".jpg";
				String rutaDestino = path+nombre_final;
	            	            
				
	            File f = new File(rutaDestino);

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
	         
				}
				
				//verificar para la modificacion
				//daoIAdministradorDAO.OficinaIng(cnx,nombre_corto,descripcion_oficina,siglas_oficina,es_activo,grupooficina,sede,oficina);
				
				if(existeFirma){
					daoIAdministradorDAO.OficinaMod(cnx,codigo_oficina,nombre_corto,descripcion_oficina,siglas_oficina,es_activo,nombre_final,Constantes.CarpetaArchivosDesarrolloSellos.getNombre(),myFile.getContentType());
					nombreArchivoSelloOficina=nombre_final;
				}else{
					
					daoIAdministradorDAO.OficinaMod(cnx,codigo_oficina,nombre_corto,descripcion_oficina,siglas_oficina,es_activo);
				}
				
				detalleOficinas= daoIAdministradorDAO.OficinaCons(cnx,codigo_oficina); 
				Collection listaoficinas = daoIAdministradorDAO.of_lista_oficinas(cnx,Integer.parseInt(usuario_sistema.getSede()));
				
				System.out.println("listaoficinas" + listaoficinas);
				System.out.println("detalleOficinas" + detalleOficinas);
				
				request.setAttribute("nombreArchivoSelloOficina", nombreArchivoSelloOficina);
				session.setAttribute("operacion",tipo);
				session.setAttribute("listaoficinas",listaoficinas);
				session.setAttribute("detalleOficinas",detalleOficinas);
				session.removeAttribute("vaRmyFile");
				session.setAttribute("nombre_corto_ls", nombre_corto);
		
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
	
				System.out.println("Eliminar  Oficinas.........." );
	
				daoIAdministradorDAO.OficinaElim(cnx,codigo_oficina);
				
				Collection listaoficinas = daoIAdministradorDAO.of_lista_oficinas(cnx,Integer.parseInt(usuario_sistema.getSede()));
				
				System.out.println("listaoficinas" + listaoficinas);
				session.setAttribute("operacion",tipo);
				session.setAttribute("listaoficinas",listaoficinas);
				
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
