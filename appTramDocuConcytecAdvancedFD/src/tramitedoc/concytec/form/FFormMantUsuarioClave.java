package tramitedoc.concytec.form;

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

public class FFormMantUsuarioClave extends ActionForm{

	
	private String usuario = null;
	private String clave = null;
	private String clavenueva = null;
	private String confirmarclavenueva = null;
	
	/**
	 * @return
	 */
	
	 public void reset(ActionMapping mapping, HttpServletRequest request) {
	
			this.usuario = null;
			this.clave = null;
			this.clavenueva = null;
			this.confirmarclavenueva = null;
			
		    }

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getClavenueva() {
		return clavenueva;
	}

	public void setClavenueva(String clavenueva) {
		this.clavenueva = clavenueva;
	}

	public String getConfirmarclavenueva() {
		return confirmarclavenueva;
	}

	public void setConfirmarclavenueva(String confirmarclavenueva) {
		this.confirmarclavenueva = confirmarclavenueva;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	 
		

}