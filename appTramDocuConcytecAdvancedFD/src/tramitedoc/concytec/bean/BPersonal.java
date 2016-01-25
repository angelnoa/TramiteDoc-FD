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
public class BPersonal extends Bean{
	
	private String codigo_personal;
	private String codigo_fondo;
	private String nombre_personal;
	private String cargo_personal;
	private String c_usuario;
	private String clave;
	
	private String grado_personal;
	private String es_usuario;
	
	private String codigo_oficina;
	private String es_responsable;
	
	private String descripcion_oficina;
	private String codigo_motivo;
	private String descripcion_motivo;
	private String destinatario;
	private String nom_persona;
	private String observacion;
	
	private String validacion;
	
	private String sede;
	private int padre=0;
	
	
	public String getC_usuario() {
		return c_usuario;
	}
	public void setC_usuario(String c_usuario) {
		this.c_usuario = c_usuario;
	}
	public String getCargo_personal() {
		return cargo_personal;
	}
	public void setCargo_personal(String cargo_personal) {
		this.cargo_personal = cargo_personal;
	}
	public String getCodigo_fondo() {
		return codigo_fondo;
	}
	public void setCodigo_fondo(String codigo_fondo) {
		this.codigo_fondo = codigo_fondo;
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
	public String getEs_responsable() {
		return es_responsable;
	}
	public void setEs_responsable(String es_responsable) {
		this.es_responsable = es_responsable;
	}
	public String getEs_usuario() {
		return es_usuario;
	}
	public void setEs_usuario(String es_usuario) {
		this.es_usuario = es_usuario;
	}
	public String getGrado_personal() {
		return grado_personal;
	}
	public void setGrado_personal(String grado_personal) {
		this.grado_personal = grado_personal;
	}
	public String getNombre_personal() {
		return nombre_personal;
	}
	public void setNombre_personal(String nombre_personal) {
		this.nombre_personal = nombre_personal;
	}
	public String getCodigo_motivo() {
		return codigo_motivo;
	}
	public void setCodigo_motivo(String codigo_motivo) {
		this.codigo_motivo = codigo_motivo;
	}
	public String getDescripcion_motivo() {
		return descripcion_motivo;
	}
	public void setDescripcion_motivo(String descripcion_motivo) {
		this.descripcion_motivo = descripcion_motivo;
	}
	public String getDescripcion_oficina() {
		return descripcion_oficina;
	}
	public void setDescripcion_oficina(String descripcion_oficina) {
		this.descripcion_oficina = descripcion_oficina;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public String getNom_persona() {
		return nom_persona;
	}
	public void setNom_persona(String nom_persona) {
		this.nom_persona = nom_persona;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getValidacion() {
		return validacion;
	}
	public void setValidacion(String validacion) {
		this.validacion = validacion;
	}
	public String getSede() {
		return sede;
	}
	public void setSede(String sede) {
		this.sede = sede;
	}
	public int getPadre() {
		return padre;
	}
	public void setPadre(int padre) {
		this.padre = padre;
	}
	
		
		}
