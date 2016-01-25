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
public class BMedio extends Bean{
	
	private int id_medio;
	private String codigo_medio;
	private String descripcion;
	private String estado;
	public int getId_medio() {
		return id_medio;
	}
	public void setId_medio(int id_medio) {
		this.id_medio = id_medio;
	}
	public String getCodigo_medio() {
		return codigo_medio;
	}
	public void setCodigo_medio(String codigo_medio) {
		this.codigo_medio = codigo_medio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
	
}
