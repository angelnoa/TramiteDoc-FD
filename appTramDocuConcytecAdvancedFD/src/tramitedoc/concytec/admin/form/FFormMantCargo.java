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

public class FFormMantCargo extends ActionForm{

	private String tipo = null;
	private String codigo_cargo = null;
	private String nombrecargo = null;
	
	/**
	 * @return
	 */
	
	 public void reset(ActionMapping mapping, HttpServletRequest request) {
			this.tipo = null;
			this.codigo_cargo = null;
			this.nombrecargo = null;
			
		    }

	public String getCodigo_cargo() {
		return codigo_cargo;
	}

	public void setCodigo_cargo(String codigo_cargo) {
		this.codigo_cargo = codigo_cargo;
	}

	public String getNombrecargo() {
		return nombrecargo;
	}

	public void setNombrecargo(String nombrecargo) {
		this.nombrecargo = nombrecargo;
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