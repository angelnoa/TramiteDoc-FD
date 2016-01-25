/*
 * Created on 23/07/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tramitedoc.concytec.bean;
import tramitedoc.concytec.util.Bean;
/**
 * @author Administrador
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BUsuario extends Bean{
	
	private String usuario;
	private String clave;
	private String estado;
	private String perfil;
	private String flag;
	
	private String codigo_oficina;
	private String descripcion_oficina;
	private String c_usuario;
	private String codigo_personal;
	private String nombre_personal;
	
	private String nombres;
	private String apellidos;
	private String email;
	private String responsable;
	
	private String dni;
	private String abreviatura;
	private String tipo_firma;
	
	private String nombre_corto;
	
	private String sede;
	
		
	public BUsuario() {
		// TODO Auto-generated constructor stub
	}
	public BUsuario(String nombres) {
		this.nombres = nombres;
		this.apellidos = "";
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
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getC_usuario() {
		return c_usuario;
	}
	public void setC_usuario(String c_usuario) {
		this.c_usuario = c_usuario;
	}
	public String getCodigo_oficina() {
		return codigo_oficina;
	}
	public void setCodigo_oficina(String codigo_oficina) {
		this.codigo_oficina = codigo_oficina;
	}
	public String getCodigo_personal() {
		return codigo_personal;
	}
	public void setCodigo_personal(String codigo_personal) {
		this.codigo_personal = codigo_personal;
	}
	public String getDescripcion_oficina() {
		return descripcion_oficina;
	}
	public void setDescripcion_oficina(String descripcion_oficina) {
		this.descripcion_oficina = descripcion_oficina;
	}
	public String getNombre_personal() {
		return nombre_personal;
	}
	public void setNombre_personal(String nombre_personal) {
		this.nombre_personal = nombre_personal;
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
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nombres+ " "+apellidos;
	}
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}
	public String getResponsable() {
		return responsable;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getDni() {
		return dni;
	}
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}
	public String getAbreviatura() {
		return abreviatura;
	}
	public void setTipo_firma(String tipo_firma) {
		this.tipo_firma = tipo_firma;
	}
	public String getTipo_firma() {
		return tipo_firma;
	}
	public void setNombre_corto(String nombre_corto) {
		this.nombre_corto = nombre_corto;
	}
	public String getNombre_corto() {
		return nombre_corto;
	}
	public String getSede() {
		return sede;
	}
	public void setSede(String sede) {
		this.sede = sede;
	}


}
