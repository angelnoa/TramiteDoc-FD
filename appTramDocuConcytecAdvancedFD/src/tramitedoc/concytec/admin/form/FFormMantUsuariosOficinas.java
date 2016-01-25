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

public class FFormMantUsuariosOficinas extends ActionForm{

	private String tipo = null;
	private String usuario = null;
	private String codigo_oficina = null;
	
	/**
	 * @return
	 */
	
	 public void reset(ActionMapping mapping, HttpServletRequest request) {
			this.tipo = null;
			this.usuario = null;
			this.codigo_oficina = null;
			
		    }

	public String getCodigo_oficina() {
		return codigo_oficina;
	}

	public void setCodigo_oficina(String codigo_oficina) {
		this.codigo_oficina = codigo_oficina;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	 
	


	/**
	 * @return
	 */
	

}