package tramitedoc.concytec.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import tramitedoc.concytec.bean.BArchivo;
import tramitedoc.concytec.bean.BInfo;
import tramitedoc.concytec.dao.interfaces.DocumentosInternosService;
import tramitedoc.concytec.dao.interfaces.IReiterativoDAO;
import tramitedoc.concytec.dao.interfaces.IUsuarioDAO;
import tramitedoc.concytec.dao.sql.SqlReiterativoDAO;
import tramitedoc.concytec.dao.sql.SqlUsuarioDAO;
import tramitedoc.concytec.form.FFormUploadArchivo;
import tramitedoc.concytec.service.DocumentosInternosServiceImplement;
import tramitedoc.concytec.util.Funciones;
import tramitedoc.concytec.util.IConstante;
import tramitedoc.concytec.util.ValidaSessionAction;

public class AActionUploadArchivoFirmadoManualmente extends ValidaSessionAction implements IConstante{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FFormUploadArchivo uploadForm = (FFormUploadArchivo)form;
		HttpSession session=request.getSession();
		
		Collection rs_upload_doc = new ArrayList();
		Connection cnx = getConnection(request, "principal");
		IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
		IReiterativoDAO iRe=new SqlReiterativoDAO();
		DocumentosInternosService service = new DocumentosInternosServiceImplement();
		
		if(uploadForm.getOpcion().equals("default")){
			
			rs_upload_doc = iRe.lista_upload_documentos_detalle_firma_manual(cnx,Integer.parseInt(uploadForm.getCodigo_documento()));
			uploadForm.setArchivos(rs_upload_doc);
			
			if(rs_upload_doc.isEmpty()){
				BInfo binfo = new BInfo();
				binfo = service.getInfoSobreDocumento(Integer.parseInt(uploadForm.getCodigo_documento()));
				session.setAttribute("nombre_archivo_origen", binfo.getNombrearchivo().substring(9,binfo.getNombrearchivo().length()));
			}
			
			
			
		}else if(uploadForm.getOpcion().equals("adjuntar")){
			
			 BArchivo archivo = new BArchivo();
			 String nombreusuario = (String)session.getAttribute("nombreusuario");
				archivo.setUsuario(nombreusuario);
				

				FormFile myFile = uploadForm.getTheFile();
				String contentType = myFile.getContentType();
				System.out.println("el contentType FILE UPLOAd es.." + contentType);

				if(!myFile.getFileName().equals("")){
					//archivo.setNombre_archivo(myFile.getFileName());
					archivo.setNombre_archivo((String)session.getAttribute("nombre_archivo_origen"));
					System.out.println("el fileName es.."
							+ archivo.getNombre_archivo());

					archivo.setData(myFile.getFileData());					
				}
				
				subir_archivo(uploadForm.getCodigo_documento(), archivo);				
			
				
				rs_upload_doc = iRe.lista_upload_documentos_detalle_firma_manual(cnx,Integer.parseInt(uploadForm.getCodigo_documento()));
			//rs_upload_doc = daoIUsuarioDAO.lista_upload_documentos_detalle(cnx,Integer.parseInt(uploadForm.getCodigo_documento()));
			uploadForm.setArchivos(rs_upload_doc);
			
		}else if(uploadForm.getOpcion().equals("eliminar")){
			daoIUsuarioDAO.EliminarDocumentoDetUpload(cnx,Integer.parseInt(uploadForm.getId_detalle()));
			rs_upload_doc = iRe.lista_upload_documentos_detalle_firma_manual(cnx,Integer.parseInt(uploadForm.getCodigo_documento()));
			uploadForm.setArchivos(rs_upload_doc);
			
		}
		
		return (mapping.findForward("upload"));
	}
	
	private void subir_archivo(String documento,BArchivo archivo) throws SQLException, IOException,Exception {

		IUsuarioDAO daoIUsuarioDAO = new SqlUsuarioDAO();
		Funciones funciones = new Funciones();
		String fileName = archivo.getNombre_archivo();
		//String filePath = "/documents/docTramite";
		
		if (fileName!=null && !fileName.equals("")) {

			int punto = fileName.lastIndexOf('.');
			System.out.println("el puntoLUUUU es.." + punto);
			System.out.println("el puntoLUUUU + 1 es.."
					+ fileName.substring(punto + 1));
			if (fileName.substring(punto + 1).equals("DOC")) {

				fileName = fileName.replace("DOC", "doc");
				System.out
						.println("El Cambiando el nombre a miniscula..XXXXXX:"
								+ fileName);
			}

			System.out.println("El Case es 5..");

			String ls_new_nombre_upload = funciones.of_valida_letras(fileName);

			if (funciones.validaNombreDelArchivo(ls_new_nombre_upload)) {
				System.out.println("El ls_new_nombre_upload ees.."
						+ ls_new_nombre_upload);

				File fileToCreate = new File(filePath, ls_new_nombre_upload);

				if (!fileToCreate.exists()) {
					FileOutputStream fileOutStream = new FileOutputStream(
							fileToCreate);
					fileOutStream.write(archivo.getData());
					fileOutStream.flush();
					fileOutStream.close();
				}
				daoIUsuarioDAO.CrearDocumentoDocEntradaDetUploadIngFirmaManual(
						Integer.parseInt(documento.trim()),
						ls_new_nombre_upload, filePath, "A", "P", archivo.getUsuario(),"O");

			}

		}
	}

}