package tramitedoc.concytec.bean;

import java.io.Serializable;

public class EstandarBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2945940787214150141L;
	int idBean;
	String nombreBean;
	public int getIdBean() {
		return idBean;
	}
	public void setIdBean(int idBean) {
		this.idBean = idBean;
	}
	public String getNombreBean() {
		return nombreBean;
	}
	public void setNombreBean(String nombreBean) {
		this.nombreBean = nombreBean;
	}

}
