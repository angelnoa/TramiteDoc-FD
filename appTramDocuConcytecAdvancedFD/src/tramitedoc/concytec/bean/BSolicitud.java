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
public class BSolicitud extends Bean{
	
	private String codigo_solicitud;
	private String nombre_solicitud;
	private String estado;
	
	public String getCodigo_solicitud() {
		return codigo_solicitud;
	}
	public void setCodigo_solicitud(String codigo_solicitud) {
		this.codigo_solicitud = codigo_solicitud;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNombre_solicitud() {
		return nombre_solicitud;
	}
	public void setNombre_solicitud(String nombre_solicitud) {
		this.nombre_solicitud = nombre_solicitud;
	}
	
				}
