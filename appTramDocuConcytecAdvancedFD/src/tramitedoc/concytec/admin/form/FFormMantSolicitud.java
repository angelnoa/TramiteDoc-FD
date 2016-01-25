package tramitedoc.concytec.admin.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * AUTOR		: Machuca Ovalle
 * FECHA		: 03-04-2006
 * VERSION		: 1.0
 * DESCRIPCIÓN	: Mapeo de datos del logeo recibidos del jsp  
 */

public class FFormMantSolicitud extends ActionForm{

	private String tipo = null;
	private String codigo_solicitud = null;
	private String nombre_solicitud = null;
	
	/**
	 * @return
	 */
	
	 public void reset(ActionMapping mapping, HttpServletRequest request) {
			this.tipo = null;
			this.codigo_solicitud = null;
			this.nombre_solicitud = null;
			
		    }

	public String getCodigo_solicitud() {
		return codigo_solicitud;
	}

	public void setCodigo_solicitud(String codigo_solicitud) {
		this.codigo_solicitud = codigo_solicitud;
	}

	public String getNombre_solicitud() {
		return nombre_solicitud;
	}

	public void setNombre_solicitud(String nombre_solicitud) {
		this.nombre_solicitud = nombre_solicitud;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
	
	 
	


	/**
	 * @return
	 */
	

}