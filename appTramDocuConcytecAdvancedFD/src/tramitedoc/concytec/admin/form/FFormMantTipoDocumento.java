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

public class FFormMantTipoDocumento extends ActionForm{

	private String tipo = null;
	private String codigo_tipo = null;
	private String descripcion_tipo = null;
	
	/**
	 * @return
	 */
	
	 public void reset(ActionMapping mapping, HttpServletRequest request) {
			this.tipo = null;
			this.codigo_tipo = null;
			this.descripcion_tipo = null;
			
		    }

	public String getCodigo_tipo() {
		return codigo_tipo;
	}

	public void setCodigo_tipo(String codigo_tipo) {
		this.codigo_tipo = codigo_tipo;
	}

	public String getDescripcion_tipo() {
		return descripcion_tipo;
	}

	public void setDescripcion_tipo(String descripcion_tipo) {
		this.descripcion_tipo = descripcion_tipo;
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