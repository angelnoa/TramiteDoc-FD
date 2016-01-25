package tramitedoc.concytec.form;

import java.util.Collection;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class FFormUploadArchivo extends ActionForm {

	private String codigo_documento;
	private String id_detalle;
	private String opcion="default";
	private Collection archivos;
	private FormFile theFile;
	
	
	
	public int getContador(){
		if(archivos==null || archivos.size()==0)
			return 0;
		else
			return archivos.size();
				
	}
	
	public String getId_detalle() {
		return id_detalle;
	}
	public void setId_detalle(String id_detalle) {
		this.id_detalle = id_detalle;
	}
	public FormFile getTheFile() {
		return theFile;
	}
	public void setTheFile(FormFile theFile) {
		this.theFile = theFile;
	}
	public String getCodigo_documento() {
		return codigo_documento;
	}
	public void setCodigo_documento(String codigo_documento) {
		this.codigo_documento = codigo_documento;
	}
	public String getOpcion() {
		return opcion;
	}
	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}
	public Collection getArchivos() {
		return archivos;
	}
	public void setArchivos(Collection archivos) {
		this.archivos = archivos;
	}
	
	

}
