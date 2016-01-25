package tramitedoc.concytec.admin.form;

import java.io.File;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
/**
 * AUTOR		: Machuca Ovalle
 * FECHA		: 03-04-2006
 * VERSION		: 1.0
 * DESCRIPCIÓN	: Mapeo de datos del logeo recibidos del jsp  
 */

public class FFormMantUsuario extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 458426556499691105L;
	private String tipo = null;
	private String usuario = null;
	private String clave = null;
	private String estado = null;
	private String flag = null;
	private String nombres = null;
	private String apellidos = null;
	private String email = null;
	private String codigoUsuarioBuscar;
	private String dni = null;
	private String abrev = null;
	private String nombre_imagen = null;
	private boolean checked;
	private FormFile theFile = null;
	
	private String sede = null;
	
	/**
	 * @return
	 */
	
	 public void reset(ActionMapping mapping, HttpServletRequest request) {
			this.tipo = null;
			this.usuario = null;
			this.clave = null;
			this.estado = null;
			this.flag = null;
			this.nombres = null;
			this.apellidos = null;
			this.email = null;
			this.dni = null;
			this.abrev = null;
			this.nombre_imagen = null;
			this.checked = false;
			this.theFile = null;
		    }
	 
	 
	 
	public String getCodigoUsuarioBuscar() {
		return codigoUsuarioBuscar;
	}

	public void setCodigoUsuarioBuscar(String codigoUsuarioBuscar) {
		this.codigoUsuarioBuscar = codigoUsuarioBuscar;
	}



	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}



	public void setDni(String dni) {
		this.dni = dni;
	}



	public String getDni() {
		return dni;
	}



	public void setAbrev(String abrev) {
		this.abrev = abrev;
	}



	public String getAbrev() {
		return abrev;
	}



	public void setNombre_imagen(String nombre_imagen) {
		this.nombre_imagen = nombre_imagen;
	}



	public String getNombre_imagen() {
		return nombre_imagen;
	}


	public void setChecked(boolean checked) {
		this.checked = checked;
	}



	public boolean isChecked() {
		return checked;
	}



	public void setTheFile(FormFile theFile) {
		this.theFile = theFile;
	}



	public FormFile getTheFile() {
		return theFile;
	}



	public String getSede() {
		return sede;
	}



	public void setSede(String sede) {
		this.sede = sede;
	}



	/**
	 * @return
	 */
	

}