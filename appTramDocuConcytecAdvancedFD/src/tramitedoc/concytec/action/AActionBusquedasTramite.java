package tramitedoc.concytec.action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.mail.Session;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import tramitedoc.concytec.bean.BArchivo;
import tramitedoc.concytec.bean.BDocumento;
import tramitedoc.concytec.dao.interfaces.IBusquedasDAO;
import tramitedoc.concytec.dao.interfaces.IUsuarioDAO;
import tramitedoc.concytec.dao.sql.SqlBusquedasDAO;
import tramitedoc.concytec.dao.sql.SqlUsuarioDAO;
import tramitedoc.concytec.form.FFormDocumentoInterno;
import tramitedoc.concytec.form.FFormDocumentoInternoEspecialC;
import tramitedoc.concytec.form.FFormDocumentoInternoEspecialT;
import tramitedoc.concytec.form.FFormMantDerivar;
import tramitedoc.concytec.form.FFormMantDocumento;
import tramitedoc.concytec.form.FFormMantPrecerrar;
import tramitedoc.concytec.util.ValidaSessionAction;

public class AActionBusquedasTramite extends ValidaSessionAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Connection cnx = getConnection(request, "principal");
		IBusquedasDAO iBusqueda = new SqlBusquedasDAO();
		IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
		HttpSession session = request.getSession(true);
		String tipo = request.getParameter("tipo");
		tipo = tipo == null ? "" : tipo;
		if (tipo.equals("instituciones")) {
			String codigo = request.getParameter("codigo");
			String nombre = request.getParameter("nombre");

			request.setAttribute("instituciones",
					iBusqueda.buscar_instituciones(codigo, nombre));
			return (mapping.findForward("instituciones"));
		} else if (tipo.equals("usuarios")) {
			String codigo = request.getParameter("codigo");
			String nombre = request.getParameter("nombre");

			request.setAttribute("usuarios",
					iBusqueda.buscar_usuarios(codigo, nombre));
			return (mapping.findForward("usuarios"));
		} else if (tipo.equals("documentos_pendientes_cerrar")) {

			FFormMantPrecerrar oForm = (FFormMantPrecerrar) session
					.getAttribute("FFormMantPrecerrar");
			if (oForm.getPaquete() == null || oForm.getPaquete().size() == 0) {
				oForm.setPaquete(iBusqueda.documentos_pend_cerrar());
				session.setAttribute("FFormMantPrecerrar", oForm);
			}

			return (mapping.findForward("documentos_pendientes_cerrar"));
		} else if (tipo.equals("eli_archivo")) {
			String id = request.getParameter("id");
			id = id == null ? "0" : id;
			int idd = Integer.parseInt(id);

			FFormMantDocumento oForm = (FFormMantDocumento) session
					.getAttribute("FFormMantDocumento");
			if (oForm.getArchivos() != null && oForm.getArchivos().size() > 0) {
				BArchivo archivo = (BArchivo) oForm.getArchivos().toArray()[idd];
				oForm.getArchivos().remove(archivo);				

			} else {
				request.setAttribute("mensaje_alerta",
						"No se ha encontrado archivos registrados");

			}
			return (mapping.findForward("pagina_popup"));
		}else if (tipo.equals("eli_archivo_admin")) {
			String id = request.getParameter("id");
			String cod = request.getParameter("cod");
			id = id == null ? "0" : id;
			int idd = Integer.parseInt(id);
			
			
			daoIUsuarioDAO.actualizar_detalle_documento(id);
			
			Collection rs_upload_doc = new ArrayList();
			rs_upload_doc = daoIUsuarioDAO.lista_upload_documentos_detalle(cnx,
					Integer.parseInt(cod.trim()));
			
			session.setAttribute("rs_upload_doc",rs_upload_doc);
			
			
			return (mapping.findForward("admin"));
			
		}else if (tipo.equals("eli_archivo_deriva")) {
			String id = request.getParameter("id");
			String nombres_files = (String) session.getAttribute("nombre_file_adjunto");
			nombres_files=nombres_files==null? "":nombres_files;
						
			id = id == null ? "0" : id;
			int idd = Integer.parseInt(id);

			FFormMantDerivar oForm = (FFormMantDerivar) session.getAttribute("FFormMantDerivar");
			if (oForm.getArchivos() != null && oForm.getArchivos().size() > 0) {
				BArchivo archivo = (BArchivo) oForm.getArchivos().toArray()[idd];
				oForm.getArchivos().remove(archivo);
				/**
				 * AQUI SE REALIZA LA VERIFICACION DE NOMBRES
				 */
				if(oForm.getArchivos().size()>0){
					session.setAttribute("contador","1");	
				}else{
					session.setAttribute("contador","0");
				}
				session.setAttribute("nombre_file_adjunto",nombres_files);

			} else {
				request.setAttribute("mensaje_alerta","No se ha encontrado archivos registrados");
				session.setAttribute("contador","0");
			}
			return (mapping.findForward("pagina_popup_deriva"));
		}else if (tipo.equals("eli_archivo_interno_normal")) {
			String id = request.getParameter("id");
			id = id == null ? "0" : id;
			int idd = Integer.parseInt(id);

			FFormDocumentoInterno oForm = (FFormDocumentoInterno) session.getAttribute("FFormDocumentoInterno");
			if (oForm.getArchivos() != null && oForm.getArchivos().size() > 0) {
				BArchivo archivo = (BArchivo) oForm.getArchivos().toArray()[idd];
				oForm.getArchivos().remove(archivo);				

			} else {
				request.setAttribute("mensaje_alerta",
						"No se ha encontrado archivos registrados");

			}
			return (mapping.findForward("pagina_popup_internos"));
		}
		else if (tipo.equals("eli_archivo_interno_especialc")) {
			String id = request.getParameter("id");
			id = id == null ? "0" : id;
			int idd = Integer.parseInt(id);

			FFormDocumentoInternoEspecialC oForm = (FFormDocumentoInternoEspecialC) session.getAttribute("FFormDocumentoInternoEspecialC");
			if (oForm.getArchivos() != null && oForm.getArchivos().size() > 0) {
				BArchivo archivo = (BArchivo) oForm.getArchivos().toArray()[idd];
				oForm.getArchivos().remove(archivo);				

			} else {
				request.setAttribute("mensaje_alerta",
						"No se ha encontrado archivos registrados");

			}
			return (mapping.findForward("pagina_popup_internosc"));
		}
		else if (tipo.equals("eli_archivo_interno_especialt")) {
			String id = request.getParameter("id");
			id = id == null ? "0" : id;
			int idd = Integer.parseInt(id);

			FFormDocumentoInternoEspecialT oForm = (FFormDocumentoInternoEspecialT) session.getAttribute("FFormDocumentoInternoEspecialT");
			if (oForm.getArchivos() != null && oForm.getArchivos().size() > 0) {
				BArchivo archivo = (BArchivo) oForm.getArchivos().toArray()[idd];
				oForm.getArchivos().remove(archivo);				

			} else {
				request.setAttribute("mensaje_alerta",
						"No se ha encontrado archivos registrados");

			}
			return (mapping.findForward("pagina_popup_internost"));
		}
		else if (tipo.equals("ver_archivo")) {

			String id = request.getParameter("id");
			id = id == null ? "0" : id;
			int idd = Integer.parseInt(id);
			FFormMantDocumento oForm = (FFormMantDocumento) session
					.getAttribute("FFormMantDocumento");
			if (oForm.getArchivos() != null && oForm.getArchivos().size() > 0) {

				BArchivo archivo = (BArchivo) oForm.getArchivos().toArray()[idd];

				System.out.println("Dentro de Detalle Modificar DOC..");

				response.setHeader("Content-Disposition",
						"attachment;filename=" + archivo.getNombre_archivo());

				ServletOutputStream salida = response.getOutputStream();
				salida.write(archivo.getData());
				salida.close();

				return (null);
			} else {
				request.setAttribute("mensaje_alerta",
						"No se ha encontrado archivos registrados");
				return (mapping.findForward("pagina_popup"));
			}

		}else if (tipo.equals("ver_archivo_deriva")) {

			String id = request.getParameter("id");
			id = id == null ? "0" : id;
			int idd = Integer.parseInt(id);
			FFormMantDerivar oForm = (FFormMantDerivar) session
					.getAttribute("FFormMantDerivar");
			if (oForm.getArchivos() != null && oForm.getArchivos().size() > 0) {

				BArchivo archivo = (BArchivo) oForm.getArchivos().toArray()[idd];

				System.out.println("Dentro de Detalle Modificar DOC..");

				response.setHeader("Content-Disposition",
						"attachment;filename=" + archivo.getNombre_archivo());

				ServletOutputStream salida = response.getOutputStream();
				salida.write(archivo.getData());
				salida.close();

				return (null);
			} else {
				request.setAttribute("mensaje_alerta",
						"No se ha encontrado archivos registrados");
				return (mapping.findForward("pagina_popup"));
			}

		}else if (tipo.equals("ver_archivo_interno_normal")) {

			String id = request.getParameter("id");
			id = id == null ? "0" : id;
			int idd = Integer.parseInt(id);
			FFormDocumentoInterno oForm = (FFormDocumentoInterno) session.getAttribute("FFormDocumentoInterno");
			if (oForm.getArchivos() != null && oForm.getArchivos().size() > 0) {

				BArchivo archivo = (BArchivo) oForm.getArchivos().toArray()[idd];

				response.setHeader("Content-Disposition",
						"attachment;filename=" + archivo.getNombre_archivo());

				ServletOutputStream salida = response.getOutputStream();
				salida.write(archivo.getData());
				salida.close();

				return (null);
			} else {
				request.setAttribute("mensaje_alerta",
						"No se ha encontrado archivos registrados");
				return (mapping.findForward("pagina_popup_internos"));
			}

		}else if (tipo.equals("ver_archivo_interno_especialc")) {

			String id = request.getParameter("id");
			id = id == null ? "0" : id;
			int idd = Integer.parseInt(id);
			FFormDocumentoInternoEspecialC oForm = (FFormDocumentoInternoEspecialC) session.getAttribute("FFormDocumentoInternoEspecialC");
			if (oForm.getArchivos() != null && oForm.getArchivos().size() > 0) {

				BArchivo archivo = (BArchivo) oForm.getArchivos().toArray()[idd];

				System.out.println("Dentro de Detalle Modificar DOC..");

				response.setHeader("Content-Disposition",
						"attachment;filename=" + archivo.getNombre_archivo());

				ServletOutputStream salida = response.getOutputStream();
				salida.write(archivo.getData());
				salida.close();

				return (null);
			} else {
				request.setAttribute("mensaje_alerta",
						"No se ha encontrado archivos registrados");
				return (mapping.findForward("pagina_popup_internosc"));
			}

		}else if (tipo.equals("ver_archivo_interno_especialt")) {

			String id = request.getParameter("id");
			id = id == null ? "0" : id;
			int idd = Integer.parseInt(id);
			FFormDocumentoInternoEspecialT oForm = (FFormDocumentoInternoEspecialT) session.getAttribute("FFormDocumentoInternoEspecialT");
			if (oForm.getArchivos() != null && oForm.getArchivos().size() > 0) {

				BArchivo archivo = (BArchivo) oForm.getArchivos().toArray()[idd];

				System.out.println("Dentro de Detalle Modificar DOC..");

				response.setHeader("Content-Disposition",
						"attachment;filename=" + archivo.getNombre_archivo());

				ServletOutputStream salida = response.getOutputStream();
				salida.write(archivo.getData());
				salida.close();

				return (null);
			} else {
				request.setAttribute("mensaje_alerta",
						"No se ha encontrado archivos registrados");
				return (mapping.findForward("pagina_popup_internost"));
			}

		}else if (tipo.equals("modificar_estado_docinterno")) {

			String id = request.getParameter("id");
			id = id == null ? "0" : id;
			int idd = Integer.parseInt(id);
			String pagina="";
			FFormDocumentoInterno oForm = (FFormDocumentoInterno) session.getAttribute("FFormDocumentoInterno");
			FFormDocumentoInternoEspecialC oFormc = (FFormDocumentoInternoEspecialC) session.getAttribute("FFormDocumentoInternoEspecialC");
			FFormDocumentoInternoEspecialT oFormt = (FFormDocumentoInternoEspecialT) session.getAttribute("FFormDocumentoInternoEspecialT");
			
			if(oForm != null){
				log.info("Entre oForm");
			if (oForm.getArchivos() != null && oForm.getArchivos().size() > 0) {
				log.info("Entre oForm");
				Collection temporaln = new ArrayList();
				Iterator it = oForm.getArchivos().iterator();
				int n=0;
				while(it.hasNext()){
		  				log.info("aqui8");
		  					BArchivo archivoenlista=(BArchivo)it.next();
		  							
		  					//if(archivoenlista.getNombre_archivo().equals(nombrex)){
		  					if(n == idd){
		  					log.info("aqui9");
		  						boolean valor = archivoenlista.isIschecked();
		  						
		  						if(valor){
		  							archivoenlista.setIschecked(false);
		  						
		  						}else{
		  							archivoenlista.setIschecked(true);
		  						}

		  						log.info(archivoenlista.getNombre_archivo());
		  					}
		  					//oForm.setArchivos(archivos)
		  					temporaln.add(archivoenlista);	
		  					n++;
				}
				oForm.setArchivos(temporaln);
				pagina = "pagina_popup_internos";
			}
			}
			
			if(oFormc != null){
				log.info("Entre oFormc");
			if (oFormc.getArchivos() != null && oFormc.getArchivos().size() > 0) {
				log.info("Entre oFormc");
				Collection temporaln = new ArrayList();
				Iterator it = oFormc.getArchivos().iterator();
				int n=0;
				while(it.hasNext()){
		  				log.info("aqui8");
		  					BArchivo archivoenlista=(BArchivo)it.next();
		  							
		  					//if(archivoenlista.getNombre_archivo().equals(nombrex)){
		  					if(n == idd){
		  					log.info("aqui9");
		  						boolean valor = archivoenlista.isIschecked();
		  						
		  						if(valor){
		  							archivoenlista.setIschecked(false);
		  						
		  						}else{
		  							archivoenlista.setIschecked(true);
		  						}

		  						log.info(archivoenlista.getNombre_archivo());
		  					}
		  					//oForm.setArchivos(archivos)
		  					temporaln.add(archivoenlista);	
		  					n++;
				}
				oFormc.setArchivos(temporaln);
				pagina = "pagina_popup_internosc";
			}
			}
			if(oFormt != null){
				log.info("Entre oFormt");
			if (oFormt.getArchivos() != null && oFormt.getArchivos().size() > 0) {
				log.info("Entre oFormt");
				Collection temporaln = new ArrayList();
				Iterator it = oFormt.getArchivos().iterator();
				int n=0;
				while(it.hasNext()){
		  				log.info("aqui8");
		  					BArchivo archivoenlista=(BArchivo)it.next();
		  							
		  					//if(archivoenlista.getNombre_archivo().equals(nombrex)){
		  					if(n == idd){
		  					log.info("aqui9");
		  						boolean valor = archivoenlista.isIschecked();
		  						
		  						if(valor){
		  							archivoenlista.setIschecked(false);
		  						
		  						}else{
		  							archivoenlista.setIschecked(true);
		  						}

		  						log.info(archivoenlista.getNombre_archivo());
		  					}
		  					//oForm.setArchivos(archivos)
		  					temporaln.add(archivoenlista);	
		  					n++;
				}
				oFormt.setArchivos(temporaln);
				pagina = "pagina_popup_internost";
			}
			}
			return (mapping.findForward(pagina));
			
		}else if (tipo.equals("seleccionar")) {

			System.out.println("entro a Seleccion");
			String val[] = request.getParameterValues("documento");

			FFormMantPrecerrar oForm = (FFormMantPrecerrar) session
					.getAttribute("FFormMantPrecerrar");

			Iterator it = null;
			String documento = "";
			String secuencia = "";
			

			// HashMap<String, BDocumento> map = new HashMap<String,
			// BDocumento>();

			Collection paquete = oForm.getPaquete();
			inicializar(paquete);
			for (int i = 0; i < val.length; i++) {
				documento = val[i].split("-")[0];
				secuencia = val[i].split("-")[1];

				Cambiar_Estado(paquete, documento, secuencia);

			}

			session.setAttribute("FFormMantPrecerrar", oForm);
			return (mapping.findForward("documentos_pendientes_cerrar"));
		}

		return (mapping.findForward("default"));
	}

	private void inicializar(Collection paquete) {
		// TODO Auto-generated method stub
		Iterator it = paquete.iterator();
		while (it.hasNext()) {
			BDocumento doc = (BDocumento) it.next();

			doc.setSeleccionado(false);
		}
	}

	private void Cambiar_Estado(Collection paquete, String documento,
			String secuencia) {
		Iterator it = paquete.iterator();
		while (it.hasNext()) {
			BDocumento doc = (BDocumento) it.next();
			if (doc.getCodigo_documento().equals(documento) && doc.getSecuencia_movimiento().equals(secuencia)) {
				doc.setSeleccionado(true);
				System.out.println("Codigo documento="+doc.getCodigo_documento()+" Secuencia ="+doc.getSecuencia_movimiento());
			}
		}
	}

}
