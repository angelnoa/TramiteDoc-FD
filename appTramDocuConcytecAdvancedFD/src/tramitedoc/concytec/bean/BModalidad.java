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
public class BModalidad extends Bean{
	
	private int cod_modalidad;
	private int cod_ruta;
	private String nombre_modalidad;
	private String estado;
	
	public int getCod_modalidad() {
		return cod_modalidad;
	}
	public void setCod_modalidad(int cod_modalidad) {
		this.cod_modalidad = cod_modalidad;
	}
	public int getCod_ruta() {
		return cod_ruta;
	}
	public void setCod_ruta(int cod_ruta) {
		this.cod_ruta = cod_ruta;
	}
	public String getNombre_modalidad() {
		return nombre_modalidad;
	}
	public void setNombre_modalidad(String nombre_modalidad) {
		this.nombre_modalidad = nombre_modalidad;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
			}
