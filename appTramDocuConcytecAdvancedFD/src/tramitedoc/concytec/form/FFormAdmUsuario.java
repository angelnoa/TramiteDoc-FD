package tramitedoc.concytec.form;

import org.apache.struts.action.ActionForm;

public class FFormAdmUsuario  extends ActionForm{
	
	private String usuario = null;
	private String password = null;
	private String new_password=null;
	private String confirm_new_password =null;
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the new_password
	 */
	public String getNew_password() {
		return new_password;
	}
	/**
	 * @param new_password the new_password to set
	 */
	public void setNew_password(String new_password) {
		this.new_password = new_password;
	}
	/**
	 * @return the confirm_new_password
	 */
	public String getConfirm_new_password() {
		return confirm_new_password;
	}
	/**
	 * @param confirm_new_password the confirm_new_password to set
	 */
	public void setConfirm_new_password(String confirm_new_password) {
		this.confirm_new_password = confirm_new_password;
	}
	
	

}
