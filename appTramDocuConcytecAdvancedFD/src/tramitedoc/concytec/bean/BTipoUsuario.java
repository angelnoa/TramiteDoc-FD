package tramitedoc.concytec.bean;

import java.io.Serializable;

public class BTipoUsuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4753369678883486891L;
	private String codigo;
	private String nombre;
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
