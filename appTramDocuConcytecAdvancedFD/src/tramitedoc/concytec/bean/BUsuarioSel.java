package tramitedoc.concytec.bean;

import java.io.Serializable;

public class BUsuarioSel implements Serializable{
	
	private String usuario;
	private String nombre_corto;
	private String nombre_cargo;
	private String institucion;
	private String nombre_institucion;
	
	public BUsuarioSel() {
		super();
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombre_corto() {
		return nombre_corto;
	}

	public void setNombre_corto(String nombre_corto) {
		this.nombre_corto = nombre_corto;
	}

	public String getNombre_cargo() {
		return nombre_cargo;
	}

	public void setNombre_cargo(String nombre_cargo) {
		this.nombre_cargo = nombre_cargo;
	}

	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	public String getInstitucion() {
		return institucion;
	}

	public String getNombre_institucion() {
		return nombre_institucion;
	}

	public void setNombre_institucion(String nombre_institucion) {
		this.nombre_institucion = nombre_institucion;
	}
}
