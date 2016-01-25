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

public class FFormLogin extends ActionForm{
	
	private String password = null;
	private String usuario = null;

	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return Returns the usuario.
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario The usuario to set.
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	/**
	 * Resetea todas las propiedades con sus valores por defecto.
	 * 
	 * @param mapping
	 *            El ActionMapping seleccionado para usar esta instancia
	 * @param request
	 *            La peticion del servlet
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.password = null;
		this.usuario = null;
	}
	
	/**
	 * Valida las propiedades que han sido colocadas desde la peticion HTTP y
	 * retorna un objeto ActionErrors que encapsula los errores de validacion
	 * que han sido encontrados. Si no hay errores, retorna null o un objeto
	 * ActionErrors sin mensajes de error almacenados.
	 * 
	 * @param mapping
	 *            El ActionMapping seleccionado para usar esta instancia
	 * @param request
	 *            La peticion del servlet
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

		if ((usuario == null) || (usuario.length() < 1))
			errors.add("login", new ActionError("user.required"));
		if ((password == null) || (password.length() < 1))
			errors.add("password", new ActionError("password.required"));
		
		return (errors);

	}

}
